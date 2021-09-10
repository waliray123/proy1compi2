/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControlBack;

import Objetos.BinGuard;
import Objetos.Lista;
import Objetos.PistaGuard;
import java.util.List;

/**
 *
 * @author user-ubunto
 */
public class ControlSemantico {
    
    public boolean validarLista(Lista listaVal){
        List<String> pistasLista = listaVal.getPistas();
        int tamanio = pistasLista.size();
        for (int i = 0; i < (tamanio-1); i++) {
            String pista1 = pistasLista.get(i);
            for (int j = i+1; j < tamanio; j++) {
                String pista2 = pistasLista.get(j);                
                if (pista1.equals(pista2)) {
                    return false;                    
                }
            }
        }        
        return validarPistas(pistasLista);
    }
    
    private boolean validarPistas(List<String> pistasLista){
        //buscarPistas
        ControlGuardado controlG = new ControlGuardado();
        controlG.obtenerBinario();
        BinGuard binarioG = controlG.getBinarioGuard();
        List<PistaGuard> pistasG = binarioG.getPistasGuardadas();
        for (String pistaL : pistasLista) {
            boolean encontrado = false;
            String nomPL = pistaL.replace("\"", "");
            for (PistaGuard pistaGuard : pistasG) {
                String nomPG = pistaGuard.getNombre().replace("\"", "");
                if (nomPL.equals(nomPG)) {
                    encontrado = true;
                    break;
                }
            }
            if (encontrado == false) {
                return false;
            }
        }
        return true;
    }
    
}
