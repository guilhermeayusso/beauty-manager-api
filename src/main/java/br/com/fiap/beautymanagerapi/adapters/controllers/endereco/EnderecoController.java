package br.com.fiap.beautymanagerapi.adapters.controllers.endereco;

import br.com.fiap.beautymanagerapi.adapters.presenters.endereco.EnderecoPresenter;
import br.com.fiap.beautymanagerapi.records.endereco.EnderecoInputDTO;
import br.com.fiap.beautymanagerapi.records.endereco.EnderecoRequestModel;
import br.com.fiap.beautymanagerapi.records.endereco.EnderecoResponseModel;
import br.com.fiap.beautymanagerapi.usecase.endereco.CriarAtualizarEnderecoUseCase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/estabelecimentos/enderecos")
public class EnderecoController {


    private final EnderecoPresenter presenter;
    private final CriarAtualizarEnderecoUseCase criarAtualizarEnderecoUseCase;

    public EnderecoController(EnderecoPresenter presenter, @Qualifier("criarAtualizarEnderecoUseCase") CriarAtualizarEnderecoUseCase criarAtualizarEnderecoUseCase) {
        this.presenter = presenter;
        this.criarAtualizarEnderecoUseCase = criarAtualizarEnderecoUseCase;
    }

    @PostMapping
    public ResponseEntity<EnderecoResponseModel> cadastraEndereco(@RequestBody EnderecoRequestModel requestModel){
        var endereco = criarAtualizarEnderecoUseCase.criarEndereco(toInputDTO(requestModel));
        return ResponseEntity.status(HttpStatus.CREATED).body(presenter.toResponseModel(endereco));
    }

    @PutMapping("{idEndereco}")
    public ResponseEntity<Void> atualizaEndereco(@PathVariable Long idEndereco, @RequestBody EnderecoRequestModel requestModel){
        criarAtualizarEnderecoUseCase.atualizarEndereco(idEndereco, toInputDTO(requestModel));
        return ResponseEntity.ok().build();
    }

    private EnderecoInputDTO toInputDTO (EnderecoRequestModel requestModel) {

        return new EnderecoInputDTO(
                requestModel.cep(),
                requestModel.logradouro(),
                requestModel.numero(),
                requestModel.complemento(),
                requestModel.bairro(),
                requestModel.cidade(),
                requestModel.uf(),
                requestModel.estabelecimentoId()
        );
    }

}
