package br.com.fiap.beautymanagerapi.adapters.gateways.agendamento;

import br.com.fiap.beautymanagerapi.entities.AgendamentoEntity;
import br.com.fiap.beautymanagerapi.enums.StatusAgendamento;

import java.util.List;
import java.util.Optional;

public interface AgendamentoRepository {

    AgendamentoEntity save(AgendamentoEntity agendamento);
    Optional<AgendamentoEntity> buscarAgendamentoPorId(Long id);
    void atualizarAgendamento(Long id, StatusAgendamento status);
    List<AgendamentoEntity> buscarAgendamentoPorIdEstabelecimento(Long estabelecimentoId);
}
