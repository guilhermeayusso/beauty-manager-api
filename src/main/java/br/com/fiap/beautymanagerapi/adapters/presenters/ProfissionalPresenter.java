package br.com.fiap.beautymanagerapi.adapters.presenters;

import br.com.fiap.beautymanagerapi.records.cliente.ClienteOutputDTO;
import br.com.fiap.beautymanagerapi.records.cliente.ClienteResponseModel;
import br.com.fiap.beautymanagerapi.records.profissional.ProfissionalOutputDTO;
import br.com.fiap.beautymanagerapi.records.profissional.ProfissionalResponseModel;
import org.springframework.stereotype.Component;

@Component
public class ProfissionalPresenter {


    public ProfissionalResponseModel toResponseModel(ProfissionalOutputDTO outputDTO){

        return new ProfissionalResponseModel(
                outputDTO.id(),
                outputDTO.nome(),
                outputDTO.especialidades(),
                outputDTO.statusProfissional(),
                outputDTO.estabelecimento(),
                null
        );

    }
}
