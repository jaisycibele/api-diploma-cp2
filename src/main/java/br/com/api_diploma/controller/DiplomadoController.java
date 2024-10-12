package br.com.api_diploma.controller;

import br.com.api_diploma.dto.DiplomadoRequest;
import br.com.api_diploma.dto.DiplomadoResponse;
import br.com.api_diploma.model.Curso;
import br.com.api_diploma.model.Diplomado;
import br.com.api_diploma.repository.DiplomadoRepository;
import br.com.api_diploma.service.DiplomadoService;
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
@RequestMapping(value = "/diplomados")
public class DiplomadoController {
    @Autowired
    private DiplomadoRepository diplomadoRepository;
    @Autowired
    private DiplomadoService diplomadoService;

    @PostMapping
    public ResponseEntity<DiplomadoResponse> create(@Valid @RequestBody DiplomadoRequest diplomadoRequest) {
        Diplomado diplomadoConvertido = diplomadoService.requestToDiplomado(diplomadoRequest);
        Diplomado diplomadoPersistido = diplomadoRepository.save(diplomadoConvertido);
        DiplomadoResponse diplomadoResponse = diplomadoService.diplomadoToResponse(diplomadoPersistido);
        return new ResponseEntity<>(diplomadoResponse, HttpStatus.CREATED);
    }

   @GetMapping
    public ResponseEntity<List<DiplomadoResponse>> read() {
        List<Diplomado> listaDiplomados = diplomadoRepository.findAll();
        if (listaDiplomados.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<DiplomadoResponse> listaDiplomadosResponses = new ArrayList<>();
        for (Diplomado diplomado : listaDiplomados) {
            DiplomadoResponse diplomadoResponse = diplomadoService.diplomadoToResponse(diplomado);
            diplomadoResponse.setLink(
                    linkTo(
                            methodOn(DiplomadoController.class)
                                    .read(diplomado.getId())).withSelfRel());
            listaDiplomadosResponses.add(diplomadoResponse);
        }
        return new ResponseEntity<>(listaDiplomadosResponses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiplomadoResponse> read(@PathVariable Long id) {
        Optional<Diplomado> diplomado = diplomadoRepository.findById(id);
        if (diplomado.isPresent()) {
            DiplomadoResponse diplomadoResponse = diplomadoService.diplomadoToResponse(diplomado.get());
            diplomadoResponse.setLink(
                    linkTo(
                            methodOn(DiplomadoController.class)
                                    .read()).withRel("Lista de diplomados"));
            return new ResponseEntity<>(diplomadoResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DiplomadoResponse> update(@PathVariable Long id, @Valid @RequestBody DiplomadoRequest diplomadoRequest) {
        Optional<Diplomado> diplomadoPersistido = diplomadoRepository.findById(id);
        if (diplomadoPersistido.isPresent()) {
            Diplomado diplomado = diplomadoService.requestToDiplomado(diplomadoRequest);
            diplomado.setId(id);
            Diplomado diplomadoAtualizado = diplomadoRepository.save(diplomado);
            DiplomadoResponse diplomadoResponse = diplomadoService.diplomadoToResponse(diplomadoAtualizado);
            return new ResponseEntity<>(diplomadoResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<Diplomado> diplomado = diplomadoRepository.findById(id);
        if (diplomado.isPresent()) {
            diplomadoRepository.delete(diplomado.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

















}
