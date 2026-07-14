package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Contact;

public interface ContactDao extends JpaRepository<Contact, Long> {

}
