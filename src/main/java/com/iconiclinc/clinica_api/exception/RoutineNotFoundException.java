package com.iconiclinc.clinica_api.exception;

public class RoutineNotFoundException extends RuntimeException{
        public RoutineNotFoundException(Integer patientId) {
            super("No routines found for patient with ID: " + patientId);
        }
}
