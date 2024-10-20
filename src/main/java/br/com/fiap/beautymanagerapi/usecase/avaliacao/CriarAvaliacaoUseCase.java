package br.com.fiap.beautymanagerapi.usecase.avaliacao;

import br.com.fiap.beautymanagerapi.adapters.gateways.avaliacao.AvaliacaoRepository;
import br.com.fiap.beautymanagerapi.adapters.gateways.estabelecimento.EstabelecimentoRepository;
import br.com.fiap.beautymanagerapi.constantes.MensagemConstantes;
import br.com.fiap.beautymanagerapi.entities.AvaliacaoEntity;
import br.com.fiap.beautymanagerapi.entities.EstabelecimentoEntity;
import br.com.fiap.beautymanagerapi.exception.EstabelecimentoNotFoundException;
import br.com.fiap.beautymanagerapi.records.avaliacao.AvaliacaoInputDTO;
import br.com.fiap.beautymanagerapi.records.avaliacao.AvaliacaoOutputDTO;
import br.com.fiap.beautymanagerapi.util.EntityValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
public class CriarAvaliacaoUseCase {

    private final AvaliacaoRepository avaliacaoRepository;
    private final EstabelecimentoRepository estabelecimentoRepository;

    public CriarAvaliacaoUseCase(AvaliacaoRepository avaliacaoRepository, EstabelecimentoRepository estabelecimentoRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
        this.estabelecimentoRepository = estabelecimentoRepository;
    }

    @Transactional
    public AvaliacaoOutputDTO criarAvaliacao(AvaliacaoInputDTO avaliacaoInputDTO) {

        EstabelecimentoEntity estabelecimento = EntityValidatorUtil.validaEstabelecimento(avaliacaoInputDTO.estabelecimento_id(), estabelecimentoRepository);

        AvaliacaoEntity avaliacaoEntity = AvaliacaoEntity.builder()
                .autor(avaliacaoInputDTO.autor())
                .nota(avaliacaoInputDTO.nota())
                .comentario(avaliacaoInputDTO.comentario())
                .estabelecimentoEntity(estabelecimento)
                .dataAvaliacao(LocalDateTime.now())
                .build();


        AvaliacaoEntity avaliacaoSaved = avaliacaoRepository.saveAvaliacao(avaliacaoEntity);

        return new AvaliacaoOutputDTO(avaliacaoSaved.getAutor(), avaliacaoSaved.getNota(), avaliacaoSaved.getComentario());
    }

}
