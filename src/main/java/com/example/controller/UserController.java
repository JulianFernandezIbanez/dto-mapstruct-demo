package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.UserDao;
import com.example.dto.UserResponse;
import com.example.entity.User;
import com.example.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(path = "/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserDao userDao;

    // Metodo que devuelve un usuario por el id
    @GetMapping(path = "/users/{id}")
    public UserResponse getUserById(@PathVariable long id) {

        return userService.getUserById(id);
        
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        
        User savedUser = userDao.save(
            User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .status(user.getStatus())
                .dateOfBirth(user.getDateOfBirth())
        .build());
        
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
		
		userDao.deleteById(id);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	} 
    

}