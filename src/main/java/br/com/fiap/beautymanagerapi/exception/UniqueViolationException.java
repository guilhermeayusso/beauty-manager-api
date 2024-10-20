package br.com.fiap.beautymanagerapi.exception;

public class UniqueViolationException extends RuntimeException {
    public UniqueViolationException(String message) {
        super(message);
    }
}