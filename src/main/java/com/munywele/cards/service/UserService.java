package com.munywele.cards.service;

import com.munywele.cards.dto.LoginResponse;
import com.munywele.cards.model.UserEntity;
import com.munywele.cards.model.UserTokenEntity;
import com.munywele.cards.repository.UserRepository;
import com.munywele.cards.repository.UserTokenRepository;
import com.munywele.cards.utils.JwtUtils;
import com.munywele.cards.utils.RefreshToken;
import com.munywele.cards.utils.UserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepo;

    private final UserTokenRepository userTokenRepo;

    private final JwtUtils jwtUtils;

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(UserRepository userRepo, UserTokenRepository userTokenRepo, JwtUtils jwtUtils) {
        this.userRepo = userRepo;
        this.userTokenRepo = userTokenRepo;
        this.jwtUtils = jwtUtils;
    }


    public List<UserEntity> getUsers() {
        return userRepo.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null) {
            throw new UsernameNotFoundException("Username not found");
        }
        UserEntity user = userRepo.findByUserEmail(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getUserRole().getRoleName()));

        return new UserDetailsImpl(user.getId().toString(), user.getUserEmail(), user.getUserPassword(), authorities);
    }

    public LoginResponse authUser(Authentication authentication) {
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        boolean isTokenExpired = true;

        UserTokenEntity userTokenEntity = userTokenRepo.findByUserEmail(userDetails.getUsername());
        UserEntity userEntity = userRepo.findByUserEmail(userDetails.getUsername()).orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        RefreshToken refreshToken = jwtUtils.createRefreshToken(userDetails.getUsername());

        if (userTokenEntity == null) {
            userTokenEntity = new UserTokenEntity();
            userTokenEntity.setUserEmail(refreshToken.getUsername());
        } else {
            isTokenExpired = jwtUtils.isRefreshTokenExpired(userTokenEntity.getExpiryDate());
        }

        if (isTokenExpired) {
            logger.info("Refresh token has expired, generating new token");
            userTokenEntity.setToken(refreshToken.getToken());
            userTokenEntity.setExpiryDate(refreshToken.getExpiryDate());
        }
        UserTokenEntity resp = userTokenRepo.save(userTokenEntity);
        String bearerToken = jwtUtils.createToken(userEntity);


        return new LoginResponse(bearerToken,resp.getExpiryDate());

    }
}