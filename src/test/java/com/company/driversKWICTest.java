package com.company;

import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class driversKWICTest {

    @Test
    void main() {
        Set<TituloKWIC> nC = new TreeSet<TituloKWIC>();

        String[] palNoSigprueba ={"el","la","los","las","al","un","una","unos","unas","y","o","a","ante","bajo","cabe","de","desde","en","entre","hacia","hasta","para","por","sin","si","no"};

        for (String s:palNoSigprueba)
        {
            nC.add(new TituloKWIC((s)));
        }
        KWIC glosario = new KWIC();
        String palNoSig = "el,la,los,las,al,un,una,unos,unas,y,o,a,ante,bajo,cabe,de,desde,en,entre,hacia,hasta,para,por,sin,si,no";

        glosario.palabrasNoSignificativas(palNoSig);
        assertEquals(nC,glosario.getNoClaves());
    /*    String[] strings = new String[1];
        strings[0]="";
        driversKWIC.main(strings);
        */

    }
}