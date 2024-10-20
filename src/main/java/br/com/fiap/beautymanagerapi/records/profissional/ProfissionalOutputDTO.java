package br.com.fiap.beautymanagerapi.records.profissional;

import br.com.fiap.beautymanagerapi.enums.StatusProfissional;

import java.io.Serializable;

public record ProfissionalOutputDTO(
        Long id,
        String nome,
        String especialidades,
        StatusProfissional statusProfissional,
        String estabelecimento,
        String enderecoEstabelecimento
)implements Serializable {
}
