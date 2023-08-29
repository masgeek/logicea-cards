package com.munywele.cards.controller;

import com.munywele.cards.model.UserEntity;
import com.munywele.cards.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("api/v1/user")
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/auth")
    public ResponseEntity<List<UserEntity>> authUser() {
        List<UserEntity> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }
}
