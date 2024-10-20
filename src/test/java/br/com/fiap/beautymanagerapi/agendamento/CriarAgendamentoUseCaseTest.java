package br.com.fiap.beautymanagerapi.agendamento;

import br.com.fiap.beautymanagerapi.constantes.MensagemConstantes;
import br.com.fiap.beautymanagerapi.enums.StatusAgendamento;
import br.com.fiap.beautymanagerapi.records.agendamento.AgendamentoInputDto;
import br.com.fiap.beautymanagerapi.records.agendamento.AgendamentoOutputDto;
import br.com.fiap.beautymanagerapi.usecase.agendamento.CriarAgendamentoUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Sql(scripts = "/scripts/insert-massa-testes.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/scripts/delete-massa-testes.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class CriarAgendamentoUseCaseTest {

    @Autowired
    private CriarAgendamentoUseCase criarAgendamentoUseCase;

    @Test
    public void deveCriarAgendamentoComSucesso() {
        AgendamentoInputDto input = new AgendamentoInputDto(
                1L,
                33L,
                11L,
                22L,
                LocalDateTime.now(),
                null
        );

        AgendamentoOutputDto output = criarAgendamentoUseCase.criarAgendamento(input);

        assertThat(output).isNotNull();
        assertThat(output.status()).isEqualTo(StatusAgendamento.ABERTO); // Verificar status default
    }

    @Test
    public void deveLancarExcecaoQuandoProfissionalNaoDisponivel() {
        AgendamentoInputDto input = new AgendamentoInputDto(
                1L,
                22L,
                11L,
                22L,
                LocalDateTime.now(),
                null
        );

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            criarAgendamentoUseCase.criarAgendamento(input);
        });

        assertThat(exception.getMessage()).isEqualTo(MensagemConstantes.INDISPONIBILIDADE_PROFISSIONAL);
    }
}
