package com.example.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String username;
	private String password;
	private LocalDate dateOfBirth;
	private String status;

	@Builder.Default
	@ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "user-contacts", joinColumns = {@JoinColumn(name = "user_id")},inverseJoinColumns = {@JoinColumn(name = "contact_id")})
	private Set<Contact> contacts = new HashSet<>();

	public void addContact(Contact contact){

		if (this.contacts == null) {
            this.contacts = new HashSet<>();
        }

        if (contact.getUsers() == null) {
            contact.setUsers(new HashSet<>());
        }


		this.contacts.add(contact);
		contact.getUsers().add(this);

	}

	public void removeContact(long contactId){

		Contact contact = this.contacts.stream().filter(c -> c.getId() == contactId).findFirst().orElse(null);
		if (contact != null) {
			
			this.contacts.remove(contact);
			contact.getUsers().remove(this);

		}

	}

}
