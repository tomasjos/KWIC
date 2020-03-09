package com.company;

public class driversKWIC {

    public static void main(String[] args) {
        // write your code here
        //palnosig es la lista de palabras no significativas, es decir, aquellas que no serán elementos del índice
        String palNoSig = "el,la,los,las,al,un,una,unos,unas,y,o,a,ante,bajo,cabe,de,desde,en,entre,hacia,hasta,para,por,sin,si,no";
        //frases es la lista de strings que corresponden a titulos de peliculas
        String []frases = {
                "El color del dinero",
                "Color púrpura",
                "Misión imposible",
                "La misión",
                "La rosa púrpura del Cairo",
                "El dinero llama al dinero",
                "La rosa del azafrán",
                "El nombre de la rosa",
                "Toma el dinero y corre"

        };
        //en primer lugar crearemos un glosario, que es una lista con las palabras no significativas
        KWIC glosario = new KWIC();
        glosario.palabrasNoSignificativas(palNoSig);
        //a continuación, buscaremos en cada frase las palabras significativas y crearemos un indice
        //por cada una de ellas, asociandole las peliculas correspondientes. eso lo hace generarfrases()
        int i;
        for ( String frase:frases){
            glosario.generarFrases(frase);
        }
        //ToString primero imprime la lista de palabras no significativas, y luego imprime la lista de indices
        System.out.println(glosario.toString());

    }

}