package br.com.fiap.beautymanagerapi.cliente;


import br.com.fiap.beautymanagerapi.adapters.presenters.cliente.ClientePresenter;
import br.com.fiap.beautymanagerapi.constantes.MensagemConstantes;
import br.com.fiap.beautymanagerapi.records.cliente.ClienteOutputDTO;
import br.com.fiap.beautymanagerapi.records.cliente.ClienteResponseModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class ClientePresenterTest {

    private ClientePresenter clientePresenter;

    @BeforeEach
    void setUp() {
        clientePresenter = new ClientePresenter();
    }

    @Test
    void toResponseModel_DeveRetornarModeloDeRespostaComMensagemDeSucesso_QuandoNaoConsulta() {
        // Mock do ClienteOutputDTO
        ClienteOutputDTO outputDTO = Mockito.mock(ClienteOutputDTO.class);
        Mockito.when(outputDTO.id()).thenReturn(1L);
        Mockito.when(outputDTO.nome()).thenReturn("João Silva");
        Mockito.when(outputDTO.email()).thenReturn("joao.silva@example.com");
        Mockito.when(outputDTO.telefone()).thenReturn("11999999999"); // Número de telefone válido

        // Chama o método a ser testado
        ClienteResponseModel responseModel = clientePresenter.toResponseModel(outputDTO, false);

        // Verifica os resultados
        assertEquals(1L, responseModel.id());
        assertEquals("João Silva", responseModel.nome());
        assertEquals("joao.silva@example.com", responseModel.email());
        assertEquals("(11) 99999-9999", responseModel.telefone()); // Telefone formatado
        assertEquals(MensagemConstantes.CLIENTE_CADASTRADO_SUCESSO, responseModel.mensagem());
    }

    @Test
    void toResponseModel_DeveRetornarModeloDeRespostaSemMensagem_QuandoConsulta() {
        // Mock do ClienteOutputDTO
        ClienteOutputDTO outputDTO = Mockito.mock(ClienteOutputDTO.class);
        Mockito.when(outputDTO.id()).thenReturn(1L);
        Mockito.when(outputDTO.nome()).thenReturn("João Silva");
        Mockito.when(outputDTO.email()).thenReturn("joao.silva@example.com");
        Mockito.when(outputDTO.telefone()).thenReturn("11999999999"); // Número de telefone válido

        // Chama o método a ser testado
        ClienteResponseModel responseModel = clientePresenter.toResponseModel(outputDTO, true);

        // Verifica os resultados
        assertEquals(1L, responseModel.id());
        assertEquals("João Silva", responseModel.nome());
        assertEquals("joao.silva@example.com", responseModel.email());
        assertEquals("(11) 99999-9999", responseModel.telefone()); // Telefone formatado
        assertEquals("", responseModel.mensagem()); // Sem mensagem
    }

    @Test
    void toResponseModel_DeveManterTelefoneSemFormatacao_QuandoTelefoneInvalido() {
        // Mock do ClienteOutputDTO
        ClienteOutputDTO outputDTO = Mockito.mock(ClienteOutputDTO.class);
        Mockito.when(outputDTO.id()).thenReturn(1L);
        Mockito.when(outputDTO.nome()).thenReturn("João Silva");
        Mockito.when(outputDTO.email()).thenReturn("joao.silva@example.com");
        Mockito.when(outputDTO.telefone()).thenReturn("999999999"); // Número de telefone inválido (sem DDD)

        // Chama o método a ser testado
        ClienteResponseModel responseModel = clientePresenter.toResponseModel(outputDTO, false);

        // Verifica os resultados
        assertEquals(1L, responseModel.id());
        assertEquals("João Silva", responseModel.nome());
        assertEquals("joao.silva@example.com", responseModel.email());
        assertEquals("999999999", responseModel.telefone()); // Telefone sem formatação
        assertEquals(MensagemConstantes.CLIENTE_CADASTRADO_SUCESSO, responseModel.mensagem());
    }
}

