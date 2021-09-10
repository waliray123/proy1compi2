/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user-ubunto
 */
public class Lista implements Serializable{
    private String nombre;
    private boolean random;
    private boolean circular;
    private List<String> pistas;
    private String codigo;

    public Lista() {
        this.pistas = new ArrayList<>();
        this.random = false;
        this.circular = false;
        this.nombre = "";
        this.codigo = "";
    }        

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isRandom() {
        return random;
    }

    public void setRandom(boolean random) {
        this.random = random;
    }

    public boolean isCircular() {
        return circular;
    }

    public void setCircular(boolean circular) {
        this.circular = circular;
    }

    public List<String> getPistas() {
        return pistas;
    }

    public void setPistas(List<String> pistas) {
        this.pistas = pistas;
    }
    
    public void insertarPista(String pista){
        this.pistas.add(pista);
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    
            
}
