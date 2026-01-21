package com.iconiclinc.clinica_api.exception;

public class ProfessionalNotFoundException extends RuntimeException{
    public ProfessionalNotFoundException(Integer id) {
        super("Professional not found with ID: " + id);
    }
}
