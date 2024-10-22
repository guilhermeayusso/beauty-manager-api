package br.com.fiap.beautymanagerapi.records.agendamento;

import br.com.fiap.beautymanagerapi.enums.StatusAgendamento;

import java.io.Serializable;
import java.time.LocalDateTime;

public record AgendamentoResponseModel(
        Long agendamentoId,
        LocalDateTime dataHora,
        StatusAgendamento status
) implements Serializable {
}
