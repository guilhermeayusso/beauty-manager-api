package br.com.fiap.beautymanagerapi.usecase.agendamento;

import br.com.fiap.beautymanagerapi.adapters.gateways.agendamento.AgendamentoRepository;
import br.com.fiap.beautymanagerapi.adapters.gateways.cliente.ClienteRepository;
import br.com.fiap.beautymanagerapi.adapters.gateways.estabelecimento.EstabelecimentoRepository;
import br.com.fiap.beautymanagerapi.adapters.gateways.profissional.ProfissionalRepository;
import br.com.fiap.beautymanagerapi.adapters.gateways.servico.ServicoRepository;
import br.com.fiap.beautymanagerapi.constantes.MensagemConstantes;
import br.com.fiap.beautymanagerapi.entities.*;
import br.com.fiap.beautymanagerapi.enums.StatusAgendamento;
import br.com.fiap.beautymanagerapi.enums.StatusProfissional;
import br.com.fiap.beautymanagerapi.exception.ConflictException;
import br.com.fiap.beautymanagerapi.records.agendamento.AgendamentoInputDto;
import br.com.fiap.beautymanagerapi.records.agendamento.AgendamentoOutputDto;
import br.com.fiap.beautymanagerapi.util.EntityValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class CriarAgendamentoUseCase {

    private final AgendamentoRepository agendamentoRepository;
    private final EstabelecimentoRepository estabelecimentoRepository;
    private final ProfissionalRepository profissionalRepository;
    private final ServicoRepository servicoRepository;
    private final ClienteRepository clienteRepository;


    public CriarAgendamentoUseCase(AgendamentoRepository agendamentoRepository, EstabelecimentoRepository estabelecimentoRepository, ProfissionalRepository profissionalRepository, ServicoRepository servicoRepository, ClienteRepository clienteRepository) {
        this.agendamentoRepository = agendamentoRepository;
        this.estabelecimentoRepository = estabelecimentoRepository;
        this.profissionalRepository = profissionalRepository;
        this.servicoRepository = servicoRepository;
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public AgendamentoOutputDto criarAgendamento(AgendamentoInputDto agendamentoInputDto) {

        EstabelecimentoEntity estabelecimento = EntityValidatorUtil.validaEstabelecimento(agendamentoInputDto.estabelecimentoId(), estabelecimentoRepository);
        ProfissionalEntity profissional = EntityValidatorUtil.validaProfissional(agendamentoInputDto.profissionalId(), profissionalRepository);
        ServicoEntity servico = EntityValidatorUtil.validaServico(agendamentoInputDto.servicoId(), servicoRepository);
        ClienteEntity cliente = EntityValidatorUtil.validaCliente(agendamentoInputDto.clienteId(), clienteRepository);

        if (profissional.getStatusProfissional() != StatusProfissional.DISPONIVEL){
            throw new ConflictException(MensagemConstantes.INDISPONIBILIDADE_PROFISSIONAL);
        }

        AgendamentoEntity agendamento = AgendamentoEntity.builder()
                .cliente(cliente)
                .estabelecimento(estabelecimento)
                .servico(servico)
                .profissional(profissional)
                .dataHora(agendamentoInputDto.dataHora())
                .status(StatusAgendamento.ABERTO)
                .build();

        AgendamentoEntity agendamentoSaved = agendamentoRepository.save(agendamento);

        return new AgendamentoOutputDto(agendamentoSaved.getId(),agendamentoSaved.getDataHora(),agendamentoSaved.getStatus());
    }


}
