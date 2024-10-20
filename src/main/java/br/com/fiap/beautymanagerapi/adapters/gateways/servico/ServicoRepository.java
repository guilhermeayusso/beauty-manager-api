package br.com.fiap.beautymanagerapi.adapters.gateways.servico;

import br.com.fiap.beautymanagerapi.entities.ServicoEntity;

import java.util.Optional;

public interface ServicoRepository {

    ServicoEntity saveServico(ServicoEntity servico);
    Optional<ServicoEntity> findServicoById(Long id);
    void deleteServicoById(Long id);
}
