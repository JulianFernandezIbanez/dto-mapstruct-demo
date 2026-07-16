package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.ContactDao;
import com.example.dao.UserDao;
import com.example.entity.Contact;
import com.example.entity.User;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ContactController {

    private final ContactDao contactDao;
    private final UserDao userDao;

    @PostMapping("/users/{userId}/contacts")
    public ResponseEntity<Contact> addContact(@PathVariable Long userId, @RequestBody Contact contact) {

        Contact Addedcontact = userDao.findById(userId).map(user ->{
            long contactId = contact.getId();
            if (contactId != 0L) {
                
                Contact _contact = contactDao.findById(contactId)
                    .orElseThrow();
                user.addContact(_contact);
                userDao.save(user);

                return _contact;

            }

            user.addContact(contact);
            return contactDao.save(contact);

        })
        	.orElseThrow();
        
        return new ResponseEntity<>(Addedcontact, HttpStatus.CREATED);
    }

    @DeleteMapping("/users/{userId}/contacts/{contactId}")
    public ResponseEntity<HttpStatus> deleteContactFromUser(@PathVariable Long userId, 
	@PathVariable Long contactId){

        User user = userDao.findById(userId)
            .orElseThrow();

        user.removeContact(contactId);
        userDao.save(user);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @DeleteMapping("/contacts/{id}")
	public ResponseEntity<HttpStatus> deleteContact(@PathVariable long id) {

		contactDao.deleteById(id);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}
    

}
