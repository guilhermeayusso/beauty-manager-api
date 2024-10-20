package br.com.fiap.beautymanagerapi.records.servico;

import java.io.Serializable;
import java.util.List;

public record ServicoInputDTO(
        String nome,
        String descricao,
        double preco,
        Long estabelecimentoId,
        List<Long> profissionaisIds
)implements Serializable {}

