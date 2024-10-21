package br.com.fiap.beautymanagerapi.adapters.gateways.estabelecimento;

import br.com.fiap.beautymanagerapi.entities.EstabelecimentoEntity;
import br.com.fiap.beautymanagerapi.enums.StatusProfissional;
import br.com.fiap.beautymanagerapi.projection.EstabelecimentoProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface EstabelecimentoRepository {

    List<EstabelecimentoEntity> buscarPorNome(String nome);
    List<EstabelecimentoEntity> buscarPorCidade(String cidadeEstabelecimento);
    List<EstabelecimentoEntity> buscarPorServicoOferecido(String nome);
    List<EstabelecimentoEntity> buscarPorStatusDoProfissional(StatusProfissional statusProfissional);
    List<EstabelecimentoEntity> buscarPorTipoDoEstabelecimento(String tipoEstabelecimento);
    EstabelecimentoEntity criarEstabelecimento (EstabelecimentoEntity estabelecimentoEntity);
    Optional<EstabelecimentoEntity> buscarPorId(Long id);
    Page<EstabelecimentoProjection> findAllPageable (Pageable pageable);
}
