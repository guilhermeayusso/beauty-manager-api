package br.com.fiap.beautymanagerapi.exception;

public class EstabelecimentoNotFoundException extends RuntimeException {
    public EstabelecimentoNotFoundException(String message) {
        super(message);
    }
}