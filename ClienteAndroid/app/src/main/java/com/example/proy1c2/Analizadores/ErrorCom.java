package com.example.proy1c2.Analizadores;

public class ErrorCom {
    private String tipo;
    private String desc;
    private String lin;
    private String col;
    private String lex;

    public ErrorCom(String tipo, String desc, String lin, String col, String lex) {
        this.tipo = tipo;
        this.desc = desc;
        this.lin = lin;
        this.col = col;
        this.lex = lex;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getLin() {
        return lin;
    }

    public void setLin(String lin) {
        this.lin = lin;
    }

    public String getCol() {
        return col;
    }

    public void setCol(String col) {
        this.col = col;
    }

    public String getLex() {
        return lex;
    }

    public void setLex(String lex) {
        this.lex = lex;
    }
}

