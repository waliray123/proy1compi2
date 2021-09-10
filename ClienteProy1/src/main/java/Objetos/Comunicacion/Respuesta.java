/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos.Comunicacion;

import ControlBack.Nota;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user-ubunto
 */
public class Respuesta {
    private String tipo;
    private String nombV;
    private List<ValorR> valores;
    private List<Nota> notas;

    public Respuesta() {
        this.valores = new ArrayList<>();
        this.notas = new ArrayList<>();
    }
       
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<ValorR> getValores() {
        return valores;
    }

    public void setValores(List<ValorR> valores) {
        this.valores = valores;
    }
    
    public void insertarValor(ValorR valor){
        this.valores.add(valor);
    }
    
    public void insertarNota(Nota nota){
        this.notas.add(nota);
    }

    public List<Nota> getNotas() {
        return notas;
    }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }

    public String getNombV() {
        return nombV;
    }

    public void setNombV(String nombV) {
        this.nombV = nombV;
    }
    
    
    
}
