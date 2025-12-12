package com.iconiclinc.clinica_api.exception;

public class RecommendationNotFoundException extends RuntimeException{
    public RecommendationNotFoundException(Integer patientId) {
        super("No recommendations found for patient with ID: " + patientId);
    }
}
