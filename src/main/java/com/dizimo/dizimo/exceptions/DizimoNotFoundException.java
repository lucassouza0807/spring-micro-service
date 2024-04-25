package com.dizimo.dizimo.exceptions;


public class DizimoNotFoundException extends RuntimeException {
    public DizimoNotFoundException(Long id) {
        super("O id " + id + " não existe.");
    }

}
