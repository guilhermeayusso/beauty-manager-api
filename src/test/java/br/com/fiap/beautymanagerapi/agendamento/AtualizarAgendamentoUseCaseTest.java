package br.com.fiap.beautymanagerapi.usecase.agendamento;

import br.com.fiap.beautymanagerapi.constantes.MensagemConstantes;
import br.com.fiap.beautymanagerapi.enums.StatusAgendamento;
import br.com.fiap.beautymanagerapi.records.agendamento.AgendamentoInputDto;
import br.com.fiap.beautymanagerapi.records.agendamento.AgendamentoOutputDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Sql(scripts = "/scripts/insert-massa-testes.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/scripts/delete-massa-testes.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class AtualizarAgendamentoUseCaseTest {

    @Autowired
    private AtualizarAgendamentoUseCase atualizarAgendamentoUseCase;

    @Test
    public void deveAtualizarAgendamentoComSucesso() {
        // Dados para atualização
        AgendamentoInputDto input = new AgendamentoInputDto(
                null,
                null,
                null,
                null,
                null,
                StatusAgendamento.FINALIZADO
        );

        // Atualizando o agendamento para "FINALIZADO"
        AgendamentoOutputDto output = atualizarAgendamentoUseCase.atualizarStatus(
                11L, // ID do agendamento a ser atualizado
                input
        );

        assertThat(output).isNotNull();
        assertThat(output.status()).isEqualTo(StatusAgendamento.FINALIZADO); // Verificando a mudança de status
    }

    @Test
    public void deveLancarExcecaoSeAgendamentoNaoExistir() {
        // Dados para atualização
        AgendamentoInputDto input = new AgendamentoInputDto(
                null,
                null,
                null,
                null,
                null,
                StatusAgendamento.FINALIZADO
        );

        // Tentando atualizar um agendamento inexistente
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            atualizarAgendamentoUseCase.atualizarStatus(
                    100L, // ID inválido
                    input
            );
        });

        assertThat(exception.getMessage()).isEqualTo(MensagemConstantes.EXCEPTION_AGENDAMENTO_NAO_ENCONTRADO);
    }
}
