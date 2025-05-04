package com.exemplo.apicinema.service.exceptions;

public class ServiceException extends Exception {

    private String campo;

    public ServiceException(String campo, String message) {
        super(message);

        this.campo = campo;
    }

    public String getCampo() {
        return campo;
    }
}
