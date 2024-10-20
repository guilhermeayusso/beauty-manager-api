package br.com.fiap.beautymanagerapi.exception;

public class AgendamentoNotFoundException extends RuntimeException {
    public AgendamentoNotFoundException(String message) {
        super(message);
    }
}
