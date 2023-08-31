package com.munywele.cards.utils;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.munywele.cards.enums.EnumJwtClaims;
import com.munywele.cards.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private UserService userService;

    private final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
        try {
            DecodedJWT decodedJWT = jwtUtils.decodeToken(request);
            if (decodedJWT != null) {
                String username = decodedJWT.getClaim(EnumJwtClaims.USERNAME.name()).asString();
                UserDetails user = userService.loadUserByUsername(username);
                if (jwtUtils.validateToken(request, user)) {
                    String id = decodedJWT.getClaim(EnumJwtClaims.USER_ID.name()).asString();

                    Set<? extends GrantedAuthority> authSet = new HashSet<>(user.getAuthorities());

                    UserDetailsImpl userDetails = new UserDetailsImpl(id, username, "", authSet);
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }

            }

        } catch (Exception ex) {
            logger.error("Cannot set user authentication {}", ex.getMessage());
        }

        filterChain.doFilter(request, response);
    }
}
