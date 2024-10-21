package br.com.fiap.beautymanagerapi.adapters.presenters.estabelecimento;

import br.com.fiap.beautymanagerapi.constantes.MensagemConstantes;
import br.com.fiap.beautymanagerapi.records.estabelecimento.EstabelecimentoOutputDTO;
import br.com.fiap.beautymanagerapi.records.estabelecimento.EstabelecimentoResponseModel;
import org.springframework.stereotype.Component;

@Component
public class EstabelecimentoPresenter {


    public EstabelecimentoResponseModel toResponseModel(EstabelecimentoOutputDTO outputDTO) {

        // Adiciona uma mensagem de sucesso
        String mensagem = MensagemConstantes.ESTABELECIMENTO_CADASTRO_ETAPA_INICIAL_CONCLUIDA;


        return new EstabelecimentoResponseModel(
                outputDTO.id(),
                outputDTO.nome(),
                mensagem
        );
    }
}
