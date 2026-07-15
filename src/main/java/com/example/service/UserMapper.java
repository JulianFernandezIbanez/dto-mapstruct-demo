package com.example.service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

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
    @Mapping(source = "contact.mobileNumber", target = "mob")
    @Mapping(source = "contact.email", target = "emailId")
    UserResponse mapUserAndContactToUserResponse(User user, Contact contact);

}
