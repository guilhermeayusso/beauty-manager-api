package br.com.fiap.beautymanagerapi.usecase.agendamento;

import br.com.fiap.beautymanagerapi.adapters.gateways.agendamento.AgendamentoRepository;
import br.com.fiap.beautymanagerapi.adapters.gateways.estabelecimento.EstabelecimentoRepository;
import br.com.fiap.beautymanagerapi.entities.AgendamentoEntity;
import br.com.fiap.beautymanagerapi.entities.EstabelecimentoEntity;
import br.com.fiap.beautymanagerapi.records.agendamento.AgendamentoOutputDto;
import br.com.fiap.beautymanagerapi.util.EntityValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BuscarAgendamentoUseCase {

    private final AgendamentoRepository agendamentoRepository;
    private final EstabelecimentoRepository estabelecimentoRepository;

    public BuscarAgendamentoUseCase(AgendamentoRepository agendamentoRepository, EstabelecimentoRepository estabelecimentoRepository) {
        this.agendamentoRepository = agendamentoRepository;
        this.estabelecimentoRepository = estabelecimentoRepository;
    }

    public List<AgendamentoOutputDto> buscarAgendamentosEstabelecimento(long idEstabelecimento) {

        EstabelecimentoEntity estabelecimento = EntityValidatorUtil.validaEstabelecimento(idEstabelecimento,estabelecimentoRepository);

        List<AgendamentoEntity> agendamentoList = agendamentoRepository.buscarAgendamentoPorIdEstabelecimento(idEstabelecimento);

        return agendamentoList.stream()
                .map(agendamentoEntity -> new AgendamentoOutputDto(
                        agendamentoEntity.getId(),
                        agendamentoEntity.getDataHora(),
                        agendamentoEntity.getStatus()
                )).collect(Collectors.toList());

    }
}
