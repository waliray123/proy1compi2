package Analizadores.Pintar;
import java.util.List;
import java.util.ArrayList;
import Analizadores.ErrorCom;

%%

//Configuracion JFLEX
%public
%class LexerPintar
%line
%column
%function scanear
%unicode
%char
%standalone
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


/*--------------Operadores-------------*/
masigual            = "+="
opIncrDecr          = "++"|"--"
oparitmeticos       = "+"|"-"|"*"|"/"|"%"|"^"
oprelacionales      = "=="|"!="|">"|"<"|">="|"<="|"!!"
oplogicos           = "&&"|"!&&"|"||"|"!||"|"&|"|"!"
pari                = "("
pard                = ")"
bracki              = "["
brackd              = "]"
curlbracki          = "{"
curlbrackd          = "}"
dospunt             = ":"
puntcoma            = ";"
coma                = ","
igual               = "="
operadoresTodo      = {oparitmeticos}|{oprelacionales}|{oplogicos}|{pari}|{pard}|{bracki}|{brackd}|{curlbracki}|{curlbrackd}|{dospunt}|{puntcoma}|{coma}|{igual}|{masigual}|{opIncrDecr}

/*--------Palabras Reservadas--------*/
%ignorecase
palabraReserv       = "true"|"false"|"extiende"|"keep"|"var"|"arreglo"|"si"|"sino"|"sino si"|"switch"|"caso"|"default"|"para"|"mientras"|"hacer"|"continuar"|"retorna"|"Reproducir"|"Esperar"|"Sumarizar"|"Longitud"|"Mensaje"|"Principal"|"lista"|"nombre"|"random"|"circular"|"pistas"|"salir"





//Codigo Incrustado
%{
    private List<ErrorCom> erroresCom;
    public PintarPalabras pintar = new PintarPalabras();
    /*------Inicializacion Variables-----*/
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
    {comentario}            {pintar.pintaGris(yychar,yylength());} 
    {espacioblanco}         {/*vacio*/}
    {palabraReserv}         {pintar.pintaAzul(yychar,yylength());}     
    {identificador}         {pintar.pintaVerde(yychar,yylength());}  
    {cadena}                {pintar.pintaNara(yychar,yylength());}  
    {caracter}              {pintar.pintaNara(yychar,yylength());}  
    {numero}                {pintar.pintaMora(yychar,yylength());}  
    {operadoresTodo}        {/*vacio*/}
    

}

/* Error por cualquier otro simbolo*/
[^]
		{System.out.println("es un Error:"+yytext());}
