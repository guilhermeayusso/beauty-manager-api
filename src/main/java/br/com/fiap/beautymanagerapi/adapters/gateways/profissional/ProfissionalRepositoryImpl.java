package br.com.fiap.beautymanagerapi.adapters.gateways.profissional;

import br.com.fiap.beautymanagerapi.entities.ProfissionalEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ProfissionalRepositoryImpl implements ProfissionalRepository {

    private final JpaProfissionalRepository jpaProfissionalRepository;

    public ProfissionalRepositoryImpl(JpaProfissionalRepository jpaProfissionalRepository) {
        this.jpaProfissionalRepository = jpaProfissionalRepository;
    }

    @Override
    public ProfissionalEntity saveProfissional(ProfissionalEntity profissional) {
        return jpaProfissionalRepository.save(profissional);
    }

    @Override
    public Optional<ProfissionalEntity> buscarProfissionalPorId(Long id) {
        return jpaProfissionalRepository.findById(id);
    }
}
