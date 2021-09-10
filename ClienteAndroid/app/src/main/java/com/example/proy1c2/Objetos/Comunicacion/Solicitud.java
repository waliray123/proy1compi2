/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.proy1c2.Objetos.Comunicacion;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user-ubunto
 */
public class Solicitud {
    private String tipo;
    private String nombre;
    private List<Dato> datos;

    public Solicitud() {
        datos = new ArrayList<>();
    }
    
    

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Dato> getDatos() {
        return datos;
    }

    public void setDatos(List<Dato> datos) {
        this.datos = datos;
    }
    
    public void insertarDato(Dato dato){
        this.datos.add(dato);
    }
}
