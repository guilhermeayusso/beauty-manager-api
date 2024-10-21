package br.com.fiap.beautymanagerapi.adapters.controllers.servico;

import br.com.fiap.beautymanagerapi.adapters.presenters.servico.ServicoPresenter;
import br.com.fiap.beautymanagerapi.records.servico.ServicoInputDTO;
import br.com.fiap.beautymanagerapi.records.servico.ServicoRequestModel;
import br.com.fiap.beautymanagerapi.records.servico.ServicoResponseModel;
import br.com.fiap.beautymanagerapi.usecase.servico.BuscarServicoUseCase;
import br.com.fiap.beautymanagerapi.usecase.servico.CriarServicoUseCase;
import br.com.fiap.beautymanagerapi.usecase.servico.DeletarServicoUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/estabelecimentos/servicos")
public class ServicoController {

    private final CriarServicoUseCase criarServicoUseCase;
    private final DeletarServicoUseCase deletarServicoUseCase;
    private final BuscarServicoUseCase buscarServicoUseCase;
    private final ServicoPresenter presenter;

    public ServicoController(CriarServicoUseCase criarServicoUseCase, DeletarServicoUseCase deletarServicoUseCase, BuscarServicoUseCase buscarServicoUseCase, ServicoPresenter presenter) {
        this.criarServicoUseCase = criarServicoUseCase;
        this.deletarServicoUseCase = deletarServicoUseCase;
        this.buscarServicoUseCase = buscarServicoUseCase;
        this.presenter = presenter;
    }

    @PostMapping
    public ResponseEntity<ServicoResponseModel> cadastrar (@RequestBody ServicoRequestModel requestModel) {
        var servico = criarServicoUseCase.criarServico(toInputDTO(requestModel));
        return ResponseEntity.status(HttpStatus.CREATED).
                body(presenter.toResponseModel(servico));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicoResponseModel> buscar (@PathVariable Long id) {
        var servico = buscarServicoUseCase.buscarServicoPorId(id);
        return ResponseEntity.ok(presenter.toResponseModel(servico));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar (@PathVariable Long id) {
       deletarServicoUseCase.deletarServico(id);
       return ResponseEntity.noContent().build();
    }

    private ServicoInputDTO toInputDTO (ServicoRequestModel requestModel){
        return new ServicoInputDTO(
                requestModel.nome(),
                requestModel.descricao(),
                requestModel.preco(),
                requestModel.estabelecimentoId(),
                requestModel.profissionaisIds()
        );
    }
}
