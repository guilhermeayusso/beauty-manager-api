package br.com.fiap.beautymanagerapi.servico;

import br.com.fiap.beautymanagerapi.adapters.gateways.estabelecimento.JpaEstabelecimentoRepository;
import br.com.fiap.beautymanagerapi.adapters.gateways.servico.ServicoRepositoryImpl;
import br.com.fiap.beautymanagerapi.entities.EstabelecimentoEntity;
import br.com.fiap.beautymanagerapi.entities.ServicoEntity;
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
@Import({ServicoRepositoryImpl.class})
public class ServicoRepositoryImplTest {

    @Autowired
    private ServicoRepositoryImpl servicoRepositoryImpl;
    @Autowired
    private JpaEstabelecimentoRepository estabelecimentoRepository;
    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void testSaveServico(){
        Optional<EstabelecimentoEntity> estabelecimentoOptional = estabelecimentoRepository.findById(11L);
        EstabelecimentoEntity estabelecimento = estabelecimentoOptional.get();

        assertThat(estabelecimento).isNotNull();

        testEntityManager.clear();

        ServicoEntity servico = new ServicoEntity();
        servico.setNome("Corte e Barba");
        servico.setDescricao("Corte e Barba");
        servico.setEstabelecimento(estabelecimento);
        servico.setProfissionaisHabilitados(estabelecimento.getProfissionaisDisponiveis());

        ServicoEntity savedServico = servicoRepositoryImpl.saveServico(servico);

        assertThat(savedServico).isNotNull();
        assertThat(savedServico.getNome()).isEqualTo("Corte e Barba");
        assertThat(savedServico.getDescricao()).isEqualTo("Corte e Barba");
        assertThat(savedServico.getEstabelecimento()).isNotNull();
        assertThat(savedServico.getProfissionaisHabilitados()).isNotNull();

    }

    @Test
    public void testDeleteServico() {
        // Primeiro, verifique se o serviço com ID 11L existe
        Optional<ServicoEntity> optionalServicoBeforeDelete = servicoRepositoryImpl.findServicoById(11L);
        assertThat(optionalServicoBeforeDelete.isPresent()).isTrue(); // Verifica que o serviço existe

        // Realiza a deleção
        servicoRepositoryImpl.deleteServicoById(11L);

        // Busca o serviço novamente para garantir que foi deletado
        Optional<ServicoEntity> optionalServicoAfterDelete = servicoRepositoryImpl.findServicoById(11L);
        assertThat(optionalServicoAfterDelete.isEmpty()).isTrue(); // Verifica que o serviço não existe mais
    }
}
