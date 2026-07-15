package com.example.service;

import org.springframework.stereotype.Service;

import com.example.dao.ContactDao;
import com.example.entity.Contact;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactDao contactDao;

    @Override
    public Contact getContactById(long id) {

        return contactDao.findById(id).get();
        
    }

}
