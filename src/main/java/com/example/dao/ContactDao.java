package com.example.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Contact;


public interface ContactDao extends JpaRepository<Contact, Long> {

    Set<Contact> findByUsers_id(long userId);

}
