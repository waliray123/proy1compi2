/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos.Instrucciones;

import Objetos.Instruccion;
import Objetos.Parametro;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user-ubunto
 */
public class Funcion extends Instruccion{
    private String nombre;
    private String tipo;
    private boolean esKeep;
    private List<Parametro> parametros;
    private List<Instruccion> instrucciones;

    public Funcion(String tipoInstruccion, int identacion) {
        super(tipoInstruccion, identacion);
        this.instrucciones = new ArrayList<>();
        this.parametros = new ArrayList<>();  
    }   

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Parametro> getParametros() {
        return parametros;
    }

    public void setParametros(List<Parametro> parametros) {
        this.parametros = parametros;
    }

    public List<Instruccion> getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(List<Instruccion> instrucciones) {
        this.instrucciones = instrucciones;
    }
    
    public void insertarParametro(Parametro parametro){
        this.parametros.add(parametro);
    }
    
    public void insertarInstruccion(Instruccion intruccion){
        this.instrucciones.add(intruccion);
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isEsKeep() {
        return esKeep;
    }

    public void setEsKeep(boolean esKeep) {
        this.esKeep = esKeep;
    }
        
    
}
