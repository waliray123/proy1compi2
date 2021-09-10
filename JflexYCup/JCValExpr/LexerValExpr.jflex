package Analizadores.ValExpr;
import java_cup.runtime.*; 
import Analizadores.ValExpr.sym.*;
import java.util.List;
import java.util.ArrayList;
import Analizadores.ErrorCom;

%%

//Configuracion JFLEX
%public
%class LexerValExpr
%standalone
%line
%column
%cup

//Expresiones Regulares

/*--------------Literales---------------*/
digito              = [0-9]
caracter            = \'#?[^\']\'
cadena              = \"[^\"\\]*\"
numero              = {digito}+([.]{digito}{1,6})?


/*----------Espacios En blanco----------*/
espacio             = " "
saltoLinea          = \n|\r|\r\n
espacioblanco       = ({espacio}|{saltoLinea}| [\t\n])+


/*--------- Codigo Incrustado ---------*/
%{
    private List<ErrorCom> erroresCom;

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
    {espacioblanco}         {/*vacio*/}
    /*-------- Palabras Reservadas --------*/
    [tT]"rue"               {return new Symbol(sym.VERDADERO,yyline+1,yycolumn+1, yytext());}
    [vV]"erdadero"          {return new Symbol(sym.VERDADERO,yyline+1,yycolumn+1, yytext());}
    [fF]"alse"              {return new Symbol(sym.FALSO,yyline+1,yycolumn+1, yytext());}
    [fF]"also"              {return new Symbol(sym.FALSO,yyline+1,yycolumn+1, yytext());}
    [nN]"ulo"               {return new Symbol(sym.NULO,yyline+1,yycolumn+1, yytext());}
    {cadena}                {return new Symbol(sym.CADENA,yyline+1,yycolumn+1, yytext());}  
    {caracter}              {return new Symbol(sym.CARACTER,yyline+1,yycolumn+1, yytext());}  
    {numero}                {return new Symbol(sym.NUMERO,yyline+1,yycolumn+1, yytext());}     
    /*------------ Operadores ------------*/
    "++"                    {return new Symbol(sym.OPINCR,yyline+1,yycolumn+1, yytext());}
    "--"                    {return new Symbol(sym.OPDECR,yyline+1,yycolumn+1, yytext());}
    "+"                     {return new Symbol(sym.SUMA,yyline+1,yycolumn+1, yytext());}
    "-"                     {return new Symbol(sym.RESTA,yyline+1,yycolumn+1, yytext());}
    "*"                     {return new Symbol(sym.MULT,yyline+1,yycolumn+1, yytext());}
    "/"                     {return new Symbol(sym.DIVI,yyline+1,yycolumn+1, yytext());}
    "%"                     {return new Symbol(sym.OPMOD,yyline+1,yycolumn+1, yytext());}
    "^"                     {return new Symbol(sym.OPELV,yyline+1,yycolumn+1, yytext());}
    "=="                    {return new Symbol(sym.DOBIGUAL,yyline+1,yycolumn+1, yytext());}
    "!="                    {return new Symbol(sym.DIFERENC,yyline+1,yycolumn+1, yytext());}
    ">="                    {return new Symbol(sym.MAYIG,yyline+1,yycolumn+1, yytext());}
    "<="                    {return new Symbol(sym.MENIG,yyline+1,yycolumn+1, yytext());}
    "!!"                    {return new Symbol(sym.OPNULO,yyline+1,yycolumn+1, yytext());}
    ">"                     {return new Symbol(sym.MAY,yyline+1,yycolumn+1, yytext());}
    "<"                     {return new Symbol(sym.MEN,yyline+1,yycolumn+1, yytext());}
    "!&&"                   {return new Symbol(sym.NAND,yyline+1,yycolumn+1, yytext());}
    "!||"                   {return new Symbol(sym.NOR,yyline+1,yycolumn+1, yytext());}
    "&|"                    {return new Symbol(sym.XOR,yyline+1,yycolumn+1, yytext());}
    "&&"                    {return new Symbol(sym.AND,yyline+1,yycolumn+1, yytext());}
    "||"                    {return new Symbol(sym.OR,yyline+1,yycolumn+1, yytext());}
    "!"                     {return new Symbol(sym.OPNOT,yyline+1,yycolumn+1, yytext());}      
    "("                     {return new Symbol(sym.PARI,yyline+1,yycolumn+1, yytext());}      
    ")"                     {return new Symbol(sym.PARD,yyline+1,yycolumn+1, yytext());}      
    "["                     {return new Symbol(sym.BRACKI,yyline+1,yycolumn+1, yytext());}
    "]"                     {return new Symbol(sym.BRACKD,yyline+1,yycolumn+1, yytext());}      
    "{"                     {return new Symbol(sym.CURLBRACKI,yyline+1,yycolumn+1, yytext());}
    "}"                     {return new Symbol(sym.CURLBRACKD,yyline+1,yycolumn+1, yytext());}      
    ":"                     {return new Symbol(sym.DOSPUNT,yyline+1,yycolumn+1, yytext());}      
    ";"                     {return new Symbol(sym.PUNTCOMA,yyline+1,yycolumn+1, yytext());}      
    ","                     {return new Symbol(sym.COMA,yyline+1,yycolumn+1, yytext());}      
    "="                     {return new Symbol(sym.IGUAL,yyline+1,yycolumn+1, yytext());}      


    

}

/* Error por cualquier otro simbolo*/
[^]
		{ error(yytext()); new Symbol(sym.error,yyline,yycolumn, yytext());}
