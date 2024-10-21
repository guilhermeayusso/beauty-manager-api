package br.com.fiap.beautymanagerapi.records.avaliacao;

import jakarta.validation.constraints.*;

import java.io.Serializable;

public record AvaliacaoRequestModel(
        @NotBlank(message = "O autor não pode ser vazio")
        @Size(min = 3, max = 100, message = "O nome do autor deve ter entre 3 e 100 caracteres")
        String autor,

        @NotNull(message = "A nota é obrigatória")
        @Min(value = 0, message = "A nota mínima é 0")
        @Min(value = 5, message = "A nota máxima é 5")
        Double nota,

        @Size(max = 500, message = "O comentário não pode ultrapassar 500 caracteres")
        String comentario,

        @NotNull(message = "O ID do estabelecimento é obrigatório")
        @Positive(message = "O ID do estabelecimento deve ser um valor positivo")
        Long estabelecimento_id
) implements Serializable {
}

