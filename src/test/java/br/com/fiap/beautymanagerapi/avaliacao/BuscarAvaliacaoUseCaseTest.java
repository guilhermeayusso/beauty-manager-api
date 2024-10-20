package br.com.fiap.beautymanagerapi.avaliacao;

import br.com.fiap.beautymanagerapi.constantes.MensagemConstantes;
import br.com.fiap.beautymanagerapi.exception.AvaliacaoNotFoundException;
import br.com.fiap.beautymanagerapi.records.avaliacao.AvaliacaoOutputDTO;
import br.com.fiap.beautymanagerapi.usecase.avaliacao.BuscarAvaliacaoUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Sql(scripts = "/scripts/insert-massa-testes.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/scripts/delete-massa-testes.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@SpringBootTest
public class BuscarAvaliacaoUseCaseTest {

    @Autowired
    private BuscarAvaliacaoUseCase buscarAvaliacaoUseCase;

    @Test
    void deveBuscarAvaliacaoComIdDoEstabelecimentoComSucesso(){
        List<AvaliacaoOutputDTO> outputDTO  = buscarAvaliacaoUseCase.buscarAvaliacaoPorEstabelecimentoId(11L);

        assertThat(outputDTO.get(0).autor()).isEqualTo("João Silva");
        assertThat(outputDTO.get(0).nota()).isEqualTo(4.5);
    }

    @Test
    void deveBuscarAvaliacaoComIdDoEstabelecimentoComSucessoEDoisItensNaLista(){
        List<AvaliacaoOutputDTO> outputDTO  = buscarAvaliacaoUseCase.buscarAvaliacaoPorEstabelecimentoId(33L);

        assertThat(outputDTO.get(0).autor()).isEqualTo("Pedro Almeida");
        assertThat(outputDTO.get(0).nota()).isEqualTo(3.5);
        assertThat(outputDTO.get(1).autor()).isEqualTo("Carlos Almeida");
        assertThat(outputDTO.get(1).nota()).isEqualTo(3.5);
    }

    @Test
    void deveLancarExcecaoQuandoAvaliacaoNaoForEncontrado(){

        // Act & Assert
        RuntimeException thrown = assertThrows(AvaliacaoNotFoundException.class, () -> {
           buscarAvaliacaoUseCase.buscarAvaliacaoPorEstabelecimentoId(99L);;
       });

        assertThat(thrown.getMessage()).isEqualTo(MensagemConstantes.EXCEPTION_AVALIACAO_NAO_ENCONTRADO);
    }

    @Test
    void deveBuscarAvaliacaoComNomeDoEstabelecimentoComSucesso(){
        List<AvaliacaoOutputDTO> outputDTO  = buscarAvaliacaoUseCase.buscarAvaliacaoPorEstabelecimentoComNome("estilo");

        assertThat(outputDTO.get(0).autor()).isEqualTo("João Silva");
        assertThat(outputDTO.get(0).nota()).isEqualTo(4.5);
    }

    @Test
    void deveBuscarAvaliacaoComNomeDoEstabelecimentoComSucessoEDoisItensNaLista(){
        List<AvaliacaoOutputDTO> outputDTO  = buscarAvaliacaoUseCase.buscarAvaliacaoPorEstabelecimentoComNome("relax");

        assertThat(outputDTO.get(0).autor()).isEqualTo("Pedro Almeida");
        assertThat(outputDTO.get(0).nota()).isEqualTo(3.5);
        assertThat(outputDTO.get(1).autor()).isEqualTo("Carlos Almeida");
        assertThat(outputDTO.get(1).nota()).isEqualTo(3.5);
    }

    @Test
    void deveLancarExcecaoQuandoAvaliacaoNaoForEncontradoPorNome(){

        // Act & Assert
        RuntimeException thrown = assertThrows(AvaliacaoNotFoundException.class, () -> {
            buscarAvaliacaoUseCase.buscarAvaliacaoPorEstabelecimentoComNome("Ytuba");;
        });

        assertThat(thrown.getMessage()).isEqualTo(MensagemConstantes.EXCEPTION_AVALIACAO_NAO_ENCONTRADO);
    }

}
