package br.com.fiap.beautymanagerapi.servico;

import br.com.fiap.beautymanagerapi.adapters.gateways.servico.ServicoRepositoryImpl;
import br.com.fiap.beautymanagerapi.constantes.MensagemConstantes;
import br.com.fiap.beautymanagerapi.entities.ServicoEntity;
import br.com.fiap.beautymanagerapi.exception.ServicoNotFoundException;
import br.com.fiap.beautymanagerapi.records.servico.ServicoOutputDTO;
import br.com.fiap.beautymanagerapi.usecase.servico.BuscarServicoUseCase;
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
@Import({BuscarServicoUseCase.class, ServicoRepositoryImpl.class})
public class BuscarServicoUseCaseTest {

    @Autowired
    private BuscarServicoUseCase buscarServicoUseCase;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void testBuscarServicoComSucesso() {
        // Busca o serviço válido a partir do script de massa
        ServicoEntity servico = testEntityManager.find(ServicoEntity.class, 11L);

        // Verifica se o serviço foi encontrado
        assertThat(servico).isNotNull();

        // Chama o caso de uso para buscar o serviço
        ServicoOutputDTO outputDTO = buscarServicoUseCase.buscarServicoPorId(servico.getId());

        // Verifica se os dados retornados estão corretos
        assertThat(outputDTO.id()).isEqualTo(servico.getId());
        assertThat(outputDTO.nome()).isEqualTo(servico.getNome());
        assertThat(outputDTO.descricao()).isEqualTo(servico.getDescricao());
        assertThat(outputDTO.preco()).isEqualTo(servico.getPreco());
    }

    @Test
    public void testBuscarServicoInvalido() {
        // Tenta buscar um serviço inexistente
        Long invalidId = 999L;

        // Verifica se a exceção ServicoNotFoundException é lançada
        Exception exception = assertThrows(ServicoNotFoundException.class, () -> {
            buscarServicoUseCase.buscarServicoPorId(invalidId);
        });

        // Verifica se a mensagem de exceção é a esperada
        assertThat(exception.getMessage()).isEqualTo(MensagemConstantes.EXCEPTION_SERVICO_NAO_ENCONTRADO);
    }
}
