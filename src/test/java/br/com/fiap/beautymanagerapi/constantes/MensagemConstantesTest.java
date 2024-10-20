package br.com.fiap.beautymanagerapi.constantes;



import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MensagemConstantesTest {

    @Test
    void deveConterMensagemClienteCadastradoComSucesso() {
        assertEquals("Cliente cadastrado com sucesso!", MensagemConstantes.CLIENTE_CADASTRADO_SUCESSO);
    }

    @Test
    void deveConterMensagemEstabelecimentoNaoEncontrado() {
        assertEquals("Estabelecimento não encontrado!", MensagemConstantes.EXCEPTION_ESTABELECIMENTO_NAO_ENCONTRADO);
        assertEquals("Estabelecimento com ID {} não encontrado na base!", MensagemConstantes.ESTABELECIMENTO_NAO_ENCONTRADO);
    }

    @Test
    void deveConterMensagemProfissionalNaoEncontrado() {
        assertEquals("Profissional com ID {} não encontrado na base!", MensagemConstantes.PROFISSIONAL_NAO_ENCONTRADO);
        assertEquals("Profissional não encontrado!", MensagemConstantes.EXCEPTION_PROFISSIONAL_NAO_ENCONTRADO);
    }

    @Test
    void deveConterMensagemServicoNaoEncontrado() {
        assertEquals("Serviço com ID {} não encontrado na base!", MensagemConstantes.SERVICO_NAO_ENCONTRADO);
        assertEquals("Servico não encontrado!", MensagemConstantes.EXCEPTION_SERVICO_NAO_ENCONTRADO);
    }

    @Test
    void deveConterMensagemClienteNaoEncontrado() {
        assertEquals("Cliente com ID {} não encontrado na base!", MensagemConstantes.CLIENTE_NAO_ENCONTRADO);
        assertEquals("Cliente não encontrado!", MensagemConstantes.EXCEPTION_CLIENTE_NAO_ENCONTRADO);
    }

    @Test
    void deveConterMensagemAgendamentoNaoEncontrado() {
        assertEquals("Agendamento com ID {} não encontrado na base!", MensagemConstantes.AGENDAMENTO_NAO_ENCONTRADO);
        assertEquals("Agendamento não encontrado!", MensagemConstantes.EXCEPTION_AGENDAMENTO_NAO_ENCONTRADO);
    }

    @Test
    void deveConterMensagemEnderecoJaCadastrado() {
        assertEquals("Endereço já cadastrado. ", MensagemConstantes.ENDERECO_JA_CADASTRADO);
    }

    @Test
    void deveConterMensagemEnderecoNaoEncontrado() {
        assertEquals("Endereço não encontrado. ", MensagemConstantes.ENDERECO_NAO_ENCONTRADO);
    }

    @Test
    void deveConterMensagemIndisponibilidadeProfissional() {
        assertEquals("O profissional deve estar disponivel para agendamento", MensagemConstantes.INDISPONIBILIDADE_PROFISSIONAL);
    }

    @Test
    void deveConterMensagemAvaliacaoNaoEncontrada() {
        assertEquals("Avaliação não encontrada!", MensagemConstantes.EXCEPTION_AVALIACAO_NAO_ENCONTRADO);
    }
}

