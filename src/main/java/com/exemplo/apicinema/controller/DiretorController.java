package com.exemplo.apicinema.controller;

import com.exemplo.apicinema.domain.DiretorDomain;
import com.exemplo.apicinema.dto.DiretorRecordDto;
import com.exemplo.apicinema.repository.DiretorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class DiretorController {

    @Autowired
    DiretorRepository diretorRepository;

    @PostMapping("/diretores")
    public ResponseEntity<DiretorDomain> salvarDiretor(@RequestBody @Valid DiretorRecordDto diretorRecordDto){
        var diretorDomain = new DiretorDomain();
        BeanUtils.copyProperties(diretorRecordDto, diretorDomain);
        return ResponseEntity.status(HttpStatus.CREATED).body(diretorRepository.save(diretorDomain));
    }
    @GetMapping("/diretores")
    public ResponseEntity<List<DiretorDomain>> obterTodosDiretores(){
        return ResponseEntity.status(HttpStatus.OK).body(diretorRepository.findAll());
    }
    @GetMapping("/diretores/{id}")
    public ResponseEntity<Object> obterDiretor(@PathVariable(value="id") UUID id){
        Optional<DiretorDomain> diretor0 = diretorRepository.findById(id);
        if(diretor0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Diretor não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(diretor0.get());
    }
}
