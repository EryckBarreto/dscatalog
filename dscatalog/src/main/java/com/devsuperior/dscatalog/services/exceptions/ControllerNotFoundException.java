package com.devsuperior.dscatalog.services.exceptions;

public class ControllerNotFoundException extends RuntimeException{

    public ControllerNotFoundException(String msg) {
        super(msg);
    }

}
