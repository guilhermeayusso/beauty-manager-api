package br.com.fiap.beautymanagerapi.records.estabelecimento;

import br.com.fiap.beautymanagerapi.enums.TipoEstabelecimento;
import br.com.fiap.beautymanagerapi.records.endereco.EnderecoInputDTO;

import java.time.LocalTime;
import java.util.List;

public record EstabelecimentoInputDTO(
        String nome,
        LocalTime horarioDeAbertura,
        LocalTime horarioDeFechamento,
        TipoEstabelecimento tipoEstabelecimento,
        List<String> fotos
) {}
