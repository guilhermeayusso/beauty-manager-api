package br.com.fiap.beautymanagerapi.avaliacao;
import br.com.fiap.beautymanagerapi.adapters.gateways.avaliacao.JpaAvaliacaoRepository;
import br.com.fiap.beautymanagerapi.adapters.gateways.estabelecimento.JpaEstabelecimentoRepository;
import br.com.fiap.beautymanagerapi.entities.AvaliacaoEntity;
import br.com.fiap.beautymanagerapi.entities.EstabelecimentoEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Sql(scripts = "/scripts/insert-massa-testes.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/scripts/delete-massa-testes.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class JpaAvaliacaoRepositoryTest {

    @Autowired
    private JpaAvaliacaoRepository jpaAvaliacaoRepository;
    @Autowired
    private JpaEstabelecimentoRepository estabelecimentoRepository;
    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void testeSaveAvaliacao(){
        Optional<EstabelecimentoEntity> estabelecimentoOptional = estabelecimentoRepository.findById(11L);
        EstabelecimentoEntity estabelecimento = estabelecimentoOptional.get();

        assertThat(estabelecimento).isNotNull();
        assertThat(estabelecimento.getNome()).isEqualTo("Beleza & Estilo");

        testEntityManager.clear();

        AvaliacaoEntity avaliacao = new AvaliacaoEntity();

        avaliacao.setDataAvaliacao(LocalDateTime.now());
        avaliacao.setNota(4.5);
        avaliacao.setAutor("Jonas F. Silva");
        avaliacao.setComentario("Gostei mas poderia ser melhor");
        avaliacao.setEstabelecimentoEntity(estabelecimento);

        AvaliacaoEntity avaliacaoSaved = jpaAvaliacaoRepository.save(avaliacao);

        assertThat(avaliacaoSaved).isNotNull();
        assertThat(avaliacaoSaved.getId()).isNotNull();
        assertThat(avaliacaoSaved.getNota()).isEqualTo(4.5);
        assertThat(avaliacaoSaved.getAutor()).isEqualTo("Jonas F. Silva");
        assertThat(avaliacaoSaved.getEstabelecimentoEntity()).isNotNull();
    }

    @Test
    public void testFindByEstabelecimentoEntityNomeContainingIgnoreCase() {
        List<AvaliacaoEntity> result = jpaAvaliacaoRepository.findByEstabelecimentoEntity_NomeContainingIgnoreCase("estilo");
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getEstabelecimentoEntity().getNome()).isEqualTo("Beleza & Estilo");
        assertThat(result.get(0).getNota()).isEqualTo(4.5);
    }
}
