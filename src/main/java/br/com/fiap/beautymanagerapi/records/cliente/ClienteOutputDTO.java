package br.com.fiap.beautymanagerapi.records.cliente;

public record ClienteOutputDTO(
        Long id,
        String nome,
        String email,
        String telefone
) {
}
