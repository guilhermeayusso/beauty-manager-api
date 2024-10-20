package br.com.fiap.beautymanagerapi.avaliacao;

import br.com.fiap.beautymanagerapi.constantes.MensagemConstantes;
import br.com.fiap.beautymanagerapi.exception.EstabelecimentoNotFoundException;
import br.com.fiap.beautymanagerapi.records.avaliacao.AvaliacaoInputDTO;
import br.com.fiap.beautymanagerapi.records.avaliacao.AvaliacaoOutputDTO;
import br.com.fiap.beautymanagerapi.usecase.avaliacao.CriarAvaliacaoUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@Sql(scripts = "/scripts/insert-massa-testes.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/scripts/delete-massa-testes.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@SpringBootTest
public class CriarAvaliacaoUseCaseTest {

    @Autowired
    private CriarAvaliacaoUseCase criarAvaliacaoUseCase;


    @Test
    void deveCriarAvaliacaoComSucesso() {


        AvaliacaoInputDTO avaliacaoInputDTO = new AvaliacaoInputDTO(
                "Guilherme Ayusso",
                4.5,
                "Atendimento excelente",
                LocalDateTime.now(),
                11L
        );

        AvaliacaoOutputDTO outputDTO = criarAvaliacaoUseCase.criarAvaliacao(avaliacaoInputDTO);

        assertThat(outputDTO.autor()).isEqualTo("Guilherme Ayusso");

    }

    @Test
    void deveLancarExcecaoQuandoEstabelecimentoNaoForEncontrado() {

        AvaliacaoInputDTO avaliacaoInputDTO = new AvaliacaoInputDTO(
                "Guilherme Ayusso",
                4.5,
                "Atendimento excelente",
                LocalDateTime.now(),
                100L
        );

        // Act & Assert
        RuntimeException thrown = assertThrows(EstabelecimentoNotFoundException.class, () -> {
            criarAvaliacaoUseCase.criarAvaliacao(avaliacaoInputDTO);
        });

        assertThat(thrown.getMessage()).isEqualTo(MensagemConstantes.EXCEPTION_ESTABELECIMENTO_NAO_ENCONTRADO);
    }

}



