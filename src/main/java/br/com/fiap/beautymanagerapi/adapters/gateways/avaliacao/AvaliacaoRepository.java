package br.com.fiap.beautymanagerapi.adapters.gateways.avaliacao;

import br.com.fiap.beautymanagerapi.entities.AvaliacaoEntity;

import java.util.List;

public interface AvaliacaoRepository {


    List<AvaliacaoEntity> buscarAvaliacaoPeloIdDoEstabelecimento(Long id);
    List<AvaliacaoEntity> buscarAvaliacaoPeloNomeDoEstabelecimento(String nome);
    AvaliacaoEntity saveAvaliacao(AvaliacaoEntity avaliacao);
}
