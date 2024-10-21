package br.com.fiap.beautymanagerapi.adapters.controllers.profissional;

import br.com.fiap.beautymanagerapi.adapters.presenters.profissional.ProfissionalPresenter;
import br.com.fiap.beautymanagerapi.records.profissional.ProfissionalInputDTO;
import br.com.fiap.beautymanagerapi.records.profissional.ProfissionalRequestModel;
import br.com.fiap.beautymanagerapi.records.profissional.ProfissionalRequestModelStatus;
import br.com.fiap.beautymanagerapi.records.profissional.ProfissionalResponseModel;
import br.com.fiap.beautymanagerapi.usecase.profissional.AtualizarStatusProfissionalUseCase;
import br.com.fiap.beautymanagerapi.usecase.profissional.BuscarProfissionalUseCase;
import br.com.fiap.beautymanagerapi.usecase.profissional.CriarProfissionalUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/estabelecimentos/profissionais")
public class ProfissionalController {

    private final CriarProfissionalUseCase criarProfissionalUseCase;
    private final BuscarProfissionalUseCase buscarProfissionalUseCase;
    private final AtualizarStatusProfissionalUseCase atualizarStatusProfissionalUseCase;
    private final ProfissionalPresenter presenter;


    public ProfissionalController(CriarProfissionalUseCase criarProfissionalUseCase, BuscarProfissionalUseCase buscarProfissionalUseCase, AtualizarStatusProfissionalUseCase atualizarStatusProfissionalUseCase, ProfissionalPresenter presenter) {
        this.criarProfissionalUseCase = criarProfissionalUseCase;
        this.buscarProfissionalUseCase = buscarProfissionalUseCase;
        this.atualizarStatusProfissionalUseCase = atualizarStatusProfissionalUseCase;
        this.presenter = presenter;
    }

    @PostMapping
    public ResponseEntity<ProfissionalResponseModel> criarProfissional(@Valid @RequestBody ProfissionalRequestModel requestModel) {
        var profissional = criarProfissionalUseCase.criarProfissional(toInputDTO(requestModel));
        return ResponseEntity.status(HttpStatus.CREATED).body(presenter.toResponseModel(profissional));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfissionalResponseModel> buscarProfissional(@PathVariable Long id) {
        var profissional = buscarProfissionalUseCase.buscarProfissional(id);
        return ResponseEntity.ok().body(presenter.toResponseModel(profissional));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> atualizaStatusProfissional(@PathVariable Long id,
                                                                                @RequestBody ProfissionalRequestModelStatus requestModel) {
        var profissional = atualizarStatusProfissionalUseCase.atualizarStatusProfissional(id,requestModel.statusProfissional());
        return ResponseEntity.noContent().build();
    }

    private ProfissionalInputDTO toInputDTO(ProfissionalRequestModel requestModel) {
        return new ProfissionalInputDTO(
                requestModel.nome(),
                requestModel.especialidades(),
                requestModel.statusProfissional(),
                requestModel.estabelecimento_id()
        );
    }

}
