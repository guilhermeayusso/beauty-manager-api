package br.com.fiap.beautymanagerapi.adapters.gateways.agendamento;

import br.com.fiap.beautymanagerapi.entities.AgendamentoEntity;
import br.com.fiap.beautymanagerapi.enums.StatusAgendamento;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AgendamentoRepositoryImpl implements AgendamentoRepository {

    private final JpaAgendamentoRepository agendamentoRepository;

    public AgendamentoRepositoryImpl(JpaAgendamentoRepository agendamentoRepository) {
        this.agendamentoRepository = agendamentoRepository;
    }

    @Override
    public AgendamentoEntity save(AgendamentoEntity agendamento) {
        return agendamentoRepository.save(agendamento);
    }

    @Override
    public void atualizarAgendamento(Long id, StatusAgendamento status) {
        agendamentoRepository.updateStatus(id, status);
    }

    @Override
    public List<AgendamentoEntity> buscarAgendamentoPorIdEstabelecimento(Long estabelecimentoId) {
        return agendamentoRepository.findByEstabelecimento_Id(estabelecimentoId);
    }

    @Override
    public Optional<AgendamentoEntity> buscarAgendamentoPorId(Long id) {
        return agendamentoRepository.findById(id);
    }

}
