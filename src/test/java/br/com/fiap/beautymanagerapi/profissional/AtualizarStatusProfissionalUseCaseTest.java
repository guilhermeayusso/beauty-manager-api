package br.com.fiap.beautymanagerapi.profissional;

import br.com.fiap.beautymanagerapi.adapters.gateways.profissional.ProfissionalRepositoryImpl;
import br.com.fiap.beautymanagerapi.constantes.MensagemConstantes;
import br.com.fiap.beautymanagerapi.entities.ProfissionalEntity;
import br.com.fiap.beautymanagerapi.enums.StatusProfissional;
import br.com.fiap.beautymanagerapi.usecase.profissional.AtualizarStatusProfissionalUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@Sql(scripts = "/scripts/insert-massa-testes.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/scripts/delete-massa-testes.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Import({ProfissionalRepositoryImpl.class, AtualizarStatusProfissionalUseCase.class})
public class AtualizarStatusProfissionalUseCaseTest {

    @Autowired
    private AtualizarStatusProfissionalUseCase atualizarStatusProfissionalUseCase;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void testAtualizarStatusProfissionalComSucesso() {
        // Busca o profissional pelo ID inserido no script de massa
        Long profissionalId = 11L;
        StatusProfissional novoStatus = StatusProfissional.INATIVO;

        // Chama o método de atualização do status
        atualizarStatusProfissionalUseCase.atualizarStatusProfissional(profissionalId, novoStatus);

        // Força o JPA a persistir as alterações e limpa o contexto
        testEntityManager.flush();  // Persiste as alterações no banco de dados
        testEntityManager.clear();  // Limpa o cache de contexto JPA

        // Verifica se o status foi atualizado corretamente
        ProfissionalEntity profissionalAtualizado = testEntityManager.find(ProfissionalEntity.class, profissionalId);
        assertThat(profissionalAtualizado).isNotNull();
        assertThat(profissionalAtualizado.getStatusProfissional()).isEqualTo(novoStatus);
    }

    @Test
    public void testAtualizarStatusProfissionalNaoEncontrado() {
        // Tenta atualizar o status de um profissional inexistente
        Long profissionalIdInvalido = 999L;
        StatusProfissional novoStatus = StatusProfissional.INATIVO;

        // Verifica se a exceção ProfissionalNotFoundException é lançada
        Exception exception = assertThrows(br.com.fiap.beautymanagerapi.exception.ProfissionalNotFoundException.class, () -> {
            atualizarStatusProfissionalUseCase.atualizarStatusProfissional(profissionalIdInvalido, novoStatus);
        });

        // Verifica se a mensagem de exceção é a esperada
        assertThat(exception.getMessage()).isEqualTo(MensagemConstantes.EXCEPTION_PROFISSIONAL_NAO_ENCONTRADO);
    }
}

