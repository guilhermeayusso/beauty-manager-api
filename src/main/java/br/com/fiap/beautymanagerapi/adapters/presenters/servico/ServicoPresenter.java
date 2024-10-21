package br.com.fiap.beautymanagerapi.adapters.presenters.servico;

import br.com.fiap.beautymanagerapi.records.servico.ServicoOutputDTO;
import br.com.fiap.beautymanagerapi.records.servico.ServicoResponseModel;
import org.springframework.stereotype.Component;

@Component
public class ServicoPresenter {

    public ServicoResponseModel toResponseModel (ServicoOutputDTO outputDTO){

        return new ServicoResponseModel(
                outputDTO.id(),
                outputDTO.nome(),
                outputDTO.descricao(),
                outputDTO.preco()
        );
    }
}
