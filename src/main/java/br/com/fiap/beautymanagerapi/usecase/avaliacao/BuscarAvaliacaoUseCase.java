package br.com.fiap.beautymanagerapi.usecase.avaliacao;

import br.com.fiap.beautymanagerapi.adapters.gateways.avaliacao.AvaliacaoRepository;
import br.com.fiap.beautymanagerapi.constantes.MensagemConstantes;
import br.com.fiap.beautymanagerapi.entities.AvaliacaoEntity;
import br.com.fiap.beautymanagerapi.exception.AvaliacaoNotFoundException;
import br.com.fiap.beautymanagerapi.records.avaliacao.AvaliacaoOutputDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class BuscarAvaliacaoUseCase {

    private final AvaliacaoRepository avaliacaoRepository;

    public BuscarAvaliacaoUseCase(AvaliacaoRepository avaliacaoRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
    }

    public List<AvaliacaoOutputDTO> buscarAvaliacaoPorEstabelecimentoId(Long id) {

        List<AvaliacaoEntity> avaliacaoList = avaliacaoRepository.buscarAvaliacaoPeloIdDoEstabelecimento(id);

        avaliacaoList.stream().findFirst().orElseThrow(() -> {
                    log.error("Avaliacao com o ID do Estabelecimento={} não encontrado na base!", id);
                    return new AvaliacaoNotFoundException(MensagemConstantes.EXCEPTION_AVALIACAO_NAO_ENCONTRADO);
                }
        );

        List<AvaliacaoOutputDTO> dtoList = new ArrayList<>();

        avaliacaoList.forEach(avaliacaoEntity -> dtoList.add(new AvaliacaoOutputDTO(avaliacaoEntity.getAutor(),
                avaliacaoEntity.getNota(), avaliacaoEntity.getComentario())));

        return dtoList;

    }

    public List<AvaliacaoOutputDTO> buscarAvaliacaoPorEstabelecimentoComNome(String nome) {

        List<AvaliacaoEntity> avaliacaoList = avaliacaoRepository.buscarAvaliacaoPeloNomeDoEstabelecimento(nome);

        avaliacaoList.stream().findFirst().orElseThrow(() -> {
                    log.error("Avaliacao com o nome do Estabelecimento={} não encontrado na base!", nome);
                    return new AvaliacaoNotFoundException(MensagemConstantes.EXCEPTION_AVALIACAO_NAO_ENCONTRADO);
                }
        );

        List<AvaliacaoOutputDTO> dtoList = new ArrayList<>();

        avaliacaoList.forEach(avaliacaoEntity -> dtoList.add(new AvaliacaoOutputDTO(avaliacaoEntity.getAutor(),
                avaliacaoEntity.getNota(), avaliacaoEntity.getComentario())));

        return dtoList;

    }
}
