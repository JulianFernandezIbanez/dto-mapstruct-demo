package com.example.service;

import org.mapstruct.Mapper;

import com.example.dto.UserResponse;
import com.example.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse mapUserToUserResponse(User user);

}
