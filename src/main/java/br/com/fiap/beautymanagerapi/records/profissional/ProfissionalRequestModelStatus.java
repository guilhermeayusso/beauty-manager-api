package br.com.fiap.beautymanagerapi.records.profissional;

import br.com.fiap.beautymanagerapi.enums.StatusProfissional;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public record ProfissionalRequestModelStatus(
        @NotNull(message = "O status do profissional é obrigatório")
        StatusProfissional statusProfissional
) implements Serializable {
}
