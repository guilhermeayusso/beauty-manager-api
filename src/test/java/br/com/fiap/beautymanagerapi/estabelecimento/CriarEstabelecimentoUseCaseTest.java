package br.com.fiap.beautymanagerapi.estabelecimento;

import br.com.fiap.beautymanagerapi.enums.TipoEstabelecimento;
import br.com.fiap.beautymanagerapi.records.estabelecimento.EstabelecimentoInputDTO;
import br.com.fiap.beautymanagerapi.records.estabelecimento.EstabelecimentoOutputDTO;
import br.com.fiap.beautymanagerapi.usecase.estabelecimento.CriarEstabelecimentoUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Sql(scripts = "/scripts/insert-massa-testes.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/scripts/delete-massa-testes.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@SpringBootTest
public class CriarEstabelecimentoUseCaseTest {

    @Autowired
    private CriarEstabelecimentoUseCase criarEstabelecimentoUseCase;

    @Test
    void deveCriarEstabelecimentoComSucesso() {

        // Mockando o EstabelecimentoInputDTO de entrada com o TipoEstabelecimento correto
        EstabelecimentoInputDTO estabelecimentoInputDTO = new EstabelecimentoInputDTO(
                "Salão Beleza",
                LocalTime.of(9, 0),
                LocalTime.of(18, 0),
                TipoEstabelecimento.BARBEARIA,  // Usando enum correto
                List.of("foto1.jpg", "foto2.jpg")
        );

        // Executando o caso de uso
        EstabelecimentoOutputDTO outputDTO = criarEstabelecimentoUseCase.criarEstabelecimento(estabelecimentoInputDTO);

        // Verificando o resultado
        assertThat(outputDTO.nome()).isEqualTo(estabelecimentoInputDTO.nome());
        assertThat(outputDTO.id()).isNotNull();
    }

    void deveLancarExcecaoQuandoEstabelecimentoJaExistir() {

        // Mockando o EstabelecimentoInputDTO com o nome de um estabelecimento já existente
        EstabelecimentoInputDTO estabelecimentoInputDTO = new EstabelecimentoInputDTO(
                "Salão Existente",  // Nome já existente na base de dados
                LocalTime.of(9, 0),
                LocalTime.of(18, 0),
                TipoEstabelecimento.BARBEARIA_GOURMET,
                List.of("foto1.jpg", "foto2.jpg")
        );

        // Act & Assert
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            criarEstabelecimentoUseCase.criarEstabelecimento(estabelecimentoInputDTO);
        });

        assertThat(thrown.getMessage()).isEqualTo("Estabelecimento já existe");  // Adapte a mensagem conforme a exceção esperada
    }
}
