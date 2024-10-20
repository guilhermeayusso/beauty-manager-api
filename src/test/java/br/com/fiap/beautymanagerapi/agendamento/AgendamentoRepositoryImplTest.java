package br.com.fiap.beautymanagerapi.agendamento;

import br.com.fiap.beautymanagerapi.adapters.gateways.agendamento.AgendamentoRepositoryImpl;
import br.com.fiap.beautymanagerapi.adapters.gateways.estabelecimento.JpaEstabelecimentoRepository;
import br.com.fiap.beautymanagerapi.entities.AgendamentoEntity;
import br.com.fiap.beautymanagerapi.entities.ClienteEntity;
import br.com.fiap.beautymanagerapi.entities.EstabelecimentoEntity;
import br.com.fiap.beautymanagerapi.entities.ProfissionalEntity;
import br.com.fiap.beautymanagerapi.entities.ServicoEntity;
import br.com.fiap.beautymanagerapi.enums.StatusAgendamento;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Sql(scripts = "/scripts/insert-massa-testes.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/scripts/delete-massa-testes.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Import(AgendamentoRepositoryImpl.class)
public class AgendamentoRepositoryImplTest {

    @Autowired
    private AgendamentoRepositoryImpl agendamentoRepositoryImpl;

    @Autowired
    private JpaEstabelecimentoRepository estabelecimentoRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void testeSaveAgendamento() {
        Optional<EstabelecimentoEntity> estabelecimentoOptional = estabelecimentoRepository.findById(11L);
        EstabelecimentoEntity estabelecimento = estabelecimentoOptional.get();

        assertThat(estabelecimento).isNotNull();
        assertThat(estabelecimento.getNome()).isEqualTo("Beleza & Estilo");

        ClienteEntity cliente = testEntityManager.find(ClienteEntity.class, 1L);
        ProfissionalEntity profissional = testEntityManager.find(ProfissionalEntity.class, 11L);
        ServicoEntity servico = testEntityManager.find(ServicoEntity.class, 11L);

        AgendamentoEntity agendamento = AgendamentoEntity.builder()
                .cliente(cliente)
                .profissional(profissional)
                .servico(servico)
                .estabelecimento(estabelecimento)
                .dataHora(LocalDateTime.now())
                .status(StatusAgendamento.ABERTO)
                .build();

        AgendamentoEntity agendamentoSaved = agendamentoRepositoryImpl.save(agendamento);

        assertThat(agendamentoSaved).isNotNull();
        assertThat(agendamentoSaved.getId()).isNotNull();
        assertThat(agendamentoSaved.getStatus()).isEqualTo(StatusAgendamento.ABERTO);
        assertThat(agendamentoSaved.getCliente()).isNotNull();
        assertThat(agendamentoSaved.getProfissional()).isNotNull();
        assertThat(agendamentoSaved.getEstabelecimento()).isNotNull();
    }

    @Test
    public void testeAtualizarAgendamento() {
        // Inserir um novo agendamento para testar a atualização
        Optional<EstabelecimentoEntity> estabelecimentoOptional = estabelecimentoRepository.findById(11L);
        EstabelecimentoEntity estabelecimento = estabelecimentoOptional.get();

        ClienteEntity cliente = testEntityManager.find(ClienteEntity.class, 1L);
        ProfissionalEntity profissional = testEntityManager.find(ProfissionalEntity.class, 11L);
        ServicoEntity servico = testEntityManager.find(ServicoEntity.class, 11L);

        AgendamentoEntity agendamento = AgendamentoEntity.builder()
                .cliente(cliente)
                .profissional(profissional)
                .servico(servico)
                .estabelecimento(estabelecimento)
                .dataHora(LocalDateTime.now())
                .status(StatusAgendamento.ABERTO)
                .build();

        AgendamentoEntity agendamentoSaved = agendamentoRepositoryImpl.save(agendamento);

        // Atualizar status para CONFIRMADO
        agendamentoRepositoryImpl.atualizarAgendamento(agendamentoSaved.getId(), StatusAgendamento.FINALIZADO);

        testEntityManager.clear();

        // Verificar se o status foi atualizado corretamente
        AgendamentoEntity agendamentoUpdated = testEntityManager.find(AgendamentoEntity.class, agendamentoSaved.getId());

        assertThat(agendamentoUpdated.getStatus()).isEqualTo(StatusAgendamento.FINALIZADO);
    }


    @Test
    public void testBuscarAgendamentosPorEstabelecimentoComDoisAgendamentos() {
        testEntityManager.clear();
        // Verificar agendamentos para o estabelecimento com id 11
        List<AgendamentoEntity> agendamentos = agendamentoRepositoryImpl.buscarAgendamentoPorIdEstabelecimento(11L);

        assertThat(agendamentos).hasSize(2); // Deve retornar dois agendamentos
        assertThat(agendamentos.get(0).getEstabelecimento().getId()).isEqualTo(11L);
        assertThat(agendamentos.get(1).getEstabelecimento().getId()).isEqualTo(11L);
    }

    @Test
    public void testBuscarAgendamentosPorEstabelecimentoComUmAgendamento() {
        testEntityManager.clear();
        // Verificar agendamentos para o estabelecimento com id 33
        List<AgendamentoEntity> agendamentos = agendamentoRepositoryImpl.buscarAgendamentoPorIdEstabelecimento(33L);

        assertThat(agendamentos).hasSize(1); // Deve retornar um agendamento
        assertThat(agendamentos.get(0).getEstabelecimento().getId()).isEqualTo(33L);
    }

    @Test
    public void testBuscarAgendamentosPorEstabelecimentoSemAgendamentos() {
        testEntityManager.clear();
        // Verificar se não há agendamentos para um estabelecimento inexistente
        List<AgendamentoEntity> agendamentos = agendamentoRepositoryImpl.buscarAgendamentoPorIdEstabelecimento(99L);

        assertThat(agendamentos).isEmpty(); // Não deve retornar agendamentos
    }
}
