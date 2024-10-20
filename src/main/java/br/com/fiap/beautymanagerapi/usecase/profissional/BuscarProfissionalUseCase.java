package br.com.fiap.beautymanagerapi.usecase.profissional;

import br.com.fiap.beautymanagerapi.adapters.gateways.profissional.ProfissionalRepository;
import br.com.fiap.beautymanagerapi.constantes.MensagemConstantes;
import br.com.fiap.beautymanagerapi.entities.ProfissionalEntity;
import br.com.fiap.beautymanagerapi.exception.ProfissionalNotFoundException;
import br.com.fiap.beautymanagerapi.records.profissional.ProfissionalOutputDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class BuscarProfissionalUseCase {

    private final ProfissionalRepository profissionalRepository;


    public BuscarProfissionalUseCase(ProfissionalRepository profissionalRepository) {
        this.profissionalRepository = profissionalRepository;
    }

    public ProfissionalOutputDTO buscarProfissional(Long id) {

        Optional<ProfissionalEntity> profissionalOptional = profissionalRepository.buscarProfissionalPorId(id);

        ProfissionalEntity profissional = profissionalOptional.orElseThrow(() -> {
                    log.error(MensagemConstantes.PROFISSIONAL_NAO_ENCONTRADO, id);
                    throw new ProfissionalNotFoundException(MensagemConstantes.EXCEPTION_PROFISSIONAL_NAO_ENCONTRADO);
                }
        );

        ProfissionalOutputDTO outputDTO = new ProfissionalOutputDTO(
                profissional.getId(),
                profissional.getNome(),
                profissional.getEspecialidades(),
                profissional.getStatusProfissional(),
                profissional.getEstabelecimento().getNome(),
                profissional.getEstabelecimento().getEndereco().getLogradouro()
        );


        return outputDTO;
    }
}
