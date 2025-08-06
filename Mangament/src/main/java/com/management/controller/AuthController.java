package com.management.controller;

import com.management.model.AuthRequest;
import com.management.model.User;
import com.management.model.UserDTO;
import com.management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserDTO userDTO) {
        User user = userService.registerUser(userDTO);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody AuthRequest authRequest) {
        User user = userService.authenticate(authRequest.getUsername(), authRequest.getPassword());
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.status(401).build();
    }
}
