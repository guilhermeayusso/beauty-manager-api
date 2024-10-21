package br.com.fiap.beautymanagerapi.records.servico;

public record ServicoResponseModel(
        Long id,
        String nome,
        String descricao,
        double preco
) {}

