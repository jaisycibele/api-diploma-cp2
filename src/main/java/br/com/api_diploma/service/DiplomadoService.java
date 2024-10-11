package br.com.api_diploma.service;

import br.com.api_diploma.dto.DiplomadoRequest;
import br.com.api_diploma.dto.DiplomadoResponse;
import br.com.api_diploma.model.Diplomado;
import org.springframework.stereotype.Service;

@Service
public class DiplomadoService {

    // Método para converter DiplomadoRequest em Diplomado (Entidade)
    public Diplomado requestToDiplomado(DiplomadoRequest diplomadoRequest) {
        Diplomado diplomado = new Diplomado();
        diplomado.setNome(diplomadoRequest.getNome());
        diplomado.setNacionalidade(diplomadoRequest.getNacionalidade());
        diplomado.setNaturalidade(diplomadoRequest.getNaturalidade());
        diplomado.setRg(diplomadoRequest.getRg());
        return diplomado;
    }

    // Método para converter Diplomado (Entidade) em DiplomadoResponse
    public DiplomadoResponse diplomadoToResponse(Diplomado diplomado) {
        DiplomadoResponse diplomadoResponse = new DiplomadoResponse();
        diplomadoResponse.setId(diplomado.getId());
        diplomadoResponse.setNome(diplomado.getNome());
        diplomadoResponse.setNacionalidade(diplomado.getNacionalidade());
        diplomadoResponse.setNaturalidade(diplomado.getNaturalidade());
        diplomadoResponse.setRg(diplomado.getRg());
        return diplomadoResponse;
    }

    // Método para converter Diplomado (Entidade) em DiplomadoRequest
    public DiplomadoRequest diplomadoToRequest(Diplomado diplomado) {
        DiplomadoRequest diplomadoRequest = new DiplomadoRequest();
        diplomadoRequest.setNome(diplomado.getNome());
        diplomadoRequest.setNacionalidade(diplomado.getNacionalidade());
        diplomadoRequest.setNaturalidade(diplomado.getNaturalidade());
        diplomadoRequest.setRg(diplomado.getRg());
        return diplomadoRequest;
    }
}
