package com.example.service;

import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import com.example.dao.UserDao;
import com.example.dto.UserResponse;
import com.example.entity.Contact;
import com.example.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final UserMapper userMapper;
    private final ContactService contactService;

    @Override
    public UserResponse getUserById(long id) {

        UserResponse userResponse = null;

        // Mapeo manual entre la entidad User y el DTO UserResponse

        User user = userDao.findById(id).orElseThrow(() -> new RuntimeException("User not found!!!"));
        Contact contact = contactService.getContactById(id);

        /* Mapeo manual
            userResponse = new UserResponse(
            user.getId(), 
            user.getUsername(), 
            user.getPassword(), 
            DateTimeFormatter.ISO_LOCAL_DATE.format(user.getDateOfBirth())
        );*/

        userResponse = userMapper.mapUserAndContactToUserResponse(user, contact);

        return userResponse;

    }

}
