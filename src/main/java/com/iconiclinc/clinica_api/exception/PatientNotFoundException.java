package com.iconiclinc.clinica_api.exception;

public class PatientNotFoundException extends RuntimeException{
    public PatientNotFoundException(Integer patientId) {
        super("Patient not found with ID: " + patientId);
    }

    public PatientNotFoundException(String message) {
        super(message);
    }
}
