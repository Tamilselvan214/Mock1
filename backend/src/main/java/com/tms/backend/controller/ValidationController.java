package com.tms.backend.controller;

import com.tms.backend.model.User;
import com.tms.backend.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private UserRepository userRepo;

    // Simple Login (no JWT yet)
    @PostMapping("/login")
    public String login(@RequestBody User req) {

        Optional<User> user = userRepo.findByEmail(req.getEmail());

        if (user.isPresent() &&
            user.get().getPassword().equals(req.getPassword())) {

            return "Login Successful";
        }

        return "Invalid Credentials";
    }
}
