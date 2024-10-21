package br.com.fiap.beautymanagerapi.profissional;

import br.com.fiap.beautymanagerapi.adapters.gateways.estabelecimento.EstabelecimentoRepositoryImpl;
import br.com.fiap.beautymanagerapi.adapters.gateways.profissional.ProfissionalRepositoryImpl;
import br.com.fiap.beautymanagerapi.constantes.MensagemConstantes;
import br.com.fiap.beautymanagerapi.entities.ProfissionalEntity;
import br.com.fiap.beautymanagerapi.exception.ProfissionalNotFoundException;
import br.com.fiap.beautymanagerapi.records.profissional.ProfissionalOutputDTO;
import br.com.fiap.beautymanagerapi.usecase.profissional.BuscarProfissionalUseCase;
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
@Import({BuscarProfissionalUseCase.class, ProfissionalRepositoryImpl.class, EstabelecimentoRepositoryImpl.class})
public class BuscarProfissionalUseCaseTest {

    @Autowired
    private BuscarProfissionalUseCase buscarProfissionalUseCase;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void testBuscarProfissionalComSucesso() {
        // Busca o profissional válido a partir do script de massa
        ProfissionalEntity profissional = testEntityManager.find(ProfissionalEntity.class, 11L);

        // Verifica se o profissional foi encontrado
        assertThat(profissional).isNotNull();

        // Chama o caso de uso para buscar o profissional
        ProfissionalOutputDTO outputDTO = buscarProfissionalUseCase.buscarProfissional(profissional.getId());

        // Verifica se os dados retornados estão corretos
        assertThat(outputDTO.id()).isEqualTo(profissional.getId());
        assertThat(outputDTO.nome()).isEqualTo(profissional.getNome());
        assertThat(outputDTO.especialidades()).isEqualTo(profissional.getEspecialidades());
        assertThat(outputDTO.statusProfissional()).isEqualTo(profissional.getStatusProfissional());
        assertThat(outputDTO.estabelecimento()).isEqualTo(profissional.getEstabelecimento().getNome());
    }

    @Test
    public void testBuscarProfissionalInexistente() {
        // Verifica se a exceção ProfissionalNotFoundException é lançada ao buscar um profissional inexistente
        Exception exception = assertThrows(ProfissionalNotFoundException.class, () -> {
            buscarProfissionalUseCase.buscarProfissional(999L); // ID inexistente
        });

        // Verifica se a mensagem de exceção é a esperada
        assertThat(exception.getMessage()).isEqualTo(MensagemConstantes.EXCEPTION_PROFISSIONAL_NAO_ENCONTRADO);
    }
}
