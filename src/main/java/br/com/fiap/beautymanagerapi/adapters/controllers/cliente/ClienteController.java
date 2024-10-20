package br.com.fiap.beautymanagerapi.adapters.controllers.cliente;

import br.com.fiap.beautymanagerapi.adapters.presenters.cliente.ClientePresenter;
import br.com.fiap.beautymanagerapi.records.cliente.ClienteInputDTO;
import br.com.fiap.beautymanagerapi.records.cliente.ClienteOutputDTO;
import br.com.fiap.beautymanagerapi.records.cliente.ClienteRequestModel;
import br.com.fiap.beautymanagerapi.records.cliente.ClienteResponseModel;
import br.com.fiap.beautymanagerapi.usecase.cliente.BuscarClienteUseCase;
import br.com.fiap.beautymanagerapi.usecase.cliente.CriarAlterarClienteUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    private final CriarAlterarClienteUseCase criarAlterarClienteUseCase;
    private final BuscarClienteUseCase buscarClienteUseCase;
    private final ClientePresenter clientePresenter;

    public ClienteController(CriarAlterarClienteUseCase criarAlterarClienteUseCase, BuscarClienteUseCase buscarClienteUseCase, ClientePresenter clientePresenter) {
        this.criarAlterarClienteUseCase = criarAlterarClienteUseCase;
        this.buscarClienteUseCase = buscarClienteUseCase;
        this.clientePresenter = clientePresenter;
    }


    @PostMapping
    public ResponseEntity<ClienteResponseModel> criarCliente(@Valid @RequestBody ClienteRequestModel requestModel) {

        var clienteDTO = toInputDTO(requestModel);
        ClienteOutputDTO outputDTO = criarAlterarClienteUseCase.criarCliente(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientePresenter.toResponseModel(outputDTO, false));

    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseModel> buscarCliente(@PathVariable Long id) {
        ClienteOutputDTO outputDTO = buscarClienteUseCase.buscarCliente(id);
        return ResponseEntity.ok(clientePresenter.toResponseModel(outputDTO, true));

    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@Valid @RequestBody ClienteRequestModel requestModel, @PathVariable Long id) {
        var clienteDTO = toInputDTO(requestModel);
        ClienteOutputDTO outputDTO = criarAlterarClienteUseCase.atualizarCliente(clienteDTO,id);
        return ResponseEntity.noContent().build();
    }



    private ClienteInputDTO toInputDTO(ClienteRequestModel requestModel) {
        return new ClienteInputDTO(
                requestModel.nome(),
                requestModel.email(),
                requestModel.telefone()
        );
    }
}
