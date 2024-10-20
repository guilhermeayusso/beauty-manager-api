package br.com.fiap.beautymanagerapi.usecase.agendamento;

import br.com.fiap.beautymanagerapi.adapters.gateways.agendamento.AgendamentoRepository;
import br.com.fiap.beautymanagerapi.adapters.gateways.estabelecimento.EstabelecimentoRepository;
import br.com.fiap.beautymanagerapi.constantes.MensagemConstantes;
import br.com.fiap.beautymanagerapi.entities.AgendamentoEntity;
import br.com.fiap.beautymanagerapi.exception.AgendamentoNotFoundException;
import br.com.fiap.beautymanagerapi.records.agendamento.AgendamentoInputDto;
import br.com.fiap.beautymanagerapi.records.agendamento.AgendamentoOutputDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class AtualizarAgendamentoUseCase {

    private final AgendamentoRepository agendamentoRepository;


    public AtualizarAgendamentoUseCase(AgendamentoRepository agendamentoRepository, EstabelecimentoRepository estabelecimentoRepository) {
        this.agendamentoRepository = agendamentoRepository;
    }

    @Transactional
    public AgendamentoOutputDto atualizarStatus(Long id, AgendamentoInputDto agendamentoInputDto) {

        Optional<AgendamentoEntity> agendamentoOptional = agendamentoRepository.buscarAgendamentoPorId(id);

        AgendamentoEntity agendamento = agendamentoOptional.orElseThrow(()->{
            log.error(MensagemConstantes.AGENDAMENTO_NAO_ENCONTRADO,id);
            return new AgendamentoNotFoundException(MensagemConstantes.EXCEPTION_AGENDAMENTO_NAO_ENCONTRADO);
        });

        agendamento.setStatus(agendamentoInputDto.status());
        AgendamentoEntity agendamentoSaved = agendamentoRepository.save(agendamento);

        return new AgendamentoOutputDto(agendamentoSaved.getId(),agendamentoSaved.getDataHora(),agendamentoSaved.getStatus());
    }
}
