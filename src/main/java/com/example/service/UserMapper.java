package com.example.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.example.dto.UserResponse;
import com.example.entity.Contact;
import com.example.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // ¿Como mapear campos que no tienen el mismo nombre en la entidad User
    // y en el DTO UserResponse?
    // @Mapping(source = "username", target = "name")
    // @Mapping(source = "dateOfBirth", target = "dob")

    // ¿Como ignorar campos en el DTO? Por ejemplo que no se muestre el campo password
    // @Mapping(target = "password", ignore = true)

    // Establecer un valor por defecto en el target cuando el source es NULL
    // por ejemplo para el campo status que es NULL para una de los dos registros 
    // que tenemos en la tabla users
    // @Mapping(source = "status", target = "status", defaultValue = "INACTIVE")


    /**
    * 
    * Solamente quiero mapear el campo que explicitamente he especificado e ignorar todos los demas,
    * que por defecto se mapearian porque tienen el mismo nombre
    * 
    * Para lo anterior hay que utilizar la anotacion @BeanMapping
    */
   // @BeanMapping(ignoreByDefault = true)
    // @Mapping(source = "username", target = "name")
    // UserResponse mapUserToUserResponse(User user);

    /*
    Mapear User y Contact a UserResponse

    Cambiamos el nombre del metodo
    */

    @Mapping(source = "user.id", target = "id")
    @Mapping(source = "user.username", target = "name")
    @Mapping(target = "password", ignore = true)
    @Mapping(source = "user.dateOfBirth", target = "dob")
    @Mapping(source = "user.status", target = "status", defaultValue = "INACTIVE")
    @Mapping(expression = "java(extractMobiles(user.getContacts()))", target = "mob")
    @Mapping(expression = "java(extractEmails(user.getContacts()))", target = "email")
    UserResponse mapUserAndContactToUserResponse(User user);

    //Mapear de manera personalizada (En este caso mostrar ***** en vez del numero de tlf)

    default List<String> extractMobiles(Set<Contact> contacts) {
        if (contacts == null || contacts.isEmpty()) {
            return null;
        }
        return contacts.stream()
                .map(Contact::getMobileNumber)
                .map(UserMapper::getPhoneNumber)
                .collect(Collectors.toList());
    }

    default List<String> extractEmails(Set<Contact> contacts) {
        if (contacts == null || contacts.isEmpty()) {
            return null;
        }
        return contacts.stream()
                .map(Contact::getEmail)
                .filter(email -> email != null)
                .collect(Collectors.toList());
    }

    static String getPhoneNumber(String phone) {
        
        if (phone == null) return null;
        return phone.replaceAll("\\d(?=\\d{3})", "*");
    }

}
