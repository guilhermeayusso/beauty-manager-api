package br.com.fiap.beautymanagerapi.adapters.controllers.estabelecimento;

import br.com.fiap.beautymanagerapi.adapters.presenters.estabelecimento.EstabelecimentoPresenter;
import br.com.fiap.beautymanagerapi.constantes.MensagemConstantes;
import br.com.fiap.beautymanagerapi.records.estabelecimento.EstabelecimentoInputDTO;
import br.com.fiap.beautymanagerapi.records.estabelecimento.EstabelecimentoRequestModel;
import br.com.fiap.beautymanagerapi.records.estabelecimento.EstabelecimentoResponseModel;
import br.com.fiap.beautymanagerapi.usecase.estabelecimento.BuscarEstabelecimentoUseCase;
import br.com.fiap.beautymanagerapi.usecase.estabelecimento.CriarEstabelecimentoUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/v1/estabelecimentos")
public class EstabelecimentoController {

    private final CriarEstabelecimentoUseCase criarEstabelecimentoUseCase;
    private final BuscarEstabelecimentoUseCase buscarEstabelecimentoUseCase;
    private final EstabelecimentoPresenter estabelecimentoPresenter;

    public EstabelecimentoController(CriarEstabelecimentoUseCase criarEstabelecimentoUseCase, BuscarEstabelecimentoUseCase buscarEstabelecimentoUseCase, EstabelecimentoPresenter estabelecimentoPresenter) {
        this.criarEstabelecimentoUseCase = criarEstabelecimentoUseCase;
        this.buscarEstabelecimentoUseCase = buscarEstabelecimentoUseCase;
        this.estabelecimentoPresenter = estabelecimentoPresenter;
    }

    @PostMapping("/cadastro-inicial")
    public ResponseEntity<EstabelecimentoResponseModel> cadastrarEstabelecimento(@Valid @RequestBody EstabelecimentoRequestModel estabelecimentoRequestModel) {
        var estabelecimento = toInputDTO(estabelecimentoRequestModel);
        var estabelecimentoSalvo = criarEstabelecimentoUseCase.criarEstabelecimento(estabelecimento);
        return ResponseEntity.status(HttpStatus.CREATED).body(estabelecimentoPresenter.toResponseModel(estabelecimentoSalvo));
    }


    private EstabelecimentoInputDTO toInputDTO(EstabelecimentoRequestModel requestModel) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[H:mm][HH:mm]");

        if (LocalTime.parse(requestModel.horarioDeAbertura(),formatter).isAfter(LocalTime.parse(requestModel.horarioDeFechamento(),formatter))) {
            throw new IllegalArgumentException(MensagemConstantes.INCONSISTENCIA_HORARIO);
        }

        return new EstabelecimentoInputDTO(
                requestModel.nome(),
                LocalTime.parse(requestModel.horarioDeAbertura(),formatter),
                LocalTime.parse(requestModel.horarioDeFechamento(),formatter),
                requestModel.tipoEstabelecimento(),
                requestModel.fotos()
        );
    }
}