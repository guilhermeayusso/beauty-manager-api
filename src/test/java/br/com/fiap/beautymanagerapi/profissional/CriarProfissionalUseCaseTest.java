package br.com.fiap.beautymanagerapi.profissional;


import br.com.fiap.beautymanagerapi.adapters.gateways.estabelecimento.EstabelecimentoRepositoryImpl;
import br.com.fiap.beautymanagerapi.adapters.gateways.profissional.ProfissionalRepositoryImpl;
import br.com.fiap.beautymanagerapi.constantes.MensagemConstantes;
import br.com.fiap.beautymanagerapi.entities.EstabelecimentoEntity;
import br.com.fiap.beautymanagerapi.entities.ProfissionalEntity;
import br.com.fiap.beautymanagerapi.enums.StatusProfissional;
import br.com.fiap.beautymanagerapi.exception.EstabelecimentoNotFoundException;
import br.com.fiap.beautymanagerapi.records.profissional.ProfissionalInputDTO;
import br.com.fiap.beautymanagerapi.records.profissional.ProfissionalOutputDTO;
import br.com.fiap.beautymanagerapi.usecase.profissional.CriarProfissionalUseCase;
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
@Import({CriarProfissionalUseCase.class, ProfissionalRepositoryImpl.class, EstabelecimentoRepositoryImpl.class})
public class CriarProfissionalUseCaseTest {

    @Autowired
    private CriarProfissionalUseCase criarProfissionalUseCase;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void testCriarProfissionalComSucesso() {
        // Busca o estabelecimento válido a partir do script de massa
        EstabelecimentoEntity estabelecimento = testEntityManager.find(EstabelecimentoEntity.class, 11L);

        // Verifica se o estabelecimento foi encontrado
        assertThat(estabelecimento).isNotNull();

        // Cria um DTO de entrada para o novo profissional
        ProfissionalInputDTO inputDTO = new ProfissionalInputDTO(
                "José Silva",
                "Cabeleireiro",
                StatusProfissional.DISPONIVEL,
                estabelecimento.getId()
        );

        // Chama o caso de uso para criar o profissional
        ProfissionalOutputDTO outputDTO = criarProfissionalUseCase.criarProfissional(inputDTO);

        // Força o JPA a persistir as alterações e limpa o contexto
        testEntityManager.flush();
        testEntityManager.clear();

        // Verifica se o profissional foi salvo corretamente
        ProfissionalEntity profissionalSalvo = testEntityManager.find(ProfissionalEntity.class, outputDTO.id());

        assertThat(profissionalSalvo).isNotNull();
        assertThat(profissionalSalvo.getNome()).isEqualTo(inputDTO.nome());
        assertThat(profissionalSalvo.getEspecialidades()).isEqualTo(inputDTO.especialidades());
        assertThat(profissionalSalvo.getStatusProfissional()).isEqualTo(inputDTO.statusProfissional());
        assertThat(profissionalSalvo.getEstabelecimento()).isEqualTo(estabelecimento);
    }


    @Test
    public void testCriarProfissionalComEstabelecimentoInvalido() {
        // Cria um DTO de entrada com um ID de estabelecimento inválido
        ProfissionalInputDTO inputDTO = new ProfissionalInputDTO(
                "Maria Souza",
                "Manicure",
                StatusProfissional.DISPONIVEL,
                999L  // Estabelecimento inexistente
        );

        // Verifica se a exceção EstabelecimentoNotFoundException é lançada
        Exception exception = assertThrows(EstabelecimentoNotFoundException.class, () -> {
            criarProfissionalUseCase.criarProfissional(inputDTO);
        });

        // Verifica se a mensagem de exceção é a esperada
        assertThat(exception.getMessage()).isEqualTo(MensagemConstantes.EXCEPTION_ESTABELECIMENTO_NAO_ENCONTRADO);
    }
}

