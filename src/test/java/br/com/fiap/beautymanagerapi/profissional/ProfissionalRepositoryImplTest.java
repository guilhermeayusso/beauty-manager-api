package br.com.fiap.beautymanagerapi.profissional;

import br.com.fiap.beautymanagerapi.adapters.gateways.estabelecimento.JpaEstabelecimentoRepository;
import br.com.fiap.beautymanagerapi.adapters.gateways.profissional.ProfissionalRepositoryImpl;
import br.com.fiap.beautymanagerapi.entities.EstabelecimentoEntity;
import br.com.fiap.beautymanagerapi.entities.ProfissionalEntity;
import br.com.fiap.beautymanagerapi.enums.StatusProfissional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Sql(scripts = "/scripts/insert-massa-testes.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/scripts/delete-massa-testes.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Import({ProfissionalRepositoryImpl.class})
public class ProfissionalRepositoryImplTest {
    @Autowired
    private ProfissionalRepositoryImpl jpaProfissionalRepository;
    @Autowired
    private JpaEstabelecimentoRepository estabelecimentoRepository;
    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void testSaveProfissional() {

        Optional<EstabelecimentoEntity> estabelecimentoOptional = estabelecimentoRepository.findById(11L);
        EstabelecimentoEntity estabelecimento = estabelecimentoOptional.get();

        assertThat(estabelecimento).isNotNull();
        assertThat(estabelecimento.getNome()).isEqualTo("Beleza & Estilo");

        ProfissionalEntity profissional = new ProfissionalEntity();

        testEntityManager.clear();
        profissional.setNome("Osvaldo de Oliveira");
        profissional.setStatusProfissional(StatusProfissional.DISPONIVEL);
        profissional.setEspecialidades("Corte e Barba");
        profissional.setEstabelecimento(estabelecimento);


        ProfissionalEntity profissionalSaved = jpaProfissionalRepository.saveProfissional(profissional);

        assertThat(profissionalSaved).isNotNull();
        assertThat(profissionalSaved.getNome()).isEqualTo("Osvaldo de Oliveira");
        assertThat(profissionalSaved.getEspecialidades()).isEqualTo("Corte e Barba");
        assertThat(profissionalSaved.getEstabelecimento()).isNotNull();
        assertThat(profissionalSaved.getEstabelecimento().getNome()).isEqualTo("Beleza & Estilo");

    }
}
