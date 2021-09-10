/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

/**
 *
 * @author user-ubunto
 */
public class Instruccion {
    private String tipoInstruccion;
    private int identacion;      

    public Instruccion(String tipoInstruccion, int identacion) {
        this.tipoInstruccion = tipoInstruccion;
        this.identacion = identacion;
    }
    
    
    public String getTipoInstruccion() {
        return tipoInstruccion;
    }

    public void setTipoInstruccion(String tipoInstruccion) {
        this.tipoInstruccion = tipoInstruccion;
    }

    public int getIdentacion() {
        return identacion;
    }

    public void setIdentacion(int identacion) {
        this.identacion = identacion;
    }
    
    
}
