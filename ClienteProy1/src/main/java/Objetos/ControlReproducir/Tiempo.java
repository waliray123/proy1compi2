/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos.ControlReproducir;

/**
 *
 * @author user-ubunto
 */
public class Tiempo {
    private int canal;
    private int tiempo;

    public Tiempo(int canal) {
        this.canal = canal;
        this.tiempo = 0;
    }
    
    public int getCanal() {
        return canal;
    }

    public void setCanal(int canal) {
        this.canal = canal;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }
            
    
    
}
