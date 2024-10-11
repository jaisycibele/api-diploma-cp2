package br.com.api_diploma.controller;

import br.com.api_diploma.dto.CursoRequest;
import br.com.api_diploma.dto.CursoResponse;
import br.com.api_diploma.model.Curso;
import br.com.api_diploma.repository.CursoRepository;
import br.com.api_diploma.service.CursoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/cursos")
public class CursoController {
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private CursoService cursoService;

    @PostMapping
    public ResponseEntity<CursoResponse> create(@Valid @RequestBody CursoRequest cursoRequest) {
        Curso cursoConvertido = cursoService.requestToCurso(cursoRequest);
        Curso cursoPersistido = cursoRepository.save(cursoConvertido);
        CursoResponse cursoResponse = cursoService.cursoToResponse(cursoPersistido);
        return new ResponseEntity<>(cursoResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CursoResponse>> read() {
        List<Curso> listaCursos = cursoRepository.findAll();
        if (listaCursos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<CursoResponse> listaCursosResponses = new ArrayList<>();
        for (Curso curso : listaCursos) {
            CursoResponse cursoResponse = cursoService.cursoToResponse(curso);
            cursoResponse.setLink(
                    linkTo(
                            methodOn(CursoController.class)
                                    .read(curso.getId())).withSelfRel());
            listaCursosResponses.add(cursoResponse);
        }
        return new ResponseEntity<>(listaCursosResponses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoResponse> read(@PathVariable Long id) {
        Optional<Curso> curso = cursoRepository.findById(id);
        if(curso.isPresent()) {
            CursoResponse cursoResponse = cursoService.cursoToResponse(curso.get());
            cursoResponse.setLink(
                    linkTo(
                            methodOn(CursoController.class)
                                    .read()).withRel("Lista de cursos"));
            return new ResponseEntity<>(cursoResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("{id}")
    public ResponseEntity<CursoResponse> update(@PathVariable Long id, @Valid @RequestBody CursoRequest cursoRequest) {
        Optional<Curso> cursoPersistido = cursoRepository.findById(id);
        if (cursoPersistido.isPresent()) {
            Curso curso = cursoService.requestToCurso(cursoRequest);
            curso.setId(id);
            Curso cursoAtualizado = cursoRepository.save(curso);
            CursoResponse cursoResponse = cursoService.cursoToResponse(cursoAtualizado);
            return new ResponseEntity<>(cursoResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<Curso> curso = cursoRepository.findById(id);
        if (curso.isPresent()) {
            cursoRepository.delete(curso.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
