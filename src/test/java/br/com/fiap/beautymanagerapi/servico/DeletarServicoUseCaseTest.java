package br.com.fiap.beautymanagerapi.servico;

import br.com.fiap.beautymanagerapi.adapters.gateways.servico.ServicoRepositoryImpl;
import br.com.fiap.beautymanagerapi.constantes.MensagemConstantes;
import br.com.fiap.beautymanagerapi.entities.ServicoEntity;
import br.com.fiap.beautymanagerapi.exception.ServicoNotFoundException;
import br.com.fiap.beautymanagerapi.usecase.servico.DeletarServicoUseCase;
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
@Import({DeletarServicoUseCase.class, ServicoRepositoryImpl.class})
public class DeletarServicoUseCaseTest {

    @Autowired
    private DeletarServicoUseCase deletarServicoUseCase;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void testDeletarServicoComSucesso() {
        // Busca o serviço válido a partir do script de massa
        ServicoEntity servico = testEntityManager.find(ServicoEntity.class, 11L);

        // Verifica se o serviço foi encontrado
        assertThat(servico).isNotNull();

        // Chama o caso de uso para deletar o serviço
        deletarServicoUseCase.deletarServico(servico.getId());

        // Força o JPA a persistir as alterações e limpa o contexto
        testEntityManager.flush();
        testEntityManager.clear();

        // Verifica se o serviço foi removido
        ServicoEntity servicoDeletado = testEntityManager.find(ServicoEntity.class, servico.getId());
        assertThat(servicoDeletado).isNull();
    }

    @Test
    public void testDeletarServicoInexistente() {
        // Tenta deletar um serviço com ID inexistente
        Long servicoIdInexistente = 999L;

        // Verifica se a exceção ServicoNotFoundException é lançada
        Exception exception = assertThrows(ServicoNotFoundException.class, () -> {
            deletarServicoUseCase.deletarServico(servicoIdInexistente);
        });

        // Verifica se a mensagem de exceção é a esperada
        assertThat(exception.getMessage()).isEqualTo(MensagemConstantes.EXCEPTION_SERVICO_NAO_ENCONTRADO);
    }
}
