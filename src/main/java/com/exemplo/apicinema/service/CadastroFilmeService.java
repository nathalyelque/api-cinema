package com.exemplo.apicinema.service;

import com.exemplo.apicinema.domain.DiretorDomain;
import com.exemplo.apicinema.domain.FilmeDomain;
import com.exemplo.apicinema.repository.DiretorRepository;
import com.exemplo.apicinema.repository.FilmeRepository;
import com.exemplo.apicinema.service.exceptions.ServiceException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
public class CadastroFilmeService {

    private final FilmeRepository filmeRepository;

    private final DiretorRepository diretorRepository;

    public CadastroFilmeService(FilmeRepository filmeRepository, DiretorRepository diretorRepository) {
        this.filmeRepository = filmeRepository;
        this.diretorRepository = diretorRepository;
    }

    public FilmeDomain cadastrar(FilmeDomain filmeDomain, UUID idDiretor) throws Exception {

        var diretorDomain = diretorRepository.findById(idDiretor);

        validarDiretor(diretorDomain);

        validarAno(filmeDomain.getAno());

        filmeDomain.setDiretor(diretorDomain.get());

        return filmeRepository.save(filmeDomain);
    }

    private void validarAno(int ano) throws Exception {
        var anoAtual = LocalDate.now().getYear();

        if (ano > anoAtual)
            throw new ServiceException("ano", "Ano informado não pode ser no futuro");

        if (ano < anoAtual - 3)
            throw new ServiceException("ano", "Ano informado não pode ser anterior a 3 anos do ano atual");
    }

    private void validarDiretor(Optional<DiretorDomain> diretorDomain) throws Exception {
        if (diretorDomain.isEmpty())
            throw new ServiceException("diretor", "Diretor não encontrado");

        if (diretorDomain.get().isAtivo() == false)
            throw new ServiceException("diretor", "Diretor não está ativo");
    }
}
