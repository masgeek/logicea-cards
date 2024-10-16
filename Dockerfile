# base image to build a JRE
FROM amazoncorretto:21.0.5-alpine as corretto-jdk

# required for strip-debug to work
RUN apk add --no-cache binutils

# Build small JRE image
RUN $JAVA_HOME/bin/jlink \
         --verbose \
         --add-modules ALL-MODULE-PATH \
         --strip-debug \
         --no-man-pages \
         --no-header-files \
         --compress=2 \
         --output /customjre

# main app image
FROM alpine:latest

ENV JAVA_HOME=/jre
ENV PATH="${JAVA_HOME}/bin:${PATH}"

# copy JRE from the base image
COPY --from=corretto-jdk /customjre $JAVA_HOME


COPY build/libs/cards*.jar app.jar
COPY src/main/resources/logback-spring.xml /src/main/resources/logback-spring.xml

EXPOSE 9005
ENTRYPOINT [ "/jre/bin/java", "-jar", "app.jar" ]
