package br.com.fiap.beautymanagerapi.servico;

import br.com.fiap.beautymanagerapi.adapters.gateways.estabelecimento.EstabelecimentoRepositoryImpl;
import br.com.fiap.beautymanagerapi.adapters.gateways.profissional.ProfissionalRepositoryImpl;
import br.com.fiap.beautymanagerapi.adapters.gateways.servico.ServicoRepositoryImpl;
import br.com.fiap.beautymanagerapi.constantes.MensagemConstantes;
import br.com.fiap.beautymanagerapi.entities.EstabelecimentoEntity;
import br.com.fiap.beautymanagerapi.entities.ProfissionalEntity;
import br.com.fiap.beautymanagerapi.entities.ServicoEntity;
import br.com.fiap.beautymanagerapi.exception.EstabelecimentoNotFoundException;
import br.com.fiap.beautymanagerapi.exception.ProfissionalNotFoundException;
import br.com.fiap.beautymanagerapi.records.servico.ServicoInputDTO;
import br.com.fiap.beautymanagerapi.records.servico.ServicoOutputDTO;
import br.com.fiap.beautymanagerapi.usecase.servico.CriarServicoUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@Sql(scripts = "/scripts/insert-massa-testes.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/scripts/delete-massa-testes.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Import({CriarServicoUseCase.class, ServicoRepositoryImpl.class, EstabelecimentoRepositoryImpl.class, ProfissionalRepositoryImpl.class})
public class CriarServicoUseCaseTest {

    @Autowired
    private CriarServicoUseCase criarServicoUseCase;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void testCriarServicoComSucesso() {
        // Busca o estabelecimento válido a partir do script de massa
        EstabelecimentoEntity estabelecimento = testEntityManager.find(EstabelecimentoEntity.class, 11L);
        assertThat(estabelecimento).isNotNull();

        // Busca os profissionais habilitados a partir do script de massa
        ProfissionalEntity profissional1 = testEntityManager.find(ProfissionalEntity.class, 11L);
        ProfissionalEntity profissional2 = testEntityManager.find(ProfissionalEntity.class, 22L);
        assertThat(profissional1).isNotNull();
        assertThat(profissional2).isNotNull();

        // Cria um DTO de entrada para o novo serviço
        ServicoInputDTO inputDTO = new ServicoInputDTO(
                "Corte de Cabelo",
                "Corte simples e moderno",
                50.0,
                estabelecimento.getId(),
                List.of(profissional1.getId(), profissional2.getId())
        );

        // Chama o caso de uso para criar o serviço
        ServicoOutputDTO outputDTO = criarServicoUseCase.criarServico(inputDTO);

        // Força o JPA a persistir as alterações e limpa o contexto
        testEntityManager.flush();
        testEntityManager.clear();

        // Verifica se o serviço foi salvo corretamente
        ServicoEntity servicoSalvo = testEntityManager.find(ServicoEntity.class, outputDTO.id());
        assertThat(servicoSalvo).isNotNull();
        assertThat(servicoSalvo.getNome()).isEqualTo(inputDTO.nome());
        assertThat(servicoSalvo.getDescricao()).isEqualTo(inputDTO.descricao());
        assertThat(servicoSalvo.getPreco()).isEqualTo(inputDTO.preco());
        assertThat(servicoSalvo.getEstabelecimento()).isEqualTo(estabelecimento);
        assertThat(servicoSalvo.getProfissionaisHabilitados()).containsExactlyInAnyOrder(profissional1, profissional2);
    }

    @Test
    public void testCriarServicoComEstabelecimentoInvalido() {
        // Cria um DTO de entrada com um ID de estabelecimento inválido
        ServicoInputDTO inputDTO = new ServicoInputDTO(
                "Corte de Cabelo",
                "Corte simples e moderno",
                50.0,
                999L,  // Estabelecimento inexistente
                List.of(11L, 2L)
        );

        // Verifica se a exceção EstabelecimentoNotFoundException é lançada
        Exception exception = assertThrows(EstabelecimentoNotFoundException.class, () -> {
            criarServicoUseCase.criarServico(inputDTO);
        });

        // Verifica se a mensagem de exceção é a esperada
        assertThat(exception.getMessage()).isEqualTo(MensagemConstantes.EXCEPTION_ESTABELECIMENTO_NAO_ENCONTRADO);
    }

    @Test
    public void testCriarServicoComProfissionalInvalido() {
        // Busca o estabelecimento válido a partir do script de massa
        EstabelecimentoEntity estabelecimento = testEntityManager.find(EstabelecimentoEntity.class, 11L);
        assertThat(estabelecimento).isNotNull();

        // Cria um DTO de entrada com um ID de profissional inválido
        ServicoInputDTO inputDTO = new ServicoInputDTO(
                "Corte de Cabelo",
                "Corte simples e moderno",
                50.0,
                estabelecimento.getId(),
                List.of(11L, 999L)  // Um profissional existente e outro inexistente
        );

        // Verifica se a exceção ProfissionalNotFoundException é lançada
        Exception exception = assertThrows(ProfissionalNotFoundException.class, () -> {
            criarServicoUseCase.criarServico(inputDTO);
        });

        // Verifica se a mensagem de exceção é a esperada
        assertThat(exception.getMessage()).isEqualTo(MensagemConstantes.EXCEPTION_PROFISSIONAL_NAO_ENCONTRADO);
    }
}
