package br.com.fiap.beautymanagerapi.usecase.profissional;

import br.com.fiap.beautymanagerapi.adapters.gateways.profissional.ProfissionalRepository;
import br.com.fiap.beautymanagerapi.constantes.MensagemConstantes;
import br.com.fiap.beautymanagerapi.entities.ProfissionalEntity;
import br.com.fiap.beautymanagerapi.enums.StatusProfissional;
import br.com.fiap.beautymanagerapi.exception.ProfissionalNotFoundException;
import br.com.fiap.beautymanagerapi.records.profissional.ProfissionalOutputDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class AtualizarStatusProfissionalUseCase {

    private final ProfissionalRepository profissionalRepository;


    public AtualizarStatusProfissionalUseCase(ProfissionalRepository profissionalRepository) {
        this.profissionalRepository = profissionalRepository;
    }

    @Transactional
    public ProfissionalOutputDTO atualizarStatusProfissional(Long profissionalId, StatusProfissional novoStatus) {

        // Busca o profissional pelo ID
        Optional<ProfissionalEntity> profissionalOptional = profissionalRepository.buscarProfissionalPorId(profissionalId);

        // Lança uma exceção caso o profissional não seja encontrado
        ProfissionalEntity profissional = profissionalOptional.orElseThrow(() -> {
            log.error(MensagemConstantes.PROFISSIONAL_NAO_ENCONTRADO, profissionalId);
            return new ProfissionalNotFoundException(MensagemConstantes.EXCEPTION_PROFISSIONAL_NAO_ENCONTRADO);
        });

        // Atualiza o status do profissional
        profissional.setStatusProfissional(novoStatus);

        // Salva o profissional atualizado
        ProfissionalEntity profissionalAtualizado = profissionalRepository.saveProfissional(profissional);

        // Constrói o DTO de saída com os dados atualizados
        return new ProfissionalOutputDTO(
                profissionalAtualizado.getId(),
                profissionalAtualizado.getNome(),
                profissionalAtualizado.getEspecialidades(),
                profissionalAtualizado.getStatusProfissional(),
                profissionalAtualizado.getEstabelecimento().getNome(),
                profissionalAtualizado.getEstabelecimento().getEndereco().getLogradouro()
        );
    }
}

