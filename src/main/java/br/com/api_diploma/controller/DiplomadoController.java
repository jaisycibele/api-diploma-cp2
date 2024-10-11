package br.com.api_diploma.controller;

import br.com.api_diploma.dto.DiplomadoRequest;
import br.com.api_diploma.dto.DiplomadoResponse;
import br.com.api_diploma.model.Diplomado;
import br.com.api_diploma.repository.DiplomadoRepository;
import br.com.api_diploma.service.DiplomadoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

   // @GetMapping
    //public ResponseEntity<List<DiplomadoResponse>> read() {
      //  List<Diplomado> listaDiplomados = diplomadoRepository.findAll();
        //if (listaDiplomados.isEmpty()){
          //  return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        //}
    //}

















}
