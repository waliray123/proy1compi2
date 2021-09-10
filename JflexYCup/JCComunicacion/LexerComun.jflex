package Analizadores.Comunicacion;
import java_cup.runtime.*; 
import Analizadores.Comunicacion.sym.*;
import java.util.List;
import java.util.ArrayList;
import Analizadores.ErrorCom;

%%

//Configuracion JFLEX
%public
%class LexerComun
%standalone
%line
%column
%cup

//Expresiones Regulares


/*--------------Literales---------------*/
digito              = [0-9]
cadena              = \"[^\"\\]*\"
numero              = {digito}+([.]{digito}{1,6})?


/*----------Espacios En blanco----------*/
espacio             = " "
saltoLinea          = \n|\r|\r\n
espacioblanco       = ({espacio}|{saltoLinea}| [\t\n])+



/*--------- Codigo Incrustado ---------*/
%{
    private List<ErrorCom> erroresCom;
    private int identCant;
    private boolean identar;

    private void error(String lexeme) {
        erroresCom.add(new ErrorCom("Lexico","Simbolo no existe en el lenguaje",String.valueOf(yyline+1),String.valueOf(yycolumn+1),lexeme));
    }    

    private void errorPrueba(String lexeme, String tipo) {
        erroresCom.add(new ErrorCom("PRUEBA",tipo,String.valueOf(yyline+1),String.valueOf(yycolumn+1),lexeme));
    }

    public List<ErrorCom> getErroresCom() {
        return erroresCom;
    }

%}

%init{
    erroresCom = new ArrayList<>();
%init}

%%


//Reglas Lexicas
<YYINITIAL> {        
    {espacioblanco}         {/*VACIO*/}
    /*-------- Palabras Reservadas --------*/
    "lista"                 {return new Symbol(sym.LISTA,yyline+1,yycolumn+1, yytext());}  
    "pista"                 {return new Symbol(sym.PISTA,yyline+1,yycolumn+1, yytext());}  
    "pistanueva"            {return new Symbol(sym.PISTANUEVA,yyline+1,yycolumn+1, yytext());}  
    "pistas"                {return new Symbol(sym.PISTAS,yyline+1,yycolumn+1, yytext());}  
    "listas"                {return new Symbol(sym.LISTAS,yyline+1,yycolumn+1, yytext());}  
    "solicitud"             {return new Symbol(sym.SOLICITUD,yyline+1,yycolumn+1, yytext());}  
    "tipo"                  {return new Symbol(sym.TIPO,yyline+1,yycolumn+1, yytext());}  
    "nombre"                {return new Symbol(sym.NOMBRE,yyline+1,yycolumn+1, yytext());}  
    "aleatoria"             {return new Symbol(sym.ALEATORIA,yyline+1,yycolumn+1, yytext());}  
    "duracion"              {return new Symbol(sym.DURACION,yyline+1,yycolumn+1, yytext());}  
    "canal"                 {return new Symbol(sym.CANAL,yyline+1,yycolumn+1, yytext());}  
    "numero"                {return new Symbol(sym.NUMEROSTR,yyline+1,yycolumn+1, yytext());}  
    "frecuencia"            {return new Symbol(sym.FRECUENCIA,yyline+1,yycolumn+1, yytext());}  
    "datos"                 {return new Symbol(sym.DATOS,yyline+1,yycolumn+1, yytext());}  
    "nota"                  {return new Symbol(sym.NOTA,yyline+1,yycolumn+1, yytext());}  
    "octava"                {return new Symbol(sym.OCTAVA,yyline+1,yycolumn+1, yytext());}      
    "respuesta"             {return new Symbol(sym.RESPUESTA,yyline+1,yycolumn+1, yytext());}      
    {cadena}                {return new Symbol(sym.CADENA,yyline+1,yycolumn+1, yytext());}      
    {numero}                {return new Symbol(sym.NUMERO,yyline+1,yycolumn+1, yytext());}          
    /*------------ Operadores ------------*/
    ">"                     {return new Symbol(sym.MAY,yyline+1,yycolumn+1, yytext());}
    "<"                     {return new Symbol(sym.MEN,yyline+1,yycolumn+1, yytext());}
    "/"                     {return new Symbol(sym.BARRA,yyline+1,yycolumn+1, yytext());}     
    "="                     {return new Symbol(sym.IGUAL,yyline+1,yycolumn+1, yytext());}      


    

}

/* Error por cualquier otro simbolo*/
[^]
		{ error(yytext()); new Symbol(sym.error,yyline,yycolumn, yytext());}
