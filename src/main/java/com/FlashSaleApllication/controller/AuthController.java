package com.FlashSaleApllication.controller;
import com.FlashSaleApllication.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.FlashSaleApllication.entity.User;
import com.FlashSaleApllication.repository.UserRepository;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository userRepository,JwtUtil jwtUtil) {
        this.userRepository = userRepository;
         this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        userRepository.save(user);
        return ResponseEntity.ok("User registered");
    }

@PostMapping("/login")
public ResponseEntity<String> login(@RequestBody User user) {
    User dbUser = userRepository.findByUsername(user.getUsername())
            .orElseThrow(() -> new RuntimeException("User not found"));

    if (!dbUser.getPassword().equals(user.getPassword())) {
        throw new RuntimeException("Invalid credentials");
    }

    String token = jwtUtil.generateToken(dbUser.getId());
    return ResponseEntity.ok(token);
}

}
