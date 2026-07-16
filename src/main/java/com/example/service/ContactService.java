package com.example.service;

import java.util.Set;

import com.example.entity.Contact;

public interface ContactService {

    Contact getContactById(long id);
    Set<Contact> getContactsByUserId(long userId);

}
