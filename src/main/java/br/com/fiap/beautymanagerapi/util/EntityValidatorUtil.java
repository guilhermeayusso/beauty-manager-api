package br.com.fiap.beautymanagerapi.util;

import br.com.fiap.beautymanagerapi.adapters.gateways.cliente.ClienteRepository;
import br.com.fiap.beautymanagerapi.adapters.gateways.estabelecimento.EstabelecimentoRepository;
import br.com.fiap.beautymanagerapi.adapters.gateways.profissional.ProfissionalRepository;
import br.com.fiap.beautymanagerapi.adapters.gateways.servico.ServicoRepository;
import br.com.fiap.beautymanagerapi.constantes.MensagemConstantes;
import br.com.fiap.beautymanagerapi.entities.ClienteEntity;
import br.com.fiap.beautymanagerapi.entities.EstabelecimentoEntity;
import br.com.fiap.beautymanagerapi.entities.ProfissionalEntity;
import br.com.fiap.beautymanagerapi.entities.ServicoEntity;
import br.com.fiap.beautymanagerapi.exception.ClienteNotFoundException;
import br.com.fiap.beautymanagerapi.exception.EstabelecimentoNotFoundException;
import br.com.fiap.beautymanagerapi.exception.ProfissionalNotFoundException;
import br.com.fiap.beautymanagerapi.exception.ServicoNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EntityValidatorUtil {

    private static final Logger log = LoggerFactory.getLogger(EntityValidatorUtil.class);

    // Construtor privado para evitar instância da classe
    private EntityValidatorUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    // Valida a existência de Estabelecimento
    public static EstabelecimentoEntity validaEstabelecimento(Long estabelecimentoId, EstabelecimentoRepository estabelecimentoRepository) {
        Optional<EstabelecimentoEntity> estabelecimentoOptional = estabelecimentoRepository.buscarPorId(estabelecimentoId);
        return estabelecimentoOptional.orElseThrow(() -> {
            log.error(MensagemConstantes.ESTABELECIMENTO_NAO_ENCONTRADO, estabelecimentoId);
            return new EstabelecimentoNotFoundException(MensagemConstantes.EXCEPTION_ESTABELECIMENTO_NAO_ENCONTRADO);
        });
    }

    // Valida a existência de Profissional
    public static ProfissionalEntity validaProfissional(Long profissionalId, ProfissionalRepository profissionalRepository) {
        Optional<ProfissionalEntity> profissionalOptional = profissionalRepository.buscarProfissionalPorId(profissionalId);
        return profissionalOptional.orElseThrow(() -> {
            log.error(MensagemConstantes.PROFISSIONAL_NAO_ENCONTRADO, profissionalId);
            return new ProfissionalNotFoundException(MensagemConstantes.EXCEPTION_PROFISSIONAL_NAO_ENCONTRADO);
        });
    }

    // Valida a existência de Serviço
    public static ServicoEntity validaServico(Long servicoId, ServicoRepository servicoRepository) {
        Optional<ServicoEntity> servicoOptional = servicoRepository.findServicoById(servicoId);
        return servicoOptional.orElseThrow(() -> {
            log.error(MensagemConstantes.SERVICO_NAO_ENCONTRADO, servicoId);
            return new ServicoNotFoundException(MensagemConstantes.EXCEPTION_SERVICO_NAO_ENCONTRADO);
        });
    }

    public static List<ProfissionalEntity> validaListaDeProfissionais(List<Long> profissionaisIds, ProfissionalRepository profissionalRepository) {
        return profissionaisIds.stream()
                .map(id -> profissionalRepository.buscarProfissionalPorId(id)
                        .orElseThrow(() -> new ProfissionalNotFoundException(MensagemConstantes.EXCEPTION_PROFISSIONAL_NAO_ENCONTRADO)))
                .collect(Collectors.toList());
    }

    public static ClienteEntity validaCliente(Long id, ClienteRepository clienteRepository) {
        Optional<ClienteEntity> clienteOptional = clienteRepository.buscarClientePorId(id);
        return clienteOptional.orElseThrow(() -> {
            log.error(MensagemConstantes.CLIENTE_NAO_ENCONTRADO,id);
            return new ClienteNotFoundException(MensagemConstantes.EXCEPTION_CLIENTE_NAO_ENCONTRADO);
        });
    }
}

