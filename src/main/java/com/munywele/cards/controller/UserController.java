package com.munywele.cards.controller;

import com.munywele.cards.dto.LoginRequest;
import com.munywele.cards.dto.LoginResponse;
import com.munywele.cards.dto.UserResponse;
import com.munywele.cards.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/users")
@RestController
@EnableMethodSecurity
/**
 * The UserController class is responsible for managing user-related operations.
 */
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

//    @Autowired
    public UserController(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/auth")
    // The `authUser` method is a POST request handler that authenticates a user. It takes in a
    // `LoginRequest` object as the request body, which contains the user's email and password. The
    // `@Valid` annotation is used for request body validation.
    public ResponseEntity<LoginResponse> authUser(@Valid @RequestBody LoginRequest userRequest) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequest.getEmail(), userRequest.getPassword()));

        LoginResponse loginResponse = userService.authUser(authentication);
        return ResponseEntity.ok(loginResponse);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    // The `listUsers()` method is a GET request handler that is responsible for retrieving a list of
    // users. It is annotated with `@GetMapping` to map the method to the `/api/v1/users` endpoint.
    public ResponseEntity<List<UserResponse>> listUsers() {
        List<UserResponse> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }
}
