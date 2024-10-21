package br.com.fiap.beautymanagerapi.adapters.controllers.avaliacao;

import br.com.fiap.beautymanagerapi.adapters.presenters.avaliacao.AvaliacaoPresenter;
import br.com.fiap.beautymanagerapi.records.avaliacao.AvaliacaoInputDTO;
import br.com.fiap.beautymanagerapi.records.avaliacao.AvaliacaoRequestModel;
import br.com.fiap.beautymanagerapi.records.avaliacao.AvaliacaoResponseModel;
import br.com.fiap.beautymanagerapi.usecase.avaliacao.BuscarAvaliacaoUseCase;
import br.com.fiap.beautymanagerapi.usecase.avaliacao.CriarAvaliacaoUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/avaliacao")
public class AvaliacaoController {

    private final CriarAvaliacaoUseCase criarAvaliacaoUseCase;
    private final BuscarAvaliacaoUseCase buscarAvaliacaoUseCase;
    private final AvaliacaoPresenter presenter;

    public AvaliacaoController(CriarAvaliacaoUseCase criarAvaliacaoUseCase, BuscarAvaliacaoUseCase buscarAvaliacaoUseCase, AvaliacaoPresenter presenter) {
        this.criarAvaliacaoUseCase = criarAvaliacaoUseCase;
        this.buscarAvaliacaoUseCase = buscarAvaliacaoUseCase;
        this.presenter = presenter;
    }

    @PostMapping("/criar")
    public ResponseEntity<AvaliacaoResponseModel> criarAvaliacao (@Valid @RequestBody AvaliacaoRequestModel requestModel){
         var avaliacao = criarAvaliacaoUseCase.criarAvaliacao(toInputDTO(requestModel));
         return ResponseEntity.status(HttpStatus.CREATED).body(presenter.toResponseModel(avaliacao));
    }

    @GetMapping("/consulta/{idEstabelecimento}")
    public ResponseEntity<List<AvaliacaoResponseModel>> buscarAvaliacoPorEC (@PathVariable Long idEstabelecimento){
        var avaliacao = buscarAvaliacaoUseCase.buscarAvaliacaoPorEstabelecimentoId(idEstabelecimento);
        return ResponseEntity.ok(new ArrayList<>(presenter.toResponseModelList(avaliacao)));
    }


    private AvaliacaoInputDTO toInputDTO(AvaliacaoRequestModel requestModel) {
        return new AvaliacaoInputDTO(
                requestModel.autor(),
                requestModel.nota(),
                requestModel.comentario(),
                null,
                requestModel.estabelecimento_id()

        );
    }
}
