package com.pruebaClaro.PruebaClaro.exceptions;

public class StudentNotFoundException extends RuntimeException{
    public StudentNotFoundException(Long id){
        super("El estudiante no puede ser encontrado en la Base de datos" + id);
    }
}
