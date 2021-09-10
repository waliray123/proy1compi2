/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos.Instrucciones;

import Objetos.Expresion;
import Objetos.Instruccion;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user-ubunto
 */
public class CasoC extends Instruccion{
    private String tipo;
    private List<Expresion> expresion;
    private List<Instruccion> instrucciones;

    public CasoC(String tipoInstruccion, int identacion) {
        super(tipoInstruccion, identacion);
        this.instrucciones = new ArrayList<>();
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<Expresion> getExpresion() {
        return expresion;
    }

    public void setExpresion(List<Expresion> expresion) {
        this.expresion = expresion;
    }


    public List<Instruccion> getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(List<Instruccion> instrucciones) {
        this.instrucciones = instrucciones;
    }

    
    public void insertarInstruccion(Instruccion instruccion){
        this.instrucciones.add(instruccion);
    }
}
