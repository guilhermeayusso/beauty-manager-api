package br.com.fiap.beautymanagerapi.records.servico;

public record ServicoOutputDTO(
        Long id,
        String nome,
        String descricao,
        double preco
) {}

