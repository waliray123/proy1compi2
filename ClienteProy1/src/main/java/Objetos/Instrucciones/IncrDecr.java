/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos.Instrucciones;

import Objetos.Instruccion;

/**
 *
 * @author user-ubunto
 */
public class IncrDecr extends Instruccion{
    private String variable;
    private String tipo;

    public IncrDecr(String tipoInstruccion, int identacion) {
        super(tipoInstruccion, identacion);
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
}
