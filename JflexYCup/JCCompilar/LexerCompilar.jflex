package Analizadores.Compilar;
import java_cup.runtime.*; 
import Analizadores.Compilar.sym.*;
import java.util.List;
import java.util.ArrayList;
import Analizadores.ErrorCom;

%%

//Configuracion JFLEX
%public
%class LexerCompilar
%standalone
%line
%column
%cup

//Expresiones Regulares



/*------------Comentarios------------*/
comentariolin       = \>\>[^\n\r]*
comentariobloc      = \<\-[^->]*\-\>
comentario          = ({comentariolin}|{comentariobloc})

/*--------------Literales---------------*/
letra               = [a-zA-Z]
digito              = [0-9]
caracter            = \'#?[^\']\'
cadena              = \"[^\"\\]*\"
numero              = {digito}+([.]{digito}{1,6})?


/*----------Espacios En blanco----------*/
espacio             = " "
saltoLinea          = \n|\r|\r\n
espacioblanco       = ({espacio}|{saltoLinea}| [\t\n])+

/*------------Identificador------------*/
identificador       = ({letra}|"_") ({letra}|{digito}|"_")*

/*---------------Notas----------------*/
notas               = "do#"|"re#"|"fa#"|"sol#"|"la#"|"do"|"re"|"mi"|"fa"|"sol"|"la"|"si"


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

    private int verificarIdentacion(String valor){
        for(char c : valor.toCharArray()) {
            if(c == '\n'){
                this.identCant = 0;
                identar = true;
            } else if (c == ' ' || c == 32) {
                this.identCant++;
            } else if (c == '\t') {
                this.identCant += 4;
            } else {
                identar = false;
            }
        } 
        if(identar == true && this.identCant != 0){
            int cantidadIdent = 0;
            while(this.identCant >= 4){
                cantidadIdent++;
                this.identCant = this.identCant - 4; 
            }
            this.identCant = 0;
            return cantidadIdent;
        }
        return -1;
    }
%}

%init{
    erroresCom = new ArrayList<>();
    identar = true;
    identCant = 0;
%init}

%%


//Reglas Lexicas
<YYINITIAL> {    
    {comentario}            {verificarIdentacion(yytext());return new Symbol(sym.COMENT,yyline+1,yycolumn+1, yytext());} 
    {espacioblanco}         {int val = verificarIdentacion(yytext()); if(val >= 1){return new Symbol(sym.IDENT,yyline+1,yycolumn+1, String.valueOf(val));}}
    /*-------- Palabras Reservadas --------*/
    [pP]"ista"              {verificarIdentacion(yytext());return new Symbol(sym.PISTA,yyline+1,yycolumn+1, yytext());}
    [tT]"rue"               {verificarIdentacion(yytext());return new Symbol(sym.VERDADERO,yyline+1,yycolumn+1, yytext());}
    [vV]"erdadero"          {verificarIdentacion(yytext());return new Symbol(sym.VERDADERO,yyline+1,yycolumn+1, yytext());}
    [fF]"alse"              {verificarIdentacion(yytext());return new Symbol(sym.FALSO,yyline+1,yycolumn+1, yytext());}
    [fF]"also"              {verificarIdentacion(yytext());return new Symbol(sym.FALSO,yyline+1,yycolumn+1, yytext());}
    [eE]"xtiende"           {verificarIdentacion(yytext());return new Symbol(sym.EXTIENDE,yyline+1,yycolumn+1, yytext());}
    [kK]"eep"               {verificarIdentacion(yytext());return new Symbol(sym.KEEP,yyline+1,yycolumn+1, yytext());}
    [vV]"ar"                {verificarIdentacion(yytext());return new Symbol(sym.VAR,yyline+1,yycolumn+1, yytext());}
    [aA]"rreglo"            {verificarIdentacion(yytext());return new Symbol(sym.ARREGLO,yyline+1,yycolumn+1, yytext());}
    [sS]"i"                 {verificarIdentacion(yytext());return new Symbol(sym.SI,yyline+1,yycolumn+1, yytext());}
    [sS]"ino"               {verificarIdentacion(yytext());return new Symbol(sym.SINO,yyline+1,yycolumn+1, yytext());}
    [sS]"ino si"            {verificarIdentacion(yytext());return new Symbol(sym.SINOSI,yyline+1,yycolumn+1, yytext());}
    [sS]"witch"             {verificarIdentacion(yytext());return new Symbol(sym.SWITCH,yyline+1,yycolumn+1, yytext());}
    [cC]"aso"               {verificarIdentacion(yytext());return new Symbol(sym.CASO,yyline+1,yycolumn+1, yytext());}
    [dD]"efault"            {verificarIdentacion(yytext());return new Symbol(sym.DEFAULT,yyline+1,yycolumn+1, yytext());}
    [pP]"ara"               {verificarIdentacion(yytext());return new Symbol(sym.PARA,yyline+1,yycolumn+1, yytext());}
    [mM]"ientras"           {verificarIdentacion(yytext());return new Symbol(sym.MIENTRAS,yyline+1,yycolumn+1, yytext());}
    [hH]"acer"              {verificarIdentacion(yytext());return new Symbol(sym.HACER,yyline+1,yycolumn+1, yytext());}
    [cC]"ontinuar"          {verificarIdentacion(yytext());return new Symbol(sym.CONTINUAR,yyline+1,yycolumn+1, yytext());}
    [rR]"etorna"            {verificarIdentacion(yytext());return new Symbol(sym.RETORNA,yyline+1,yycolumn+1, yytext());}
    [rR]"eproducir"         {verificarIdentacion(yytext());return new Symbol(sym.REPRODUCIR,yyline+1,yycolumn+1, yytext());}
    [eE]"sperar"            {verificarIdentacion(yytext());return new Symbol(sym.ESPERAR,yyline+1,yycolumn+1, yytext());}
    [sS]"umarizar"          {verificarIdentacion(yytext());return new Symbol(sym.SUMARIZAR,yyline+1,yycolumn+1, yytext());}
    [lL]"ongitud"           {verificarIdentacion(yytext());return new Symbol(sym.LONGITUD,yyline+1,yycolumn+1, yytext());}
    [mM]"ensaje"            {verificarIdentacion(yytext());return new Symbol(sym.MENSAJE,yyline+1,yycolumn+1, yytext());}
    [pP]"rincipal"          {verificarIdentacion(yytext());return new Symbol(sym.PRINCIPAL,yyline+1,yycolumn+1, yytext());}
    [lL]"ista"              {verificarIdentacion(yytext());return new Symbol(sym.LISTA,yyline+1,yycolumn+1, yytext());}
    [nN]"ombre"             {verificarIdentacion(yytext());return new Symbol(sym.NOMBRE,yyline+1,yycolumn+1, yytext());}
    [rR]"andom"             {verificarIdentacion(yytext());return new Symbol(sym.RANDOM,yyline+1,yycolumn+1, yytext());}
    [cC]"ircular"           {verificarIdentacion(yytext());return new Symbol(sym.CIRCULAR,yyline+1,yycolumn+1, yytext());}
    [pP]"istas"             {verificarIdentacion(yytext());return new Symbol(sym.PISTAS,yyline+1,yycolumn+1, yytext());}
    [sS]"alir"              {verificarIdentacion(yytext());return new Symbol(sym.SALIR,yyline+1,yycolumn+1, yytext());}
    [eE]"ntero"             {verificarIdentacion(yytext());return new Symbol(sym.ENTEROSTR,yyline+1,yycolumn+1, yytext());}
    [dD]"oble"              {verificarIdentacion(yytext());return new Symbol(sym.DOBLESTR,yyline+1,yycolumn+1, yytext());}
    [bB]"oolean"            {verificarIdentacion(yytext());return new Symbol(sym.BOOLEANSTR,yyline+1,yycolumn+1, yytext());}
    [cC]"aracter"           {verificarIdentacion(yytext());return new Symbol(sym.CARACTERSTR,yyline+1,yycolumn+1, yytext());}
    [cC]"adena"             {verificarIdentacion(yytext());return new Symbol(sym.CADENASTR,yyline+1,yycolumn+1, yytext());}
    {notas}                 {verificarIdentacion(yytext());return new Symbol(sym.NOTA,yyline+1,yycolumn+1, yytext());}      
    {identificador}         {verificarIdentacion(yytext());return new Symbol(sym.ID,yyline+1,yycolumn+1, yytext());}  
    {cadena}                {verificarIdentacion(yytext());return new Symbol(sym.CADENA,yyline+1,yycolumn+1, yytext());}  
    {caracter}              {verificarIdentacion(yytext());return new Symbol(sym.CARACTER,yyline+1,yycolumn+1, yytext());}  
    {numero}                {verificarIdentacion(yytext());return new Symbol(sym.NUMERO,yyline+1,yycolumn+1, yytext());}          
    /*------------ Operadores ------------*/
    "++"                    {verificarIdentacion(yytext());return new Symbol(sym.OPINCR,yyline+1,yycolumn+1, yytext());}
    "--"                    {verificarIdentacion(yytext());return new Symbol(sym.OPDECR,yyline+1,yycolumn+1, yytext());}
    "+="                    {verificarIdentacion(yytext());return new Symbol(sym.OPSUMSIM,yyline+1,yycolumn+1, yytext());}
    "+"                     {verificarIdentacion(yytext());return new Symbol(sym.SUMA,yyline+1,yycolumn+1, yytext());}
    "-"                     {verificarIdentacion(yytext());return new Symbol(sym.RESTA,yyline+1,yycolumn+1, yytext());}
    "*"                     {verificarIdentacion(yytext());return new Symbol(sym.MULT,yyline+1,yycolumn+1, yytext());}
    "/"                     {verificarIdentacion(yytext());return new Symbol(sym.DIVI,yyline+1,yycolumn+1, yytext());}
    "%"                     {verificarIdentacion(yytext());return new Symbol(sym.OPMOD,yyline+1,yycolumn+1, yytext());}
    "^"                     {verificarIdentacion(yytext());return new Symbol(sym.OPELV,yyline+1,yycolumn+1, yytext());}
    "=="                    {verificarIdentacion(yytext());return new Symbol(sym.DOBIGUAL,yyline+1,yycolumn+1, yytext());}
    "!="                    {verificarIdentacion(yytext());return new Symbol(sym.DIFERENC,yyline+1,yycolumn+1, yytext());}
    ">="                    {verificarIdentacion(yytext());return new Symbol(sym.MAYIG,yyline+1,yycolumn+1, yytext());}
    "<="                    {verificarIdentacion(yytext());return new Symbol(sym.MENIG,yyline+1,yycolumn+1, yytext());}
    "!!"                    {verificarIdentacion(yytext());return new Symbol(sym.OPNULO,yyline+1,yycolumn+1, yytext());}
    ">"                     {verificarIdentacion(yytext());return new Symbol(sym.MAY,yyline+1,yycolumn+1, yytext());}
    "<"                     {verificarIdentacion(yytext());return new Symbol(sym.MEN,yyline+1,yycolumn+1, yytext());}
    "!&&"                   {verificarIdentacion(yytext());return new Symbol(sym.NAND,yyline+1,yycolumn+1, yytext());}
    "!||"                   {verificarIdentacion(yytext());return new Symbol(sym.NOR,yyline+1,yycolumn+1, yytext());}
    "&|"                    {verificarIdentacion(yytext());return new Symbol(sym.XOR,yyline+1,yycolumn+1, yytext());}
    "&&"                    {verificarIdentacion(yytext());return new Symbol(sym.AND,yyline+1,yycolumn+1, yytext());}
    "||"                    {verificarIdentacion(yytext());return new Symbol(sym.OR,yyline+1,yycolumn+1, yytext());}
    "!"                     {verificarIdentacion(yytext());return new Symbol(sym.OPNOT,yyline+1,yycolumn+1, yytext());}      
    "("                     {verificarIdentacion(yytext());return new Symbol(sym.PARI,yyline+1,yycolumn+1, yytext());}      
    ")"                     {verificarIdentacion(yytext());return new Symbol(sym.PARD,yyline+1,yycolumn+1, yytext());}      
    "["                     {verificarIdentacion(yytext());return new Symbol(sym.BRACKI,yyline+1,yycolumn+1, yytext());}
    "]"                     {verificarIdentacion(yytext());return new Symbol(sym.BRACKD,yyline+1,yycolumn+1, yytext());}      
    "{"                     {verificarIdentacion(yytext());return new Symbol(sym.CURLBRACKI,yyline+1,yycolumn+1, yytext());}
    "}"                     {verificarIdentacion(yytext());return new Symbol(sym.CURLBRACKD,yyline+1,yycolumn+1, yytext());}      
    ":"                     {verificarIdentacion(yytext());return new Symbol(sym.DOSPUNT,yyline+1,yycolumn+1, yytext());}      
    ";"                     {verificarIdentacion(yytext());return new Symbol(sym.PUNTCOMA,yyline+1,yycolumn+1, yytext());}      
    ","                     {verificarIdentacion(yytext());return new Symbol(sym.COMA,yyline+1,yycolumn+1, yytext());}      
    "="                     {verificarIdentacion(yytext());return new Symbol(sym.IGUAL,yyline+1,yycolumn+1, yytext());}      


    

}

/* Error por cualquier otro simbolo*/
[^]
		{ error(yytext()); new Symbol(sym.error,yyline,yycolumn, yytext());}
