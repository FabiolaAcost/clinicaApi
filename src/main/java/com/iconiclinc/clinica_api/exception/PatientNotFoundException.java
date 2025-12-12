package com.iconiclinc.clinica_api.exception;

import com.sun.jdi.PrimitiveValue;

public class PatientNotFoundException extends RuntimeException{
    public PatientNotFoundException(Integer patientId) {
        super("Patient not found with ID: " + patientId);
    }
}
