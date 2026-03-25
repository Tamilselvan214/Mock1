package com.tms.backend.controller;

import com.tms.backend.model.User;
import com.tms.backend.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserRepository userRepo;

    // Create User
    @PostMapping("/create")
    public User createUser(@RequestBody User user) {
        return userRepo.save(user);
    }

    // Get All Users
    @GetMapping("/")
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    // Get Employees under Manager
    @GetMapping("/manager/{managerId}")
    public List<User> getByManager(@PathVariable Long managerId) {
        return userRepo.findByManagerId(managerId);
    }
}
