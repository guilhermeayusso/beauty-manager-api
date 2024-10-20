package br.com.fiap.beautymanagerapi.adapters.gateways.estabelecimento;

import br.com.fiap.beautymanagerapi.entities.EstabelecimentoEntity;
import br.com.fiap.beautymanagerapi.enums.StatusProfissional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaEstabelecimentoRepository extends JpaRepository<EstabelecimentoEntity,Long> {


    List<EstabelecimentoEntity> findByNomeContainingIgnoreCase(String nome);

    List<EstabelecimentoEntity> findByEndereco_CidadeContainingIgnoreCase(String cidadeEstabelecimento);

    List<EstabelecimentoEntity> findByServicosOferecidos_NomeContainingIgnoreCase(String nome);

    List<EstabelecimentoEntity> findByProfissionaisDisponiveis_StatusProfissional(StatusProfissional statusProfissional);

    @Query("SELECT r FROM EstabelecimentoEntity r WHERE LOWER(r.tipoEstabelecimento) LIKE %:tipoEstabelecimento%")
    List<EstabelecimentoEntity> findByTipoEstabelecimentoContainingIgnoreCase(@Param("tipoEstabelecimento") String tipoEstabelecimento);

}
