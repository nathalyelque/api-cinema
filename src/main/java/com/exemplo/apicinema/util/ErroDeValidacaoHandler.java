package com.exemplo.apicinema.util;

import com.exemplo.apicinema.service.exceptions.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErroDeValidacaoHandler {

    @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErroDeValidacao> tratar(MethodArgumentNotValidException excecao) {
        List<ErroDeValidacao> erros = new ArrayList<>();

        List<FieldError> listaDeCampoDeErro = excecao.getBindingResult().getFieldErrors();

        for (FieldError campoDeErro : listaDeCampoDeErro) {
            erros.add(new ErroDeValidacao(campoDeErro.getField(), campoDeErro.getDefaultMessage()));
        }

        return erros;
    }

    @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(ServiceException.class)
    public ErroDeValidacao tratar(ServiceException excecao){
        var erro = new ErroDeValidacao(excecao.getCampo(), excecao.getMessage());

        return erro;
    }
}
