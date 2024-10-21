package br.com.fiap.beautymanagerapi.records.estabelecimento;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record EstabelecimentoResponseModel(
        Long id,
        String nome,
        String mensagem
) {}
