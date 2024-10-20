package br.com.fiap.beautymanagerapi.adapters.gateways.profissional;

import br.com.fiap.beautymanagerapi.entities.ProfissionalEntity;

import java.util.Optional;

public interface ProfissionalRepository {

    ProfissionalEntity saveProfissional(ProfissionalEntity profissional);
    Optional<ProfissionalEntity> buscarProfissionalPorId(Long id);
}
