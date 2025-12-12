package com.iconiclinc.clinica_api.exception;

public class TreatmentNotFoundException extends RuntimeException{

    public TreatmentNotFoundException(Integer patientId) {
        super("No treatments found for patient with ID: " + patientId);
    }
}
