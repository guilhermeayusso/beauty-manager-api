package br.com.fiap.beautymanagerapi.localizacao;

import br.com.fiap.beautymanagerapi.adapters.gateways.estabelecimento.JpaEstabelecimentoRepository;
import br.com.fiap.beautymanagerapi.adapters.gateways.localizacao.LocalizacaoRepositoryImpl;
import br.com.fiap.beautymanagerapi.entities.EstabelecimentoEntity;
import br.com.fiap.beautymanagerapi.entities.LocalizacaoEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Sql(scripts = "/scripts/insert-massa-testes.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/scripts/delete-massa-testes.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Import(LocalizacaoRepositoryImpl.class)
public class LocalizacaoRepositoryImplTest {

    @Autowired
    private LocalizacaoRepositoryImpl localizacaoRepository;

    @Autowired
    private JpaEstabelecimentoRepository estabelecimentoRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void testFindLocationsWithinRadius(){
        List<LocalizacaoEntity> result = localizacaoRepository.buscarEstabelecimentosProximos(-23.55052,-46.633308,50);
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getLatitude()).isEqualTo(-23.55052);
        assertThat(result.get(0).getLongitude()).isEqualTo(-46.633308);
        assertThat(result.get(0).getEstabelecimento().getNome()).isEqualTo("Beleza & Estilo");
    }

    @Test
    public void testSaveLocation(){
        Optional<EstabelecimentoEntity> estabelecimentoOptional = estabelecimentoRepository.findById(44L);
        EstabelecimentoEntity estabelecimento = estabelecimentoOptional.get();

        assertThat(estabelecimento).isNotNull();
        assertThat(estabelecimento.getNome()).isEqualTo("The Shaving");

        testEntityManager.clear();

        LocalizacaoEntity localizacao = new LocalizacaoEntity();

        localizacao.setLatitude(23.55052);
        localizacao.setLongitude(-46.633308);
        localizacao.setEstabelecimento(estabelecimento);

        LocalizacaoEntity localizacaoSaved = localizacaoRepository.saveLocalizacao(localizacao);

        assertThat(localizacaoSaved).isNotNull();
        assertThat(localizacaoSaved.getLatitude()).isEqualTo(23.55052);
        assertThat(localizacaoSaved.getLongitude()).isEqualTo(-46.633308);
        assertThat(localizacaoSaved.getEstabelecimento().getNome()).isEqualTo("The Shaving");


    }
}
