package br.com.fiap.beautymanagerapi.exception;

public class ProfissionalNotFoundException extends RuntimeException {
    public ProfissionalNotFoundException(String message) {
        super(message);
    }
}