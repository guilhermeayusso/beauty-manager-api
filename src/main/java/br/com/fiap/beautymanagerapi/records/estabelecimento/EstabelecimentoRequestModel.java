package br.com.fiap.beautymanagerapi.records.estabelecimento;

import br.com.fiap.beautymanagerapi.enums.TipoEstabelecimento;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.List;

public record EstabelecimentoRequestModel(
        @NotBlank(message = "O nome do estabelecimento é obrigatório.")
        @Size(max = 100, message = "O nome do estabelecimento deve ter no máximo 100 caracteres.")
        String nome,

        @NotNull(message = "O horário de abertura é obrigatório.")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
        @Pattern(regexp = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$", message = "Horário de fechamento deve estar no formato HH:mm (00:00 a 23:59)")
        String horarioDeAbertura,

        @NotNull(message = "O horário de fechamento é obrigatório.")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
        @Pattern(regexp = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$", message = "Horário de fechamento deve estar no formato HH:mm (00:00 a 23:59)")
        String horarioDeFechamento,

        @NotNull(message = "O tipo de estabelecimento é obrigatório.")
        TipoEstabelecimento tipoEstabelecimento,

        List<String> fotos // Este campo é opcional, sem validações
) implements Serializable {}
