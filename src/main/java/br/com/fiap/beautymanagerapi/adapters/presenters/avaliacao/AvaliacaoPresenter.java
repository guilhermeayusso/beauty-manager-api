package br.com.fiap.beautymanagerapi.adapters.presenters.avaliacao;

import br.com.fiap.beautymanagerapi.records.avaliacao.AvaliacaoOutputDTO;
import br.com.fiap.beautymanagerapi.records.avaliacao.AvaliacaoResponseModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AvaliacaoPresenter {

    public AvaliacaoResponseModel toResponseModel(AvaliacaoOutputDTO outputDTO) {

        return new AvaliacaoResponseModel(
                outputDTO.autor(),
                outputDTO.nota(),
                outputDTO.comentario()
        );

    }

    public List<AvaliacaoResponseModel> toResponseModelList(List<AvaliacaoOutputDTO> outputDTO) {

        return outputDTO.stream()
                .map(a -> new AvaliacaoResponseModel(
                        a.autor(),
                        a.nota(),
                        a.comentario()
                )).collect(Collectors.toList());

    }
}
