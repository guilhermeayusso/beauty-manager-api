package br.com.fiap.beautymanagerapi.records.cliente;

public record ClienteRequestModel(
        String nome,
        String email,
        String telefone
) {
}
