#! /bin/bash 
echo "INICIANDO COMPILACION JFLEX "
java -jar ../lib/jflex-full-1.8.2.jar LexerValExpr.jflex
echo "INICIANDO COMPILACION JAVA CUP"
java -jar ../lib/java-cup-11b.jar -parser ParserValExpr -symbols sym ParserValExpr.cup