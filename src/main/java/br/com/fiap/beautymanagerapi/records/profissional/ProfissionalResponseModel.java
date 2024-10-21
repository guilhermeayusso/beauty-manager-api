package br.com.fiap.beautymanagerapi.records.profissional;

import br.com.fiap.beautymanagerapi.enums.StatusProfissional;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ProfissionalResponseModel(
        Long id,
        String nome,
        String especialidades,
        StatusProfissional statusProfissional,
        String estabelecimento,
        String enderecoEstabelecimento
)implements Serializable {
}
