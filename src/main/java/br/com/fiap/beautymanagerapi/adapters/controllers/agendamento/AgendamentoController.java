package br.com.fiap.beautymanagerapi.adapters.controllers.agendamento;

import br.com.fiap.beautymanagerapi.adapters.presenters.agendamento.AgendamentoPresenter;
import br.com.fiap.beautymanagerapi.records.agendamento.AgendamentoInputDto;
import br.com.fiap.beautymanagerapi.records.agendamento.AgendamentoRequestModel;
import br.com.fiap.beautymanagerapi.records.agendamento.AgendamentoRequestModelStatus;
import br.com.fiap.beautymanagerapi.records.agendamento.AgendamentoResponseModel;
import br.com.fiap.beautymanagerapi.usecase.agendamento.CriarAgendamentoUseCase;
import br.com.fiap.beautymanagerapi.usecase.agendamento.BuscarAgendamentoUseCase;
import br.com.fiap.beautymanagerapi.usecase.agendamento.AtualizarAgendamentoUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/agendamentos")
public class AgendamentoController {

    private final CriarAgendamentoUseCase criarAgendamentoUseCase;
    private final BuscarAgendamentoUseCase buscarAgendamentoUseCase;
    private final AtualizarAgendamentoUseCase atualizarAgendamentoUseCase;
    private final AgendamentoPresenter presenter;

    public AgendamentoController(CriarAgendamentoUseCase criarAgendamentoUseCase,
                                 BuscarAgendamentoUseCase buscarAgendamentoUseCase,
                                 AtualizarAgendamentoUseCase atualizarAgendamentoUseCase,
                                 AgendamentoPresenter presenter) {
        this.criarAgendamentoUseCase = criarAgendamentoUseCase;
        this.buscarAgendamentoUseCase = buscarAgendamentoUseCase;
        this.atualizarAgendamentoUseCase = atualizarAgendamentoUseCase;
        this.presenter = presenter;
    }

    @PostMapping
    public ResponseEntity<AgendamentoResponseModel> criarAgendamento(@Valid @RequestBody AgendamentoRequestModel requestModel) {
        var agendamento = criarAgendamentoUseCase.criarAgendamento(toInputDTO(requestModel));
        return ResponseEntity.status(HttpStatus.CREATED).body(presenter.toResponseModel(agendamento));
    }

    @GetMapping("/{idEstabelecimento}")
    public ResponseEntity<List<AgendamentoResponseModel>> buscarAgendamento(@PathVariable Long idEstabelecimento) {
        var agendamento = buscarAgendamentoUseCase.buscarAgendamentosEstabelecimento(idEstabelecimento);
        return ResponseEntity.ok().body(presenter.toResponseModelList(agendamento));
    }

    @PatchMapping("/{idAgendamento}")
    public ResponseEntity<Void> atualizarAgendamento(@PathVariable Long idAgendamento, @RequestBody AgendamentoRequestModelStatus requestModelStatus) {
        atualizarAgendamentoUseCase.atualizarStatus(idAgendamento, requestModelStatus.status());
        return ResponseEntity.noContent().build();
    }

    private AgendamentoInputDto toInputDTO(AgendamentoRequestModel requestModel) {
        return new AgendamentoInputDto(
                requestModel.clienteId(),
                requestModel.profissionalId(),
                requestModel.servicoId(),
                requestModel.estabelecimentoId(),
                requestModel.dataHora(),
                requestModel.status()
        );
    }
}
