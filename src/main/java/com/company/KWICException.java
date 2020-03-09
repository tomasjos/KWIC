package com.company;
// generamos una clase KWICException derivada de RunTimeException, que se empleará en aquellas operaciones
// en las que haya que trabajar con elementos de la clase TituloKWIC
public class KWICException extends RuntimeException {

    public KWICException(){
        super("Error. ");
    }

    public KWICException(String msg){
        super("Error. "+msg);
    }
}
