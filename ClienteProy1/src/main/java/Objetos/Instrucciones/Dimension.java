/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos.Instrucciones;

import Objetos.Expresion;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author user-ubunto
 */
public class Dimension {
    List<Expresion> expresiones;

    public Dimension() {
        this.expresiones= new ArrayList<>();
    }

    public List<Expresion> getExpresiones() {
        return expresiones;
    }

    public void setExpresiones(List<Expresion> expresiones) {
        this.expresiones = expresiones;
    }    
    
    public void insertarExpresion(Expresion expresion){
        this.expresiones.add(expresion);
    }
}
