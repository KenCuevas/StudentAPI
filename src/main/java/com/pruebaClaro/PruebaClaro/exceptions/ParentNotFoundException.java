package com.pruebaClaro.PruebaClaro.exceptions;

public class ParentNotFoundException extends  RuntimeException{
    public ParentNotFoundException(Long id){
        super("El padre no pudo ser ubicado en la base de datos.");
    }
}
