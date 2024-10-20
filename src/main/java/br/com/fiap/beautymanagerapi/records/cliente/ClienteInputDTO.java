package br.com.fiap.beautymanagerapi.records.cliente;

public record ClienteInputDTO(
        String nome,
        String email,
        String telefone
) {
}
