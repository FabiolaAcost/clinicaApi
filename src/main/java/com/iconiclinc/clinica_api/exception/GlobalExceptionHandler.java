package com.iconiclinc.clinica_api.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePatientNotFoundException(
            PatientNotFoundException exception,
            HttpServletRequest request){
        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                exception.getMessage(),
                request.getRequestURI(),
                "PATIENT_NOT_FOUND"
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RoutineNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleRoutineNotFoundException(
            RoutineNotFoundException exception,
            HttpServletRequest request){
        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                exception.getMessage(),
                request.getRequestURI(),
                "ROUTINE_NOT_FOUND"
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RecommendationNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleRecommendationNotFoundException(
            RecommendationNotFoundException exception,
            HttpServletRequest request){
        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                exception.getMessage(),
                request.getRequestURI(),
                "RECOMMENDATION_NOT_FOUND"
        );
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TreatmentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTreatmentNotFoundException(
            TreatmentNotFoundException exception,
            HttpServletRequest request){
        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                exception.getMessage(),
                request.getRequestURI(),
                "TREATMENT_NOT_FOUND"
        );
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(
            BusinessException exception,
            HttpServletRequest request){

        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                exception.getMessage(),
                request.getRequestURI(),
                "BUSINESS_ERROR"
        );
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProfessionalNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProfessionalNotFoundException(
            ProfessionalNotFoundException exception,
            HttpServletRequest request){
        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                exception.getMessage(),
                request.getRequestURI(),
                "PROFESSIONAL_NOT_FOUND"
        );
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(
            UserNotFoundException exception,
            HttpServletRequest request){
        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                exception.getMessage(),
                request.getRequestURI(),
                "USER_NOT_FOUND"
        );
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }
}
