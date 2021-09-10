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
public class SwitchC extends Instruccion{
    private List<Expresion> variable;
    private List<CasoC> casos;
    

    public SwitchC(String tipoInstruccion, int identacion) {
        super(tipoInstruccion, identacion);
        this.casos = new ArrayList<>();        
    }

    public List<Expresion> getVariable() {
        return variable;
    }

    public void setVariable(List<Expresion> variable) {
        this.variable = variable;
    }

    public List<CasoC> getCasos() {
        return casos;
    }

    public void setCasos(List<CasoC> casos) {
        this.casos = casos;
    }
    
    public void insertarCaso(CasoC caso){
        this.casos.add(caso);
    }
    
}
