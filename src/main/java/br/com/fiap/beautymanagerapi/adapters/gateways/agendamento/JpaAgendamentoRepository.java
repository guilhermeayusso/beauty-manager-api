package br.com.fiap.beautymanagerapi.adapters.gateways.agendamento;

import br.com.fiap.beautymanagerapi.entities.AgendamentoEntity;
import br.com.fiap.beautymanagerapi.enums.StatusAgendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface JpaAgendamentoRepository extends JpaRepository<AgendamentoEntity, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE AgendamentoEntity a SET a.status = :status WHERE a.id = :id")
    void updateStatus(Long id, StatusAgendamento status);

    // Método customizado para buscar agendamentos por estabelecimento
    List<AgendamentoEntity> findByEstabelecimento_Id(Long estabelecimentoId);

}