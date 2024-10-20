package br.com.fiap.beautymanagerapi.adapters.controllers.cliente;

import br.com.fiap.beautymanagerapi.adapters.presenters.cliente.ClientePresenter;
import br.com.fiap.beautymanagerapi.records.cliente.ClienteInputDTO;
import br.com.fiap.beautymanagerapi.records.cliente.ClienteOutputDTO;
import br.com.fiap.beautymanagerapi.records.cliente.ClienteRequestModel;
import br.com.fiap.beautymanagerapi.records.cliente.ClienteResponseModel;
import br.com.fiap.beautymanagerapi.usecase.cliente.CriarClienteUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    private final CriarClienteUseCase criarClienteUseCase;
    private final ClientePresenter clientePresenter;

    public ClienteController(CriarClienteUseCase criarClienteUseCase, ClientePresenter clientePresenter) {
        this.criarClienteUseCase = criarClienteUseCase;
        this.clientePresenter = clientePresenter;
    }

    @PostMapping
    public ResponseEntity<ClienteResponseModel> criarCliente(@RequestBody ClienteRequestModel requestModel){

        var clienteDTO = toInputDTO(requestModel);
        ClienteOutputDTO outputDTO = criarClienteUseCase.criarCliente(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientePresenter.toResponseModel(outputDTO));

    }

    private ClienteInputDTO toInputDTO(ClienteRequestModel requestModel) {
        return new ClienteInputDTO(
                requestModel.nome(),
                requestModel.email(),
                requestModel.telefone()
        );
    }
}
