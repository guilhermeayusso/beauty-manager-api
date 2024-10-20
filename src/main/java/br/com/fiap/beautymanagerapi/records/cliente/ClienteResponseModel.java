package br.com.fiap.beautymanagerapi.records.cliente;

public record ClienteResponseModel(
        Long id,
        String nome,
        String email,
        String telefone,
        String mensagem
) {
}