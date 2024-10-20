package br.com.fiap.beautymanagerapi.adapters.gateways.endereco;

import br.com.fiap.beautymanagerapi.entities.EnderecoEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class EnderecoRepositoryImpl implements EnderecoRepository {

    private final JpaEnderecoRepository jpaEnderecoRepository;

    public EnderecoRepositoryImpl(JpaEnderecoRepository jpaEnderecoRepository) {
        this.jpaEnderecoRepository = jpaEnderecoRepository;
    }

    @Override
    public EnderecoEntity salvarEndereco(EnderecoEntity endereco) {
        return jpaEnderecoRepository.save(endereco);
    }

    @Override
    public Optional<EnderecoEntity> buscarEnderecoPorId(Long id) {
        return jpaEnderecoRepository.findById(id);
    }

    @Override
    public Optional<EnderecoEntity> findByEstabelecimento_Id(Long id) {
        return jpaEnderecoRepository.findByEstabelecimento_Id(id);
    }
}
