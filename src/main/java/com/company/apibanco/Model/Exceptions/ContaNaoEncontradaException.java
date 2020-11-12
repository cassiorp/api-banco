package com.company.apibanco.Model.Exceptions;

public class ContaNaoEncontradaException extends RuntimeException{
    public ContaNaoEncontradaException(String message) {
        super(message);
    }
}
