package br.com.fiap.beautymanagerapi.cliente;


import br.com.fiap.beautymanagerapi.adapters.presenters.cliente.ClientePresenter;
import br.com.fiap.beautymanagerapi.constantes.MensagemConstantes;
import br.com.fiap.beautymanagerapi.records.cliente.ClienteOutputDTO;
import br.com.fiap.beautymanagerapi.records.cliente.ClienteRequestModel;
import br.com.fiap.beautymanagerapi.records.cliente.ClienteResponseModel;
import br.com.fiap.beautymanagerapi.usecase.cliente.BuscarClienteUseCase;
import br.com.fiap.beautymanagerapi.usecase.cliente.CriarAlterarClienteUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CriarAlterarClienteUseCase criarAlterarClienteUseCase;

    @MockBean
    private BuscarClienteUseCase buscarClienteUseCase;

    @MockBean
    private ClientePresenter clientePresenter;

    @Autowired
    private ObjectMapper objectMapper;

    private ClienteRequestModel clienteRequest;

    @BeforeEach
    void setUp() {
        clienteRequest = new ClienteRequestModel("João Silva", "joao.silva@example.com", "(11) 99999-9999");
    }

    @Test
    void criarCliente_DeveRetornarStatus201() throws Exception {
        // Criando um ClienteOutputDTO de exemplo
        ClienteOutputDTO clienteOutputDTO = new ClienteOutputDTO(1L, "nome", "joao.silva@example.com", "(11) 99999-9999");

        // Mockando o comportamento do caso de uso
        when(criarAlterarClienteUseCase.criarCliente(any())).thenReturn(clienteOutputDTO);

        // Criando um ClienteResponseModel de exemplo para o retorno do presenter
        ClienteResponseModel clienteResponseModel = new ClienteResponseModel(1L, "nome", "joao.silva@example.com", "(11) 99999-9999", MensagemConstantes.CLIENTE_CADASTRADO_SUCESSO);

        // Mockando o comportamento do presenter
        when(clientePresenter.toResponseModel(clienteOutputDTO, false)).thenReturn(clienteResponseModel);

        // Execução da requisição e validação do status 201 (Created)
        mockMvc.perform(post("/api/v1/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clienteRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))  // Validação do nome no JSON de resposta
                .andExpect(jsonPath("$.nome").value("nome"))
                .andExpect(jsonPath("$.email").value("joao.silva@example.com"))  // Validação do email
                .andExpect(jsonPath("$.telefone").value("(11) 99999-9999"))  // Validação do telefone
                .andExpect(jsonPath("$.mensagem").value(MensagemConstantes.CLIENTE_CADASTRADO_SUCESSO));
    }


    @Test
    public void buscarCliente_DeveRetornarClienteComStatus200() throws Exception {
        // Criando um ClienteOutputDTO de exemplo
        ClienteOutputDTO clienteOutputDTO = new ClienteOutputDTO(1L, "nome", "email", "telefone");

        // Mockando o comportamento do caso de uso
        when(buscarClienteUseCase.buscarCliente(anyLong())).thenReturn(clienteOutputDTO);

        // Criando um ClienteResponseModel de exemplo para o retorno do presenter
        ClienteResponseModel clienteResponseModel = new ClienteResponseModel(1L, "nome", "email", "telefone", null);

        // Mockando o comportamento do presenter
        when(clientePresenter.toResponseModel(clienteOutputDTO, true)).thenReturn(clienteResponseModel);

        // Executando a requisição e validando a resposta
        mockMvc.perform(get("/api/v1/clientes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("nome"))
                .andExpect(jsonPath("$.email").value("email"))
                .andExpect(jsonPath("$.telefone").value("telefone"));
    }

    @Test
    void atualizarCliente_DeveRetornarStatus204() throws Exception {
        // Criando um ClienteOutputDTO de exemplo
        ClienteOutputDTO clienteOutputDTO = new ClienteOutputDTO(1L, "nome", "email", "telefone");
        Long clienteId = 1L;

        Mockito.when(criarAlterarClienteUseCase.atualizarCliente(Mockito.any(), Mockito.eq(clienteId)))
                .thenReturn(clienteOutputDTO);

        mockMvc.perform(put("/api/v1/clientes/{id}", clienteId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clienteRequest)))
                .andExpect(status().isNoContent());
    }
}

