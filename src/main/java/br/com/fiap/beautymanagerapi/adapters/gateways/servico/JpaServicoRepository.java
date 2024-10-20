package br.com.fiap.beautymanagerapi.adapters.gateways.servico;

import br.com.fiap.beautymanagerapi.entities.ServicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaServicoRepository extends JpaRepository<ServicoEntity, Long> {
}
