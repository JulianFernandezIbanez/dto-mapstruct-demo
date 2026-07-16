package com.example.service;

import java.util.Set;

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

        return contactDao.findById(id).orElseThrow(() -> new RuntimeException("Contact not found!!!"));
        
    }

    @Override
    public Set<Contact> getContactsByUserId(long userId) {

        return contactDao.findByUsers_id(userId);

    }

}
