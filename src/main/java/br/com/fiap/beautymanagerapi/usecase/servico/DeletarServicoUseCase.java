package br.com.fiap.beautymanagerapi.usecase.servico;

import br.com.fiap.beautymanagerapi.adapters.gateways.servico.ServicoRepository;
import br.com.fiap.beautymanagerapi.constantes.MensagemConstantes;
import br.com.fiap.beautymanagerapi.entities.ServicoEntity;
import br.com.fiap.beautymanagerapi.exception.ServicoNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class DeletarServicoUseCase {

    private final ServicoRepository servicoRepository;

    public DeletarServicoUseCase(ServicoRepository servicoRepository) {
        this.servicoRepository = servicoRepository;
    }

    @Transactional
    public void deletarServico(Long id) {
        Optional<ServicoEntity> servicoOptional = servicoRepository.findServicoById(id);

        if (servicoOptional.isEmpty()) {
            log.error(MensagemConstantes.SERVICO_NAO_ENCONTRADO, id);
            throw new ServicoNotFoundException(MensagemConstantes.EXCEPTION_SERVICO_NAO_ENCONTRADO);
        }

        servicoRepository.deleteServicoById(id);
    }
}
