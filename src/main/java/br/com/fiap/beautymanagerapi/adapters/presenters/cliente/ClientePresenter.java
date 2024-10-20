package br.com.fiap.beautymanagerapi.adapters.presenters.cliente;

import br.com.fiap.beautymanagerapi.constantes.MensagemConstantes;
import br.com.fiap.beautymanagerapi.records.cliente.ClienteOutputDTO;
import br.com.fiap.beautymanagerapi.records.cliente.ClienteResponseModel;
import org.springframework.stereotype.Component;

@Component
public class ClientePresenter {

    public ClienteResponseModel toResponseModel(ClienteOutputDTO outputDTO, boolean consulta) {
        // Exemplo de formatação do telefone (coloca o DDD entre parênteses)
        String telefoneFormatado = formatarTelefone(outputDTO.telefone());

        // Adiciona uma mensagem de sucesso
        String mensagemSucesso = MensagemConstantes.CLIENTE_CADASTRADO_SUCESSO;

        if (consulta) {
            return new ClienteResponseModel(
                    outputDTO.id(),
                    outputDTO.nome(),
                    outputDTO.email(),
                    telefoneFormatado,
                    ""
            );
        }
        return new ClienteResponseModel(
                outputDTO.id(),
                outputDTO.nome(),
                outputDTO.email(),
                telefoneFormatado,
                mensagemSucesso
        );
    }


    private String formatarTelefone(String telefone) {

        if (telefone.length() == 11) {
            String ddd = telefone.substring(0, 2);
            String parte1 = telefone.substring(2, 7);
            String parte2 = telefone.substring(7);
            return "(" + ddd + ") " + parte1 + "-" + parte2;
        }
        // Retorna o telefone como está se não for no formato esperado
        return telefone;
    }
}

