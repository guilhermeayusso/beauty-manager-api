package br.com.fiap.beautymanagerapi.records.localizacao;

import java.io.Serializable;

public record LocalizacaoOutputDTO(
        double latitude,
        double longitude,
        String estabelecimentoNome
)implements Serializable {}

