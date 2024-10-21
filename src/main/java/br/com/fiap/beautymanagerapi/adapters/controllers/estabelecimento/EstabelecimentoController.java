package br.com.fiap.beautymanagerapi.adapters.controllers.estabelecimento;

import br.com.fiap.beautymanagerapi.adapters.presenters.estabelecimento.EstabelecimentoPresenter;
import br.com.fiap.beautymanagerapi.constantes.MensagemConstantes;
import br.com.fiap.beautymanagerapi.projection.EstabelecimentoProjection;
import br.com.fiap.beautymanagerapi.records.estabelecimento.EstabelecimentoInputDTO;
import br.com.fiap.beautymanagerapi.records.estabelecimento.EstabelecimentoRequestModel;
import br.com.fiap.beautymanagerapi.records.estabelecimento.EstabelecimentoResponseModel;
import br.com.fiap.beautymanagerapi.usecase.estabelecimento.BuscarEstabelecimentoUseCase;
import br.com.fiap.beautymanagerapi.usecase.estabelecimento.CriarEstabelecimentoUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static io.swagger.v3.oas.annotations.enums.ParameterIn.QUERY;

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

    @GetMapping("/busca-por-id/{id}")
    public ResponseEntity<EstabelecimentoResponseModel> buscarEstabelecimentoPorId(@PathVariable Long id) {
        var estabelecimento = buscarEstabelecimentoUseCase.buscarEstabelecimentoPorId(id);
        return ResponseEntity.ok(estabelecimentoPresenter.toResponseModelGet(estabelecimento));
    }

    @GetMapping("/busca-por-nome/{nome}")
    public ResponseEntity<List<EstabelecimentoResponseModel>> buscarEstabelecimentos(@PathVariable String nome) {
        var estabelecimento = buscarEstabelecimentoUseCase.buscarEstabelecimentosPorNome(nome);
        return ResponseEntity.ok(estabelecimentoPresenter.toResponseModelGet(estabelecimento));
    }

    @Operation(
            parameters = {
                    @Parameter(in = QUERY, name = "page",
                            content = @Content(schema = @Schema(type = "integer", defaultValue = "0")),
                            description = "Representa a página retornada"
                    ),
                    @Parameter(in = QUERY, name = "size",
                            content = @Content(schema = @Schema(type = "integer", defaultValue = "5")),
                            description = "Representa o total de elementos por página"
                    ),
                    @Parameter(in = QUERY, name = "sort", hidden = true,
                            array = @ArraySchema(schema = @Schema(type = "string", defaultValue = "nome,asc")),
                            description = "Representa a ordenação dos resultados. Aceita multiplos critérios de ordenação são suportados.")
            })
    @GetMapping
    public ResponseEntity<Page<EstabelecimentoProjection>> buscarTodosEstabelecimentos(@Parameter(hidden = true)
                                                                       @PageableDefault(size = 5, sort = {"nome"}) Pageable pageable){
        Page<EstabelecimentoProjection> estabelecimentos = buscarEstabelecimentoUseCase.buscarTodosEstabelecimentos(pageable);
        return ResponseEntity.ok(estabelecimentos);
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
