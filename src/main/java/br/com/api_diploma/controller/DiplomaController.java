package br.com.api_diploma.controller;

import br.com.api_diploma.dto.DiplomaRequest;
import br.com.api_diploma.dto.DiplomaResponse;
import br.com.api_diploma.model.Diploma;
import br.com.api_diploma.repository.DiplomaRepository;
import br.com.api_diploma.service.DiplomaService;
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
@RequestMapping(value = "/diplomas")
public class DiplomaController {
    @Autowired
    private DiplomaRepository diplomaRepository;
    @Autowired
    private DiplomaService diplomaService;

    @PostMapping
    public ResponseEntity<DiplomaResponse> create(@Valid @RequestBody DiplomaRequest diplomaRequest) {
        Diploma diplomaConvertido = diplomaService.requestToDiploma(diplomaRequest);
        Diploma diplomaPersistido = diplomaRepository.save(diplomaConvertido);
        DiplomaResponse diplomaResponse = diplomaService.diplomaToResponse(diplomaPersistido);
        return new ResponseEntity<>(diplomaResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DiplomaResponse>> read() {
        List<Diploma> listaDiplomas = diplomaRepository.findAll();
        if (listaDiplomas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<DiplomaResponse> listaDiplomasResponses = new ArrayList<>();
        for (Diploma diploma : listaDiplomas) {
            DiplomaResponse diplomaResponse = diplomaService.diplomaToResponse(diploma);
            diplomaResponse.setLink(
                    linkTo(
                            methodOn(DiplomaController.class)
                                    .gerarTextoDiploma(diploma.getId())).withSelfRel());
            listaDiplomasResponses.add(diplomaResponse);
        }
        return new ResponseEntity<>(listaDiplomasResponses, HttpStatus.OK);
    }

    @GetMapping("/{id}/texto")
    public ResponseEntity<String> gerarTextoDiploma(@PathVariable String id) {
        Optional<Diploma> diplomaOptional = diplomaRepository.findById(id);
        if (diplomaOptional.isPresent()) {
            DiplomaResponse diplomaResponse = diplomaService.diplomaToResponse(diplomaOptional.get());
            String textoDiploma = diplomaService.gerarTextoDiploma(diplomaResponse);
            return new ResponseEntity<>(textoDiploma, HttpStatus.OK);
        } else {
            System.out.println("Diploma com ID " + id + " não encontrado.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DiplomaResponse> update(@PathVariable String id, @Valid @RequestBody DiplomaRequest diplomaRequest) {
        Optional<Diploma> diplomaPersistido = diplomaRepository.findById(id);
        if (diplomaPersistido.isPresent()) {
            Diploma diploma = diplomaService.requestToDiploma(diplomaRequest);
            diploma.setId(id);
            Diploma diplomaAtualizado = diplomaRepository.save(diploma);
            DiplomaResponse diplomaResponse = diplomaService.diplomaToResponse(diplomaAtualizado);
            return new ResponseEntity<>(diplomaResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        Optional<Diploma> diploma = diplomaRepository.findById(id);
        if (diploma.isPresent()) {
            diplomaRepository.delete(diploma.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
