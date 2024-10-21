package br.com.fiap.beautymanagerapi.adapters.controllers.localizacao;

import br.com.fiap.beautymanagerapi.constantes.MensagemConstantes;
import br.com.fiap.beautymanagerapi.records.cliente.ClienteInputDTO;
import br.com.fiap.beautymanagerapi.records.cliente.ClienteRequestModel;
import br.com.fiap.beautymanagerapi.records.estabelecimento.EstabelecimentoOutputDTO;
import br.com.fiap.beautymanagerapi.records.localizacao.LocalizacaoInputDTO;
import br.com.fiap.beautymanagerapi.records.localizacao.LocalizacaoResponseModel;
import br.com.fiap.beautymanagerapi.records.localizacao.LocalizacaoResquestModel;
import br.com.fiap.beautymanagerapi.usecase.localizacao.BuscarEstabelecimentosProximosUseCase;
import br.com.fiap.beautymanagerapi.usecase.localizacao.CriarLocalizacaoUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/estabelecimentos/localizacao")
public class LocalizacaoController {

    private final CriarLocalizacaoUseCase criarLocalizacaoUseCase;
    private final BuscarEstabelecimentosProximosUseCase buscarEstabelecimentosProximosUseCase;

    public LocalizacaoController(CriarLocalizacaoUseCase criarLocalizacaoUseCase, BuscarEstabelecimentosProximosUseCase buscarEstabelecimentosProximosUseCase) {
        this.criarLocalizacaoUseCase = criarLocalizacaoUseCase;
        this.buscarEstabelecimentosProximosUseCase = buscarEstabelecimentosProximosUseCase;
    }

    @PostMapping("/cadastro-localizacao")
    public ResponseEntity<LocalizacaoResponseModel> cadastrarLocalizacao(@Valid @RequestBody LocalizacaoResquestModel localizacaoResquestModel) {
        var localizacao = toInputDTO(localizacaoResquestModel);
        criarLocalizacaoUseCase.criarLocalizacao(localizacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(new LocalizacaoResponseModel(MensagemConstantes.LOCALIZACAO_CADASTRADA));
    }

    @GetMapping
    public ResponseEntity<List<EstabelecimentoOutputDTO>> buscarEstabelecimentos(
            @RequestParam double latitude,
            @RequestParam double longitude,
            @RequestParam double radius
    ){
        return ResponseEntity.ok(buscarEstabelecimentosProximosUseCase.buscarEstabelecimentosProximos(latitude,longitude,radius));
    }

    private LocalizacaoInputDTO toInputDTO(LocalizacaoResquestModel requestModel) {
        return new LocalizacaoInputDTO(
                requestModel.latitude(),
                requestModel.longitude(),
                requestModel.estabelecimentoId()
        );
    }
}
