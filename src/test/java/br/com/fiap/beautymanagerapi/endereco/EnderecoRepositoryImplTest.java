package br.com.fiap.beautymanagerapi.endereco;

import br.com.fiap.beautymanagerapi.adapters.gateways.endereco.EnderecoRepositoryImpl;
import br.com.fiap.beautymanagerapi.entities.EnderecoEntity;
import br.com.fiap.beautymanagerapi.entities.EstabelecimentoEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@Import(EnderecoRepositoryImpl.class)
@Sql(scripts = "/scripts/insert-massa-testes.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/scripts/delete-massa-testes.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class EnderecoRepositoryImplTest {

    @Autowired
    private EnderecoRepositoryImpl jpaEnderecoRepositoryImpl;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void testInsercaoEnderecoPositiva() {
        // Recuperar estabelecimento já existente
        EstabelecimentoEntity estabelecimento = testEntityManager.find(EstabelecimentoEntity.class, 44L);
        assertThat(estabelecimento).isNotNull();

        // Criar novo endereço para o estabelecimento
        EnderecoEntity novoEndereco = EnderecoEntity.builder()
                .cep("12345-678")
                .logradouro("Rua Teste")
                .numero("100")
                .complemento("Apto 1")
                .bairro("Bairro Exemplo")
                .cidade("Cidade Teste")
                .uf("SP")
                .estabelecimento(estabelecimento)
                .build();

        // Salvar o endereço
        EnderecoEntity enderecoSalvo = jpaEnderecoRepositoryImpl.salvarEndereco(novoEndereco);

        // Verificar se o endereço foi salvo corretamente
        assertThat(enderecoSalvo).isNotNull();
        assertThat(enderecoSalvo.getId()).isNotNull();
        assertThat(enderecoSalvo.getEstabelecimento().getId()).isEqualTo(estabelecimento.getId());
    }

    @Test
    public void testInsercaoEnderecoNegativa() {
        // Recuperar estabelecimento que já possui um endereço
        EstabelecimentoEntity estabelecimentoComEndereco = testEntityManager.find(EstabelecimentoEntity.class, 22L);
        assertThat(estabelecimentoComEndereco).isNotNull();

        // Verificar que o estabelecimento já tem um endereço associado
        EnderecoEntity enderecoExistente = testEntityManager.find(EnderecoEntity.class, estabelecimentoComEndereco.getEndereco().getId());
        assertThat(enderecoExistente.getEstabelecimento().getId()).isEqualTo(estabelecimentoComEndereco.getId());

        // Tentar adicionar um novo endereço ao mesmo estabelecimento
        EnderecoEntity novoEndereco = EnderecoEntity.builder()
                .cep("87654-321")
                .logradouro("Rua Nova")
                .numero("200")
                .complemento("Apto 2")
                .bairro("Novo Bairro")
                .cidade("Cidade Nova")
                .uf("RJ")
                .estabelecimento(estabelecimentoComEndereco)
                .build();

        // Expectativa: deve lançar uma exceção por causa da restrição de um-para-um (unique constraint)
        assertThrows(DataIntegrityViolationException.class, () -> {
            jpaEnderecoRepositoryImpl.salvarEndereco(novoEndereco);
            testEntityManager.flush(); // Forçar a execução para capturar a exceção
        });
    }
}
