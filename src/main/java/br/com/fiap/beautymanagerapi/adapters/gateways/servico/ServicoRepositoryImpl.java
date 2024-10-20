package br.com.fiap.beautymanagerapi.adapters.gateways.servico;

import br.com.fiap.beautymanagerapi.entities.ServicoEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ServicoRepositoryImpl implements ServicoRepository {

    private final JpaServicoRepository jpaServicoRepository;

    public ServicoRepositoryImpl(JpaServicoRepository jpaServicoRepository) {
        this.jpaServicoRepository = jpaServicoRepository;
    }

    @Override
    public ServicoEntity saveServico(ServicoEntity servico) {
        return jpaServicoRepository.save(servico);
    }

    @Override
    public Optional<ServicoEntity> findServicoById(Long id) {
        return jpaServicoRepository.findById(id);
    }

    @Override
    public void deleteServicoById(Long id) {
        jpaServicoRepository.deleteById(id);
    }
}
