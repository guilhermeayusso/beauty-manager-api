package br.com.fiap.beautymanagerapi.adapters.gateways.profissional;

import br.com.fiap.beautymanagerapi.entities.ProfissionalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProfissionalRepository extends JpaRepository<ProfissionalEntity,Long> {
}
