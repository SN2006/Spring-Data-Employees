package com.example.app.SpringDataEmployees.exceptions;

import lombok.Getter;

import java.util.Map;

@Getter
public class EmployeeDataException extends RuntimeException {

    private final Map<String, String> errors;

    public EmployeeDataException(String message, Map<String, String> errors) {
        super(message);
        this.errors = errors;
    }
}
