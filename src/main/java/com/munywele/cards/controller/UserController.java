package com.munywele.cards.controller;

import com.munywele.cards.dto.LoginResponse;
import com.munywele.cards.dto.LoginRequest;
import com.munywele.cards.model.UserEntity;
import com.munywele.cards.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/users")
@RestController
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserController(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/auth")
    public ResponseEntity<LoginResponse> authUser(@Valid @RequestBody LoginRequest userRequest) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequest.getEmail(), userRequest.getPassword()));

        LoginResponse loginResponse = userService.authUser(authentication);
        return ResponseEntity.ok(loginResponse);
    }

    @GetMapping
    public ResponseEntity<List<UserEntity>> listUsers() {
        List<UserEntity> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }
}
