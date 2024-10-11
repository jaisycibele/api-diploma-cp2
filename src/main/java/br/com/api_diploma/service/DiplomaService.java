package br.com.api_diploma.service;

import br.com.api_diploma.dto.DiplomaRequest;
import br.com.api_diploma.dto.DiplomaResponse;
import br.com.api_diploma.model.Diploma;
import br.com.api_diploma.model.Diplomado;
import br.com.api_diploma.model.Curso;
import org.springframework.stereotype.Service;

@Service
public class DiplomaService {

    // Método para converter DiplomaRequest em Diploma (Entidade)
    public Diploma requestToDiploma(DiplomaRequest diplomaRequest) {
        Diploma diploma = new Diploma();
        diploma.setDataConclusao(diplomaRequest.getDataConclusao());
        diploma.setSexo(diplomaRequest.getSexo());
        diploma.setNomeReitor(diplomaRequest.getNomeReitor());
        diploma.setDiplomado(diplomaRequest.getDiplomado());
        diploma.setCurso(diplomaRequest.getCurso());
        return diploma;
    }

    // Método para converter Diploma (Entidade) em DiplomaResponse
    public DiplomaResponse diplomaToResponse(Diploma diploma) {
        DiplomaResponse diplomaResponse = new DiplomaResponse();
        Diplomado diplomado = diploma.getDiplomado();
        Curso curso = diploma.getCurso();

        diplomaResponse.setNomeDiplomado(diplomado.getNome());
        diplomaResponse.setNacionalidade(diplomado.getNacionalidade());
        diplomaResponse.setNaturalidade(diplomado.getNaturalidade());
        diplomaResponse.setNumeroRg(diplomado.getRg());
        diplomaResponse.setNomeCurso(curso.getNome());
        diplomaResponse.setTipoCurso(curso.getTipo().name());
        diplomaResponse.setDataConclusao(diploma.getDataConclusao());
        diplomaResponse.setNomeReitor(diploma.getNomeReitor());
        diplomaResponse.setSexoReitor(diploma.getSexo());

        return diplomaResponse;
    }

    // Método para converter Diploma (Entidade) em DiplomaRequest
    public DiplomaRequest diplomaToRequest(Diploma diploma) {
        DiplomaRequest diplomaRequest = new DiplomaRequest();
        diplomaRequest.setDataConclusao(diploma.getDataConclusao());
        diplomaRequest.setSexo(diploma.getSexo());
        diplomaRequest.setNomeReitor(diploma.getNomeReitor());
        diplomaRequest.setDiplomado(diploma.getDiplomado());
        diplomaRequest.setCurso(diploma.getCurso());
        return diplomaRequest;
    }
}
