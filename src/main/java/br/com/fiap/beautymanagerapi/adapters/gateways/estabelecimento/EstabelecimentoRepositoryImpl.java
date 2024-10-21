package br.com.fiap.beautymanagerapi.adapters.gateways.estabelecimento;

import br.com.fiap.beautymanagerapi.entities.EstabelecimentoEntity;
import br.com.fiap.beautymanagerapi.enums.StatusProfissional;
import br.com.fiap.beautymanagerapi.projection.EstabelecimentoProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EstabelecimentoRepositoryImpl implements EstabelecimentoRepository {


    private final JpaEstabelecimentoRepository estabelecimentoRepository;

    public EstabelecimentoRepositoryImpl(JpaEstabelecimentoRepository estabelecimentoRepository) {
        this.estabelecimentoRepository = estabelecimentoRepository;
    }

    @Override
    public List<EstabelecimentoEntity> buscarPorNome(String nome) {
        return estabelecimentoRepository.findByNomeContainingIgnoreCase(nome);
    }

    @Override
    public List<EstabelecimentoEntity> buscarPorCidade(String cidadeEstabelecimento) {
        return estabelecimentoRepository.findByEndereco_CidadeContainingIgnoreCase(cidadeEstabelecimento);
    }

    @Override
    public List<EstabelecimentoEntity> buscarPorServicoOferecido(String nome) {
        return estabelecimentoRepository.findByServicosOferecidos_NomeContainingIgnoreCase(nome);
    }

    @Override
    public List<EstabelecimentoEntity> buscarPorStatusDoProfissional(StatusProfissional statusProfissional) {
        return estabelecimentoRepository.findByProfissionaisDisponiveis_StatusProfissional(statusProfissional);
    }

    @Override
    public List<EstabelecimentoEntity> buscarPorTipoDoEstabelecimento(String tipoEstabelecimento) {
        return estabelecimentoRepository.findByTipoEstabelecimentoContainingIgnoreCase(tipoEstabelecimento);
    }

    @Override
    public EstabelecimentoEntity criarEstabelecimento(EstabelecimentoEntity estabelecimentoEntity) {
        return estabelecimentoRepository.save(estabelecimentoEntity);
    }

    @Override
    public Optional<EstabelecimentoEntity> buscarPorId(Long id) {
        return estabelecimentoRepository.findById(id);
    }

    @Override
    public Page<EstabelecimentoProjection> findAllPageable(Pageable pageable) {
        return estabelecimentoRepository.findAllPageable(pageable);
    }
}
