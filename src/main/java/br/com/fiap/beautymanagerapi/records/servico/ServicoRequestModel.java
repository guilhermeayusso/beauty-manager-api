package br.com.fiap.beautymanagerapi.records.servico;

import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.util.List;

public record ServicoRequestModel(

        @NotBlank(message = "O nome não pode estar em branco")
        @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres")
        String nome,

        @NotBlank(message = "A descrição não pode estar em branco")
        @Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres")
        String descricao,

        @Positive(message = "O preço deve ser um valor positivo")
        @DecimalMin(value = "0.01", message = "O preço deve ser no mínimo 0.01")
        double preco,

        @NotNull(message = "O ID do estabelecimento é obrigatório")
        @Min(value = 1, message = "O ID do estabelecimento deve ser maior que 0")
        Long estabelecimentoId,

        @NotEmpty(message = "A lista de IDs dos profissionais não pode estar vazia")
        List<Long> profissionaisIds
) implements Serializable {}
