package com.suraj.JWT_App.controller;

import com.suraj.JWT_App.entity.Users;
import com.suraj.JWT_App.payload.AuthenticationRequestDTO;
import com.suraj.JWT_App.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService service) {
        this.authService = service;
    }

    @GetMapping("/getMessage")
    public ResponseEntity<String> getMessage(){
        return ResponseEntity.ok("Success fully developed this...");
    }

    @PostMapping("/user")
    public ResponseEntity<String> registerUser(@RequestBody Users user) {
        authService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("Registration Successful");
    }

    @PostMapping("/content-manager-register")
    public ResponseEntity<String> registerContentManager(@RequestBody Users user) {
        authService.registerContentManager(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("Registration Successful");
    }

    @PostMapping("/blog-manager-register")
    public ResponseEntity<String> registerBlogManager(@RequestBody Users user) {
        authService.registerBlogManager(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("Registration Successful");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthenticationRequestDTO user) {
        String token = authService.verify(user);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        return ResponseEntity.ok("Logout Successful");
    }
}
