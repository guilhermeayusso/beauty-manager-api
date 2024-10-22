package br.com.fiap.beautymanagerapi.records.endereco;

import jakarta.validation.constraints.*;

import java.io.Serializable;

public record EnderecoRequestModel(

        @NotBlank(message = "O CEP não pode estar em branco")
        @Pattern(regexp = "\\d{5}-\\d{3}", message = "O CEP deve estar no formato 99999-999")
        String cep,

        @NotBlank(message = "O logradouro não pode estar em branco")
        @Size(max = 255, message = "O logradouro deve ter no máximo 255 caracteres")
        String logradouro,

        @NotBlank(message = "O número não pode estar em branco")
        @Size(max = 10, message = "O número deve ter no máximo 10 caracteres")
        String numero,

        @Size(max = 255, message = "O complemento deve ter no máximo 255 caracteres")
        String complemento,

        @NotBlank(message = "O bairro não pode estar em branco")
        @Size(max = 100, message = "O bairro deve ter no máximo 100 caracteres")
        String bairro,

        @NotBlank(message = "A cidade não pode estar em branco")
        @Size(max = 100, message = "A cidade deve ter no máximo 100 caracteres")
        String cidade,

        @NotBlank(message = "O UF não pode estar em branco")
        @Pattern(regexp = "[A-Z]{2}", message = "O UF deve ser composto por 2 letras maiúsculas")
        String uf,

        @NotNull(message = "O ID do estabelecimento é obrigatório")
        @Min(value = 1, message = "O ID do estabelecimento deve ser maior que 0")
        Long estabelecimentoId

) implements Serializable {}
