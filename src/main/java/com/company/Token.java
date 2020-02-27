package com.company;
//creamos un interfaz token que tendrá los metodos getter y setter de TituloKWIC y así hacer aun más independiente la clase básica TituloKWIC,
//tal y como se pide en el enunciado
public interface Token {
    String getTitulo();

    void setTitulo(String t);

}
