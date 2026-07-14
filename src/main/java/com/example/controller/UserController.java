package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.UserResponse;
import com.example.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // Metodo que devuelve un usuario por el id
    @GetMapping(path = "/{id}")
    public UserResponse getUserById(@PathVariable long id) {

        return userService.getUserById(id);
        
    }

}