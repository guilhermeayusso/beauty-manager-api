package br.com.fiap.beautymanagerapi.usecase.servico;

import br.com.fiap.beautymanagerapi.adapters.gateways.servico.ServicoRepository;
import br.com.fiap.beautymanagerapi.constantes.MensagemConstantes;
import br.com.fiap.beautymanagerapi.entities.ServicoEntity;
import br.com.fiap.beautymanagerapi.exception.ServicoNotFoundException;
import br.com.fiap.beautymanagerapi.records.servico.ServicoOutputDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class BuscarServicoUseCase {

    private final ServicoRepository servicoRepository;

    public BuscarServicoUseCase(ServicoRepository servicoRepository) {
        this.servicoRepository = servicoRepository;
    }

    public ServicoOutputDTO buscarServicoPorId(Long id) {
        Optional<ServicoEntity> servicoOptional = servicoRepository.findServicoById(id);

        ServicoEntity servico = servicoOptional.orElseThrow(() -> {
            log.error(MensagemConstantes.SERVICO_NAO_ENCONTRADO, id);
            return new ServicoNotFoundException(MensagemConstantes.EXCEPTION_SERVICO_NAO_ENCONTRADO);
        });

        return new ServicoOutputDTO(
                servico.getId(),
                servico.getNome(),
                servico.getDescricao(),
                servico.getPreco()
        );
    }
}
