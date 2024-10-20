package br.com.fiap.beautymanagerapi.adapters.gateways.avaliacao;

import br.com.fiap.beautymanagerapi.entities.AvaliacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaAvaliacaoRepository extends JpaRepository<AvaliacaoEntity,Long> {

    List<AvaliacaoEntity> findByEstabelecimentoEntity_NomeContainingIgnoreCase(String nome);
    List<AvaliacaoEntity> findByEstabelecimentoEntity_Id(Long id);
}
