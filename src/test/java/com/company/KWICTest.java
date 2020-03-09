package com.company;

import org.junit.jupiter.api.Test;
import sun.reflect.generics.tree.Tree;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class KWICTest {

    @Test
    void palabrasNoSignificativas() {

            Set<TituloKWIC> conjunto=new TreeSet<TituloKWIC>();

            String[] strings ={"el","la","los","las","al","un","una","unos","unas","y","o","a","ante","bajo","cabe","de","desde","en","entre","hacia","hasta","para","por","sin","si","no"};
            for (String string:strings) {
                TituloKWIC t=new TituloKWIC(string);
                conjunto.add(t);
            }

            KWIC k =new KWIC();
            String palNoSig = "el,la,los,las,al,un,una,unos,unas,y,o,a,ante,bajo,cabe,de,desde,en,entre,hacia,hasta,para,por,sin,si,no";
            k.palabrasNoSignificativas(palNoSig);
            assertEquals(conjunto,k.getNoClaves());

    }

    @Test
    void generarFrases() {
         Map<TituloKWIC,Set<String>> glosario = new TreeMap<TituloKWIC, Set<String>>();
         Set<String> t=new TreeSet<String>();
         t.add("LA ROSA DEL ...");
         glosario.put(new TituloKWIC("AZAFRÁN"),t);
        Set<String> q=new TreeSet<String>();
        q.add("LA ROSA PURPURA DEL ...");
        glosario.put(new TituloKWIC("CAIRO"),q);
        Set<String> s=new TreeSet<String>();
        s.add("EL COLOR ... DINERO");
        s.add("LA ROSA ... AZAFRÁN");
        s.add("LA ROSA PÚRPURA ... CAIRO");
        glosario.put(new TituloKWIC("DEL"),s);

        Set<String> u=new TreeSet<String>();
        u.add("EL ... LLAMA AL ...");
        u.add("EL COLOR DEL ... ");
        u.add("TOMA EL ... Y CORRE");
        glosario.put(new TituloKWIC("DEL"),u);

        Set<String> v=new TreeSet<String>();
        v.add("MISION ...");
        glosario.put(new TituloKWIC("IMPOSIBLE"),v);

        Set<String> w=new TreeSet<String>();
        w.add("EL DINERO  ... AL DINERO");
        glosario.put(new TituloKWIC("LLAMA"),w);

        Set<String> x=new TreeSet<String>();
        x.add(" ... IMPOSIBLE");
        x.add("LA ...");
        glosario.put(new TituloKWIC("MISIÓN"),x);

        Set<String> y=new TreeSet<String>();
        y.add(" ... DE LA ROSA");
        glosario.put(new TituloKWIC("NOMBRE"),y);


        Set<String> z=new TreeSet<String>();
        z.add(" COLOR ... ");
        z.add("LA ROSA ... DEL CAIRO");
        glosario.put(new TituloKWIC("PÚRPURA"),x);

        Set<String> a=new TreeSet<String>();
        a.add("EL NOMBRE DE LA  ... ");
        a.add("LA ... ROSA DEL AZAFRÁN");
        a.add("LA ... PÚRPURA DEL CAIRO");
        glosario.put(new TituloKWIC("ROSA"),a);

        Set<String> b=new TreeSet<String>();
        b.add(" ... EL DINERO Y CORRE");
        glosario.put(new TituloKWIC("TOMA"),b);

        KWIC k =new KWIC();
        String palNoSig = "el,la,los,las,al,un,una,unos,unas,y,o,a,ante,bajo,cabe,de,desde,en,entre,hacia,hasta,para,por,sin,si,no";
        k.palabrasNoSignificativas(palNoSig);
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
        int i;
        for ( String frase:frases){
            k.generarFrases(frase);
        }
        assertEquals(glosario,k.getGlosario());
    }
}