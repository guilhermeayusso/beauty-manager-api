package br.com.fiap.beautymanagerapi.adapters.presenters.agendamento;

import br.com.fiap.beautymanagerapi.records.agendamento.AgendamentoResponseModel;
import br.com.fiap.beautymanagerapi.records.agendamento.AgendamentoOutputDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AgendamentoPresenter {

    public AgendamentoResponseModel toResponseModel(AgendamentoOutputDto outputDto) {
        return new AgendamentoResponseModel(
                outputDto.agendamentoId(),
                outputDto.dataHora(),
                outputDto.status()
        );
    }

    public List<AgendamentoResponseModel> toResponseModelList(List<AgendamentoOutputDto> outputDtoList) {
        return outputDtoList.stream()
                .map(this::toResponseModel)
                .collect(Collectors.toList());
    }
}
