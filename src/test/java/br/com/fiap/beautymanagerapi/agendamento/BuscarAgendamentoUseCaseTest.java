package br.com.fiap.beautymanagerapi.agendamento;

import br.com.fiap.beautymanagerapi.records.agendamento.AgendamentoOutputDto;
import br.com.fiap.beautymanagerapi.usecase.agendamento.BuscarAgendamentoUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Sql(scripts = "/scripts/insert-massa-testes.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/scripts/delete-massa-testes.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class BuscarAgendamentoUseCaseTest {

    @Autowired
    private BuscarAgendamentoUseCase buscarAgendamentoUseCase;

    @Test
    public void deveBuscarAgendamentosPorEstabelecimentoComSucesso() {
        List<AgendamentoOutputDto> agendamentos = buscarAgendamentoUseCase.buscarAgendamentosEstabelecimento(11L);

        assertThat(agendamentos).isNotEmpty();
        assertThat(agendamentos.size()).isEqualTo(2); // Exemplo de validação, supondo que o Estabelecimento 11 tem 2 agendamentos
    }

    @Test
    public void deveRetornarListaVaziaSeNaoExistiremAgendamentos() {
        List<AgendamentoOutputDto> agendamentos = buscarAgendamentoUseCase.buscarAgendamentosEstabelecimento(44L);

        assertThat(agendamentos).isEmpty();
    }
}
