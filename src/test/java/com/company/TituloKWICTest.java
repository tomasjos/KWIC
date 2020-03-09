package com.company;

import com.company.TituloKWIC;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TituloKWICTest {

    @BeforeEach
    void setUp() {
   }

    @AfterEach
    void tearDown() {
    }

    @Test
    void compareTo() {
    }

    @Test
    void testEquals() {
    }

    @Test
    void testHashCode() {
    }

    @Test
    void testToString() {
    }

    @Test
    void setTitulo() {


    }

    @Test
    void getTitulo() {
        String prueba="PRUEBA";
        TituloKWIC t=new TituloKWIC("prueba");
        assertEquals(prueba,t.getTitulo());
    }

    @Test
    void testCompareTo() {
        TituloKWIC titulok=new TituloKWIC("prueba");
        TituloKWIC titulokaux=new TituloKWIC("prueba");
        assertEquals(0,titulok.compareTo(titulokaux),0);
    }

    @Test
    void testEquals1() {
        TituloKWIC t=new TituloKWIC("prueba");
        assertTrue(t.equals(new TituloKWIC("Prueba")));
    }

    @Test
    void testHashCode1() {
        TituloKWIC t=new TituloKWIC("prueba");
        assertTrue(t.equals(new TituloKWIC("PRUEBA")));
    }

    @Test
    void testToString1() {
        TituloKWIC t=new TituloKWIC("prueba");
        assertEquals("PRUEBA",t.toString());

    }

    @Test
    void replace() {
        String titulo="EL ... PURPURA ";
        TituloKWIC t=new TituloKWIC("COLOR");
        assertEquals(titulo,t.replace("EL COLOR PURPURA"));
    }
}