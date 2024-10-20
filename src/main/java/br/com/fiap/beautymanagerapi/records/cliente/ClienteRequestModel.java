package br.com.fiap.beautymanagerapi.records.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Modelo de requisição para criação de um cliente.
 */
public record ClienteRequestModel(
        /**
         * Nome completo do cliente.
         */
        @NotBlank(message = "O nome é obrigatório.")
        @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres.")
        String nome,

        /**
         * Email do cliente para contato.
         */
        @NotBlank(message = "O email é obrigatório.")
        @Email(message = "O email deve ser válido.")
        String email,

        /**
         * Telefone do cliente no formato (XX) XXXXX-XXXX.
         */
        @NotBlank(message = "O telefone é obrigatório.")
        @Pattern(regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}", message = "O telefone deve seguir o padrão (99) 99999-9999.")
        String telefone
) {
}
