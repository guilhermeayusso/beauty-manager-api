package br.com.fiap.beautymanagerapi.records.profissional;

import br.com.fiap.beautymanagerapi.enums.StatusProfissional;

import java.io.Serializable;

public record ProfissionalInputDTO(
        String nome,
        String especialidades,
        StatusProfissional statusProfissional,
        Long estabelecimento_id
) implements Serializable {
}
