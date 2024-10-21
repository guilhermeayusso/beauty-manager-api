package br.com.fiap.beautymanagerapi.records.localizacao;

import java.io.Serializable;

public record LocalizacaoResquestModel(
        double latitude,
        double longitude,
        Long estabelecimentoId
)implements Serializable {}

