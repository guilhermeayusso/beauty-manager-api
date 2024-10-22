package br.com.fiap.beautymanagerapi.adapters.presenters.endereco;

import br.com.fiap.beautymanagerapi.records.endereco.EnderecoOutputDTO;
import br.com.fiap.beautymanagerapi.records.endereco.EnderecoResponseModel;
import org.springframework.stereotype.Component;

@Component
public class EnderecoPresenter {

    public EnderecoResponseModel toResponseModel (EnderecoOutputDTO outputDTO){

        return new EnderecoResponseModel(
                outputDTO.id(),
                outputDTO.cep(),
                outputDTO.logradouro(),
                outputDTO.numero(),
                outputDTO.complemento(),
                outputDTO.bairro(),
                outputDTO.cidade(),
                outputDTO.uf()
        );
    }
}
