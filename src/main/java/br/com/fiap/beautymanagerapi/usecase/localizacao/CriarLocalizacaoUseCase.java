package br.com.fiap.beautymanagerapi.usecase.localizacao;

import br.com.fiap.beautymanagerapi.adapters.gateways.estabelecimento.EstabelecimentoRepository;
import br.com.fiap.beautymanagerapi.adapters.gateways.localizacao.LocalizacaoRepository;
import br.com.fiap.beautymanagerapi.entities.EstabelecimentoEntity;
import br.com.fiap.beautymanagerapi.entities.LocalizacaoEntity;
import br.com.fiap.beautymanagerapi.records.localizacao.LocalizacaoInputDTO;
import br.com.fiap.beautymanagerapi.records.localizacao.LocalizacaoOutputDTO;
import br.com.fiap.beautymanagerapi.util.EntityValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class CriarLocalizacaoUseCase {

    private final LocalizacaoRepository localizacaoRepository;
    private final EstabelecimentoRepository estabelecimentoRepository;

    public CriarLocalizacaoUseCase(LocalizacaoRepository localizacaoRepository, EstabelecimentoRepository estabelecimentoRepository) {
        this.localizacaoRepository = localizacaoRepository;
        this.estabelecimentoRepository = estabelecimentoRepository;
    }

    @Transactional
    public LocalizacaoOutputDTO criarLocalizacao(LocalizacaoInputDTO localizacaoInputDTO) {

        EstabelecimentoEntity estabelecimento = EntityValidatorUtil.validaEstabelecimento(localizacaoInputDTO.estabelecimentoId(),estabelecimentoRepository);

        LocalizacaoEntity localizacaoEntity = LocalizacaoEntity.builder()
                .latitude(localizacaoInputDTO.latitude())
                .longitude(localizacaoInputDTO.longitude())
                .estabelecimento(estabelecimento)
                .build();

        LocalizacaoEntity localizacaoSaved = localizacaoRepository.saveLocalizacao(localizacaoEntity);

        return new LocalizacaoOutputDTO(localizacaoSaved.getLatitude(), localizacaoSaved.getLongitude(), estabelecimento.getNome());
    }
}
