package br.com.fiap.beautymanagerapi.usecase.profissional;

import br.com.fiap.beautymanagerapi.adapters.gateways.estabelecimento.EstabelecimentoRepository;
import br.com.fiap.beautymanagerapi.adapters.gateways.profissional.ProfissionalRepository;
import br.com.fiap.beautymanagerapi.entities.EstabelecimentoEntity;
import br.com.fiap.beautymanagerapi.entities.ProfissionalEntity;
import br.com.fiap.beautymanagerapi.records.profissional.ProfissionalInputDTO;
import br.com.fiap.beautymanagerapi.records.profissional.ProfissionalOutputDTO;
import br.com.fiap.beautymanagerapi.util.EntityValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class CriarProfissionalUseCase {

    private final ProfissionalRepository profissionalRepository;
    private final EstabelecimentoRepository estabelecimentoRepository;

    public CriarProfissionalUseCase(ProfissionalRepository profissionalRepository, EstabelecimentoRepository estabelecimentoRepository) {
        this.profissionalRepository = profissionalRepository;
        this.estabelecimentoRepository = estabelecimentoRepository;
    }

    @Transactional
    public ProfissionalOutputDTO criarProfissional(ProfissionalInputDTO profissionalInputDTO) {

        EstabelecimentoEntity estabelecimento = EntityValidatorUtil.validaEstabelecimento(profissionalInputDTO.estabelecimento_id(),estabelecimentoRepository);

        ProfissionalEntity profissional = ProfissionalEntity.builder()
                .nome(profissionalInputDTO.nome())
                .especialidades(profissionalInputDTO.especialidades())
                .statusProfissional(profissionalInputDTO.statusProfissional())
                .estabelecimento(estabelecimento)
                .build();

        ProfissionalEntity profissionalSaved = profissionalRepository.saveProfissional(profissional);

        ProfissionalOutputDTO outputDTO = new ProfissionalOutputDTO(
                profissionalSaved.getId(),
                profissionalSaved.getNome(),
                profissionalSaved.getEspecialidades(),
                profissionalSaved.getStatusProfissional(),
                profissionalSaved.getEstabelecimento().getNome(),
                profissionalSaved.getEstabelecimento().getEndereco().getLogradouro()
        );

        return outputDTO;
    }
}
