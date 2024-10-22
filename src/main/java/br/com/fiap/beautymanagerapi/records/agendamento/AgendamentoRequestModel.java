package br.com.fiap.beautymanagerapi.records.agendamento;

import br.com.fiap.beautymanagerapi.enums.StatusAgendamento;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDateTime;

public record AgendamentoRequestModel(

        @NotNull(message = "O ID do cliente é obrigatório")
        @Min(value = 1, message = "O ID do cliente deve ser maior que 0")
        Long clienteId,

        @NotNull(message = "O ID do profissional é obrigatório")
        @Min(value = 1, message = "O ID do profissional deve ser maior que 0")
        Long profissionalId,

        @NotNull(message = "O ID do serviço é obrigatório")
        @Min(value = 1, message = "O ID do serviço deve ser maior que 0")
        Long servicoId,

        @NotNull(message = "O ID do estabelecimento é obrigatório")
        @Min(value = 1, message = "O ID do estabelecimento deve ser maior que 0")
        Long estabelecimentoId,

        @NotNull(message = "A data e hora do agendamento são obrigatórias")
        @FutureOrPresent(message = "A data e hora do agendamento devem estar no presente ou futuro")
        LocalDateTime dataHora,

        @NotNull(message = "O status do agendamento é obrigatório")
        StatusAgendamento status

) implements Serializable {
}

