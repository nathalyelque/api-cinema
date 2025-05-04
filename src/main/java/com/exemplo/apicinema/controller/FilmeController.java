package com.exemplo.apicinema.controller;

import com.exemplo.apicinema.domain.FilmeDomain;
import com.exemplo.apicinema.dto.FilmeRecordDto;
import com.exemplo.apicinema.repository.DiretorRepository;
import com.exemplo.apicinema.repository.FilmeRepository;
import com.exemplo.apicinema.service.CadastroFilmeService;
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
public class FilmeController {

    @Autowired
    FilmeRepository filmeRepository;
    @Autowired
    DiretorRepository diretorRepository;

    @Autowired
    CadastroFilmeService cadastroFilmeService;

    @PostMapping("/filmes")
    public ResponseEntity<FilmeDomain> salvarFilme(@RequestBody @Valid FilmeRecordDto filmeRecordDto) throws Exception {
        var filmeDomain = new FilmeDomain();
        BeanUtils.copyProperties(filmeRecordDto, filmeDomain);

        cadastroFilmeService.cadastrar(filmeDomain, filmeRecordDto.diretor().idDiretor());

        return ResponseEntity.status(HttpStatus.CREATED).body(filmeRepository.save(filmeDomain));
    }
    @GetMapping("/filmes")
    public ResponseEntity<List<FilmeDomain>> obterTodosFilmes(){
        return ResponseEntity.status(HttpStatus.OK).body(filmeRepository.findAll());
    }
    public ResponseEntity<Object> obterfilme(@PathVariable(value="id") UUID id){
        Optional<FilmeDomain> filme0 = filmeRepository.findById(id);
        if(filme0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Filme não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(filme0.get());
    }
}
