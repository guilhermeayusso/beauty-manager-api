package br.com.fiap.beautymanagerapi.records.profissional;

import br.com.fiap.beautymanagerapi.enums.StatusProfissional;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record ProfissionalRequestModel(
        @NotBlank(message = "O nome não pode estar em branco")
        @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres")
        String nome,

        @NotBlank(message = "As especialidades não podem estar em branco")
        String especialidades,

        @NotNull(message = "O status do profissional é obrigatório")
        StatusProfissional statusProfissional,

        @NotNull(message = "O ID do estabelecimento é obrigatório")
        @Min(value = 1, message = "O ID do estabelecimento deve ser maior que 0")
        Long estabelecimento_id
) implements Serializable {
}
