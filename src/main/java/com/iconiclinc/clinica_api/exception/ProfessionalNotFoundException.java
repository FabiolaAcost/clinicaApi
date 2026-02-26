package com.iconiclinc.clinica_api.exception;

public class ProfessionalNotFoundException extends RuntimeException{
    public ProfessionalNotFoundException(String email) {
        super("Professional not found with email: " + email);
    }
}
