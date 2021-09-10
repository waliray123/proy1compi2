/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;


import Objetos.Instrucciones.Dimension;
import Objetos.Instrucciones.Funcion;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user-ubunto
 */
public class Expresion {
    private String tipo;
    private String valor;
    private List<Dimension> dimensiones;
    private Funcion funcion;

    public Expresion() {
        this.dimensiones = new ArrayList<>();
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public List<Dimension> getDimensiones() {
        return dimensiones;
    }

    public void setDimensiones(List<Dimension> dimensiones) {
        this.dimensiones = dimensiones;
    }           
    
    public void insertarExpresion(Dimension dimension){
        this.dimensiones.add(dimension);
    }

    public Funcion getFuncion() {
        return funcion;
    }

    public void setFuncion(Funcion funcion) {
        this.funcion = funcion;
    }        
    
}
