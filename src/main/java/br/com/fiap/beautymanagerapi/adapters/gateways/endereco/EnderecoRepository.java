package br.com.fiap.beautymanagerapi.adapters.gateways.endereco;

import br.com.fiap.beautymanagerapi.entities.EnderecoEntity;

import java.util.Optional;

public interface EnderecoRepository {

    EnderecoEntity salvarEndereco(EnderecoEntity endereco);
    Optional<EnderecoEntity> buscarEnderecoPorId(Long id);

    Optional<EnderecoEntity> findByEstabelecimento_Id(Long id);
}
