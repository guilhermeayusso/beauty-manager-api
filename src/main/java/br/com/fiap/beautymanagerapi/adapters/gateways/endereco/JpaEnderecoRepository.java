package br.com.fiap.beautymanagerapi.adapters.gateways.endereco;

import br.com.fiap.beautymanagerapi.entities.EnderecoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaEnderecoRepository extends JpaRepository<EnderecoEntity, Long> {

    Optional<EnderecoEntity> findByEstabelecimento_Id(Long id);
}