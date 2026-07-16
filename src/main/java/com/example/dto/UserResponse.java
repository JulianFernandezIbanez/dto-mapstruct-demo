package com.example.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

// La siguiente anotacion es para que el proceso de deserializacion solo muestre
// propiedades que no sean NULL
@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserResponse(
    long id,
    String name,
    String password,
    String dob,
    String status,
    //Campos de Contact
    List<String> mob,
    List<String> email
) {}