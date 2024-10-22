package br.com.fiap.beautymanagerapi.records.agendamento;

import br.com.fiap.beautymanagerapi.enums.StatusAgendamento;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public record AgendamentoRequestModelStatus(
        @NotNull(message = "O status do agendamento é obrigatório")
        StatusAgendamento status
) implements Serializable {
}
