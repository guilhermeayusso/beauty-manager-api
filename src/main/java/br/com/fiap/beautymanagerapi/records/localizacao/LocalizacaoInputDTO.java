package br.com.fiap.beautymanagerapi.records.localizacao;

import java.io.Serializable;

public record LocalizacaoInputDTO(
        double latitude,
        double longitude,
        Long estabelecimentoId
)implements Serializable {}

