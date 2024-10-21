package br.com.fiap.beautymanagerapi.endereco;

import br.com.fiap.beautymanagerapi.constantes.MensagemConstantes;
import br.com.fiap.beautymanagerapi.exception.ConflictException;
import br.com.fiap.beautymanagerapi.records.endereco.EnderecoInputDTO;
import br.com.fiap.beautymanagerapi.records.endereco.EnderecoOutputDTO;
import br.com.fiap.beautymanagerapi.usecase.endereco.CriarAtualizarEnderecoUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Sql(scripts = "/scripts/insert-massa-testes.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/scripts/delete-massa-testes.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class CriarAtualizarEnderecoUseCaseTest {

    @Autowired
    private CriarAtualizarEnderecoUseCase criarAtualizarEnderecoUseCase;

    @Test
    void deveCriarEnderecoComSucesso() {
        // Dado um DTO válido para criação de um novo endereço
        EnderecoInputDTO enderecoInputDTO = new EnderecoInputDTO(
                "12345-678",
                "Rua Teste",
                "123",
                "Apto 101",
                "Centro",
                "Cidade Teste",
                "SP",
                44L // ID do Estabelecimento
        );

        // Quando criamos o endereço
        EnderecoOutputDTO enderecoOutputDTO = criarAtualizarEnderecoUseCase.criarEndereco(enderecoInputDTO);

        // Então o endereço deve ser criado com sucesso e os dados devem corresponder
        assertThat(enderecoOutputDTO.cep()).isEqualTo("12345-678");
        assertThat(enderecoOutputDTO.logradouro()).isEqualTo("Rua Teste");
    }

    @Test
    void deveLancarExcecaoQuandoEstabelecimentoJaTiverEndereco() {
        // Dado um estabelecimento que já possui um endereço
        EnderecoInputDTO enderecoInputDTO = new EnderecoInputDTO(
                "12345-678",
                "Rua Teste",
                "123",
                "Apto 101",
                "Centro",
                "Cidade Teste",
                "SP",
                11L // ID do Estabelecimento que já tem um endereço
        );

        // Quando tentamos criar um segundo endereço para o mesmo estabelecimento
        ConflictException exception = assertThrows(ConflictException.class, () -> {
            criarAtualizarEnderecoUseCase.criarEndereco(enderecoInputDTO);
        });

        // Então uma exceção de conflito deve ser lançada com a mensagem correta
        assertThat(exception.getMessage()).isEqualTo(MensagemConstantes.ENDERECO_JA_CADASTRADO);
    }

    @Test
    void deveAtualizarEnderecoComSucesso() {
        // Dado um DTO válido para atualizar um endereço existente
        EnderecoInputDTO enderecoInputDTO = new EnderecoInputDTO(
                "98765-432",
                "Rua Atualizada",
                "456",
                "Apto 202",
                "Bairro Atualizado",
                "Cidade Atualizada",
                "RJ",
                500L // ID do Estabelecimento
        );

        // Quando atualizamos o endereço com ID 1
        EnderecoOutputDTO enderecoOutputDTO = criarAtualizarEnderecoUseCase.atualizarEndereco(11L, enderecoInputDTO);

        // Então o endereço deve ser atualizado com sucesso e os dados devem ser atualizados corretamente
        assertThat(enderecoOutputDTO.cep()).isEqualTo("98765-432");
        assertThat(enderecoOutputDTO.logradouro()).isEqualTo("Rua Atualizada");
        assertThat(enderecoOutputDTO.bairro()).isEqualTo("Bairro Atualizado");
    }

    @Test
    void deveLancarExcecaoQuandoEnderecoNaoForEncontrado() {
        // Dado um ID de endereço inexistente
        EnderecoInputDTO enderecoInputDTO = new EnderecoInputDTO(
                "98765-432",
                "Rua Atualizada",
                "456",
                "Apto 202",
                "Bairro Atualizado",
                "Cidade Atualizada",
                "RJ",
                11L // ID do Estabelecimento
        );

        // Quando tentamos atualizar um endereço que não existe
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            criarAtualizarEnderecoUseCase.atualizarEndereco(999L, enderecoInputDTO); // ID inexistente
        });

        // Então uma exceção deve ser lançada com a mensagem correta
        assertThat(exception.getMessage()).isEqualTo(MensagemConstantes.ENDERECO_NAO_ENCONTRADO);
    }
}
