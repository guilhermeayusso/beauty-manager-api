package br.com.fiap.beautymanagerapi.records.cliente;

public record ClienteResponseModel(
        String nome,
        String email,
        String telefone,
        String mensagem
) {
}