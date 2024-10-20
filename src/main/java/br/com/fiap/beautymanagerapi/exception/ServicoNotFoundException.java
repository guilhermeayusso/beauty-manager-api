package br.com.fiap.beautymanagerapi.exception;

public class ServicoNotFoundException extends RuntimeException {
    public ServicoNotFoundException(String message) {
        super(message);
    }
}