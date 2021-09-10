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
public class BinGuard implements Serializable{
    List<PistaGuard> pistasGuardadas;
    List<Lista> listasGuardadas;
    

    public BinGuard() {
        this.pistasGuardadas = new ArrayList<>();
        this.listasGuardadas = new ArrayList<>();
    }
    
    public void insertarPista(PistaGuard pista){
        this.pistasGuardadas.add(pista);
    }        

    public List<PistaGuard> getPistasGuardadas() {
        return pistasGuardadas;
    }

    public void setPistasGuardadas(List<PistaGuard> pistasGuardadas) {
        this.pistasGuardadas = pistasGuardadas;
    }
    
    public boolean eliminarPista(String nombre){
        int cont = 0;
        for (PistaGuard pistaGuardada : pistasGuardadas) {            
            String nom = pistaGuardada.getNombre();
            if (nom.equals(nombre)) {
                this.pistasGuardadas.remove(cont);
                return true;                
            }
            cont++;
        }
        return false;
    }
    
    public void insertarLista(Lista lista){
        this.listasGuardadas.add(lista);
    }

    public List<Lista> getListasGuardadas() {
        return listasGuardadas;
    }

    public void setListasGuardadas(List<Lista> listasGuardadas) {
        this.listasGuardadas = listasGuardadas;
    }        
    
    public boolean eliminarLista(String nombre){
        int cont = 0;
        for (Lista listaGuardada : listasGuardadas) {            
            String nom = listaGuardada.getNombre();
            if (nom.equals(nombre)) {
                this.listasGuardadas.remove(cont);
                return true;                
            }
            cont++;
        }
        return false;
    }
    
    
}
