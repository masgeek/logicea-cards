package com.munywele.cards.service;

import com.munywele.cards.dto.LoginResponse;
import com.munywele.cards.dto.UserResponse;
import com.munywele.cards.model.UserEntity;
import com.munywele.cards.repository.UserRepository;
import com.munywele.cards.utils.JwtUtils;
import com.munywele.cards.utils.UserDetailsImpl;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
/**
 * The UserService class implements the UserDetailsService interface.
 */
public class UserService implements UserDetailsService {

    @Value("${cards.time-zone}")
    private String timeZone = null;
    private final UserRepository userRepo;


    private final JwtUtils jwtUtils;

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public UserService(UserRepository userRepo,  JwtUtils jwtUtils) {
        this.userRepo = userRepo;
        this.jwtUtils = jwtUtils;
    }


    /**
     * The function retrieves a list of users from a repository and maps them to a list of user
     * responses using a model mapper.
     * 
     * @return The method is returning a List of UserResponse objects.
     */
    public List<UserResponse> getUsers() {
        List<UserEntity> users = userRepo.findAll();

        List<UserResponse> userResponseList = new ArrayList<>();
        for (UserEntity user : users) {
            UserResponse userResponse = modelMapper.map(user, UserResponse.class);
            userResponseList.add(userResponse);
        }
        return userResponseList;
    }

    @Override
    // The `loadUserByUsername` method is part of the `UserDetailsService` interface implementation in
    // the `UserService` class. It is responsible for loading a user's details based on their username.
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null) {
            throw new UsernameNotFoundException("Username not found");
        }
        UserEntity user = userRepo.findByUserEmail(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getUserRole().getRoleName()));

        return new UserDetailsImpl(user.getId().toString(), user.getUserEmail(), user.getUserPassword(), authorities);
    }

    /**
     * The function authUser takes an Authentication object, sets it as the current authentication in
     * the SecurityContextHolder, retrieves the UserDetails from the authentication object, finds the
     * corresponding UserEntity in the userRepo, creates a JWT token using jwtUtils, and returns a
     * LoginResponse object containing the JWT token.
     * 
     * @param authentication The authentication parameter is an object of type Authentication, which
     * represents the authentication request made by the user. It contains information such as the
     * user's credentials (username and password) and any additional details required for
     * authentication.
     * @return The method is returning a LoginResponse object.
     */
    public LoginResponse authUser(Authentication authentication) {
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        UserEntity userEntity = userRepo.findByUserEmail(userDetails.getUsername()).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        String bearerToken = jwtUtils.createToken(userEntity);
        return new LoginResponse(bearerToken);

    }
}
