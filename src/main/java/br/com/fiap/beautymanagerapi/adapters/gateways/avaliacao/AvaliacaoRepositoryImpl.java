package br.com.fiap.beautymanagerapi.adapters.gateways.avaliacao;

import br.com.fiap.beautymanagerapi.entities.AvaliacaoEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AvaliacaoRepositoryImpl implements AvaliacaoRepository {

    private final JpaAvaliacaoRepository jpaAvaliacaoRepository;

    public AvaliacaoRepositoryImpl(JpaAvaliacaoRepository jpaAvaliacaoRepository) {
        this.jpaAvaliacaoRepository = jpaAvaliacaoRepository;
    }


    @Override
    public List<AvaliacaoEntity> buscarAvaliacaoPeloIdDoEstabelecimento(Long id) {
        return jpaAvaliacaoRepository.findByEstabelecimentoEntity_Id(id);
    }

    @Override
    public List<AvaliacaoEntity> buscarAvaliacaoPeloNomeDoEstabelecimento(String nome) {
        return jpaAvaliacaoRepository.findByEstabelecimentoEntity_NomeContainingIgnoreCase(nome);
    }

    @Override
    public AvaliacaoEntity saveAvaliacao(AvaliacaoEntity avaliacao) {
        return jpaAvaliacaoRepository.save(avaliacao);
    }
}
