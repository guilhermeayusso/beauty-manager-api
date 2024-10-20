package br.com.fiap.beautymanagerapi.estabelecimento;

import br.com.fiap.beautymanagerapi.constantes.MensagemConstantes;
import br.com.fiap.beautymanagerapi.enums.StatusProfissional;
import br.com.fiap.beautymanagerapi.exception.EstabelecimentoNotFoundException;
import br.com.fiap.beautymanagerapi.records.estabelecimento.EstabelecimentoOutputDTO;
import br.com.fiap.beautymanagerapi.usecase.estabelecimento.BuscarEstabelecimentoUseCase;
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
public class BuscarEstabelecimentoUseCaseTest {

    @Autowired
    private BuscarEstabelecimentoUseCase buscarEstabelecimentoUseCase;

    @Test
    void deveBuscarEstabelecimentoPorIdComSucesso() {
        // Act
        EstabelecimentoOutputDTO estabelecimento = buscarEstabelecimentoUseCase.buscarEstabelecimentoPorId(11L);

        // Assert
        assertThat(estabelecimento.id()).isEqualTo(11L);
        assertThat(estabelecimento.nome()).isEqualTo("Beleza & Estilo");
    }

    @Test
    void deveLancarExcecaoQuandoEstabelecimentoNaoForEncontrado() {
        // Act & Assert
        RuntimeException thrown = assertThrows(EstabelecimentoNotFoundException.class, () -> {
            buscarEstabelecimentoUseCase.buscarEstabelecimentoPorId(999L);
        });

        // Assert
        assertThat(thrown.getMessage()).isEqualTo(MensagemConstantes.EXCEPTION_ESTABELECIMENTO_NAO_ENCONTRADO);
    }

    @Test
    void deveBuscarEstabelecimentosPorNome() {
        // Act
        List<EstabelecimentoOutputDTO> estabelecimentos = buscarEstabelecimentoUseCase.buscarEstabelecimentosPorNome("beleza");

        // Assert
        assertThat(estabelecimentos).hasSize(1);
        assertThat(estabelecimentos.get(0).nome()).isEqualTo("Beleza & Estilo");
    }

    @Test
    void deveBuscarEstabelecimentosPorCidade() {
        // Act
        List<EstabelecimentoOutputDTO> estabelecimentos = buscarEstabelecimentoUseCase.buscarEstabelecimentosPorCidade("São Paulo");

        // Assert
        assertThat(estabelecimentos).hasSize(2);
        assertThat(estabelecimentos.get(0).nome()).isEqualTo("Corte Certo");
        assertThat(estabelecimentos.get(1).nome()).isEqualTo("Spa Relax");
    }

    @Test
    void deveBuscarPorStatusDoProfissional() {
        // Act
        List<EstabelecimentoOutputDTO> estabelecimentos = buscarEstabelecimentoUseCase.buscarPorStatusDoProfissional(StatusProfissional.DISPONIVEL);

        // Assert
        assertThat(estabelecimentos).hasSize(2);
        assertThat(estabelecimentos.get(0).nome()).isEqualTo("Beleza & Estilo");
        assertThat(estabelecimentos.get(1).nome()).isEqualTo("Spa Relax");
    }

    @Test
    void deveBuscarPorServicosOferecidos() {
        // Act
        List<EstabelecimentoOutputDTO> estabelecimentos = buscarEstabelecimentoUseCase.buscarPorServicosOferecidos("Corte de Cabelo");

        // Assert
        assertThat(estabelecimentos).hasSize(1);
        assertThat(estabelecimentos.get(0).nome()).isEqualTo("Beleza & Estilo");
    }
}
