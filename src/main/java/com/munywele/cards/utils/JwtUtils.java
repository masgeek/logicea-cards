package com.munywele.cards.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.munywele.cards.enums.EnumJwtClaims;
import com.munywele.cards.model.UserEntity;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;
import java.util.Objects;

@Component
public class JwtUtils {
    private final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${cards.time-zone}")
    private String timeZone = null;

    @Value("${jwt.secret}")
    private String secret = null;

    @Value("${jwt.token-validity}")
    private Long tokenValidity = 0L;

    @Value("${jwt.issuer}")
    private String issuer = null;

    private final SecureRandom secureRandom = new SecureRandom();

    public String createToken(UserEntity userEntity) throws JWTCreationException {

        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create()
                .withSubject(userEntity.getUserEmail())
                .withClaim(EnumJwtClaims.USERNAME.name(), userEntity.getUserEmail())
                .withClaim(EnumJwtClaims.ROLE.name(), userEntity.getUserRole().getRoleName())
                .withClaim(EnumJwtClaims.USER_ID.name(), userEntity.getId())
                .withIssuer(issuer)
                .withAudience(userEntity.getUserEmail())
                .withExpiresAt(getExpirationDate(tokenValidity))
                .sign(algorithm);


    }

    public boolean validateToken(HttpServletRequest request, UserDetails user) {
        String username = getUsernameFromToken(request);

        return Objects.equals(username, user.getUsername()) && !isTokenExpired(parseJwt(request));
    }


    public DecodedJWT decodeToken(HttpServletRequest request) {
        String jwt = parseJwt(request);
        if (jwt != null) {
            return verify(jwt);
        }
        return null;
    }

    public RefreshToken createRefreshToken(String username) {
        byte[] bytes = new byte[64];
        secureRandom.nextBytes(bytes);
        String token = Base64.getEncoder().encodeToString(bytes);
        LocalDateTime expiryDate = LocalDateTime.ofInstant(Instant.now().plusSeconds(tokenValidity), ZoneId.of(timeZone));

        return new RefreshToken(username, token, expiryDate);
    }

    private DecodedJWT verify(String token) throws JWTVerificationException {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier jwtVerifier = JWT.require(algorithm).withIssuer(issuer).build();
            return jwtVerifier.verify(token);
        } catch (JWTVerificationException ex) {
            logger.error("JWT cannot be verified because {}", ex.getMessage());
            throw ex;
        }
    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7, headerAuth.length());
        }
        return null;
    }

    private Date getExpirationDate(Long expirationInSeconds) {
        LocalDateTime localDate = LocalDateTime.now().plusSeconds(expirationInSeconds);
        return Date.from(localDate.atZone(ZoneId.of(timeZone)).toInstant());
    }


    private String getUsernameFromToken(HttpServletRequest request) {
        String token = parseJwt(request);
        DecodedJWT decodedJWT = verify(token);
        if (decodedJWT != null) {
            return decodedJWT.getClaim(EnumJwtClaims.USERNAME.name()).asString();
        }
        return "";
    }

    private boolean isTokenExpired(String token) {
        Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    private Date getExpirationDateFromToken(String token) {
        DecodedJWT decodedJWT = verify(token);
        if (decodedJWT != null) {
            return decodedJWT.getExpiresAt();
        }
        return new Date();
    }

    public boolean isRefreshTokenExpired(LocalDateTime expiryDate) {
        return expiryDate.isBefore(LocalDateTime.now());
    }
}
