package com.example.service;

import com.example.dto.UserResponse;

public interface UserService {

    UserResponse getUserById(long id);
    
}