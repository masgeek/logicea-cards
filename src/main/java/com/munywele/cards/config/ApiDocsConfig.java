package com.munywele.cards.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(name = "bearer", scheme = "bearer", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class ApiDocsConfig {

    @Bean
    public OpenAPI api(
            @Value("${cards.version}") String appVersion,
            @Value("${cards.name}") String appName,
            @Value("${cards.dev-email}") String devEmail,
            @Value("${cards.company}") String companyName,
            @Value("${cards.doc-site}") String docSite,
            @Value("${cards.license}") String license
    ) {
        return new OpenAPI()
                .info(new Info()
                        .title(appName)
                        .description(appName)
                        .contact(new Contact()
                                .email(devEmail)
                                .name(companyName)
                                .url(docSite))
                        .version(appVersion)
                        .license(new License()
                                .name(license)
                                .url("https://www.gnu.org/licenses/gpl-3.0.en.html")))
                .externalDocs(new ExternalDocumentation()
                        .description(appName + " Documentation")
                        .url(docSite));
    }

}
