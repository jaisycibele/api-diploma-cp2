package br.com.api_diploma.service;

import br.com.api_diploma.dto.CursoRequest;
import br.com.api_diploma.dto.CursoResponse;
import br.com.api_diploma.model.Curso;
import org.springframework.stereotype.Service;

@Service
public class CursoService {

    // Método para converter CursoRequest em Curso (Entidade)
    public Curso requestToCurso(CursoRequest cursoRequest) {
        Curso curso = new Curso();
        curso.setNome(cursoRequest.getNome());
        curso.setTipo(cursoRequest.getTipo());
        return curso;
    }

    // Método para converter Curso (Entidade) em CursoResponse
    public CursoResponse cursoToResponse(Curso curso) {
        CursoResponse cursoResponse = new CursoResponse();
        cursoResponse.setId(curso.getId());
        cursoResponse.setNome(curso.getNome());
        cursoResponse.setTipo(curso.getTipo());
        return cursoResponse;
    }

    // Método para converter Curso (Entidade) em CursoRequest
    public CursoRequest cursoToRequest(Curso curso) {
        CursoRequest cursoRequest = new CursoRequest();
        cursoRequest.setNome(curso.getNome());
        cursoRequest.setTipo(curso.getTipo());
        return cursoRequest;
    }
}
