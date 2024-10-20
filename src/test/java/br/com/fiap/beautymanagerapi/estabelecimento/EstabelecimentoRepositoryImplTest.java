package br.com.fiap.beautymanagerapi.estabelecimento;

import br.com.fiap.beautymanagerapi.adapters.gateways.estabelecimento.EstabelecimentoRepositoryImpl;
import br.com.fiap.beautymanagerapi.entities.EstabelecimentoEntity;
import br.com.fiap.beautymanagerapi.enums.StatusProfissional;
import br.com.fiap.beautymanagerapi.enums.TipoEstabelecimento;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Sql(scripts = "/scripts/insert-massa-testes.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/scripts/delete-massa-testes.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Import(EstabelecimentoRepositoryImpl.class)
public class EstabelecimentoRepositoryImplTest {

    @Autowired
    private EstabelecimentoRepositoryImpl estabelecimentoRepository;

    @Test
    public void testBuscarPorNome() {
        List<EstabelecimentoEntity> result = estabelecimentoRepository.buscarPorNome("estilo");
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getNome()).isEqualTo("Beleza & Estilo");
    }

    @Test
    public void testCriarEstabelecimento(){
        EstabelecimentoEntity estabelecimentoEntity = new EstabelecimentoEntity();

        estabelecimentoEntity.setId(8L);
        estabelecimentoEntity.setNome("The Shaving");
        estabelecimentoEntity.setHorarioDeAbertura(LocalTime.of(12, 0, 0));
        estabelecimentoEntity.setHorarioDeFechamento(LocalTime.of(23, 0, 0));
        estabelecimentoEntity.setTipoEstabelecimento(TipoEstabelecimento.BARBEARIA);

        // Adicionando fotos
        estabelecimentoEntity.setFotos(Arrays.asList("foto1.jpg", "foto2.jpg"));

        EstabelecimentoEntity estabelecimentoEntitySaved = estabelecimentoRepository.criarEstabelecimento(estabelecimentoEntity);

        assertThat(estabelecimentoEntitySaved.getNome()).isEqualTo("The Shaving");
        assertThat(estabelecimentoEntitySaved.getHorarioDeAbertura()).isEqualTo("12:00:00");

    }

    @Test
    public void testBuscarPorCidade() {
        List<EstabelecimentoEntity> result = estabelecimentoRepository.buscarPorCidade("Osasco");
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getNome()).isEqualTo("Beleza & Estilo");
    }

    @Test
    public void testBuscarPorTipoDoEstabelecimento() {
        List<EstabelecimentoEntity> result = estabelecimentoRepository.buscarPorTipoDoEstabelecimento("barbearia");
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getNome()).isEqualTo("Corte Certo");
    }

    @Test
    public void testBuscarPorServicosOferecidos() {
        List<EstabelecimentoEntity> result = estabelecimentoRepository.buscarPorServicoOferecido("corte");
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getNome()).isEqualTo("Beleza & Estilo");
    }

    @Test
    public void testBuscarPorStatusDoProfissional() {
        List<EstabelecimentoEntity> result = estabelecimentoRepository.buscarPorStatusDoProfissional(StatusProfissional.DISPONIVEL);
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getNome()).isEqualTo("Beleza & Estilo");
        assertThat(result.get(1).getNome()).isEqualTo("Spa Relax");
    }

    @Test
    public void testBuscarPorId(){
        Optional<EstabelecimentoEntity> resultOptional = estabelecimentoRepository.buscarPorId(11L);
        assertThat(resultOptional.isPresent()).isTrue();
        assertThat(resultOptional.get().getId()).isEqualTo(11L);
        assertThat(resultOptional.get().getNome()).isEqualTo("Beleza & Estilo");
        assertThat(resultOptional.get().getHorarioDeAbertura()).isEqualTo("09:00:00");
    }
}
