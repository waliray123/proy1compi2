/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos.ControlCrear;

/**
 *
 * @author user-ubunto
 */
public class ExprVal {
    private String tipo;
    private String val;

    public ExprVal() {
    }

    public ExprVal(String tipo, String val) {
        this.tipo = tipo;
        this.val = val;
    }        

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;       
    }
    
    
}
