package br.com.fiap.beautymanagerapi.adapters.gateways.cliente;

import br.com.fiap.beautymanagerapi.entities.ClienteEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository {

    ClienteEntity save(ClienteEntity cliente);
    Optional<ClienteEntity> buscarClientePorId(Long id);


}
