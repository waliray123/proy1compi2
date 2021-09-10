/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos.Comunicacion;

/**
 *
 * @author user-ubunto
 */
public class Dato {
   
    private String canal;
    private String nota;
    private String octava;
    private String duracion;

    public Dato() {
        this.canal = "";
        this.nota = "";
        this.octava = "";
        this.duracion = "";
    }        

    public String getCanal() {
        return canal;
    }

    public void setCanal(String canal) {
        this.canal = canal;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getOctava() {
        return octava;
    }

    public void setOctava(String octava) {
        this.octava = octava;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }
    
    
    
}
