package br.com.fiap.beautymanagerapi.adapters.gateways.cliente;

import br.com.fiap.beautymanagerapi.entities.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaClienteRepository  extends JpaRepository<ClienteEntity,Long> {
}
