package com.example.proy1c2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.proy1c2.Analizadores.Comunicacion.LexerComun
import com.example.proy1c2.Analizadores.Comunicacion.ParserComun
import com.example.proy1c2.Analizadores.ErrorCom
import com.example.proy1c2.ControlBack.Nota
import com.example.proy1c2.Objetos.Comunicacion.Respuesta
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.IOException
import java.io.StringReader
import java.net.Socket
import java.util.logging.Level
import java.util.logging.Logger



class RepPistaActivity : AppCompatActivity() {
    private lateinit var edPista:TextView
    private var nombrePista:String = ""
    private var mensajeRec: String = ""
    private var listaNotas:List<Nota> = ArrayList<Nota>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rep_pista)

        edPista = findViewById(R.id.txtNombrePista)

        val objetoIntent: Intent = intent
        nombrePista = objetoIntent.getStringExtra("Nombre").toString().replace("\"","")
        edPista.setText(nombrePista)

        traerPista()

        var buttonRep: Button =  findViewById(R.id.button3)
        buttonRep.setOnClickListener{
            reproducirPista()
        }

        var buttonDet: Button =  findViewById(R.id.button4)
        buttonDet.setOnClickListener{
            detenerPista()
        }

    }

    private fun reproducirPista(){

    }

    private fun detenerPista(){

    }

    private fun traerPista(){
        var valorEnviar:String = "<solicitud> \n <tipo> pista </tipo> <nombre>\"$nombrePista\"</nombre> \n </solicitud>"
        enviarSolicitudServer(valorEnviar)
        var valorRecibido:String = mensajeRec
    }

    private fun enviarSolicitudServer(valor:String){
        CoroutineScope(Dispatchers.IO).launch { client1(valor) }
    }

    private suspend fun client1(valor: String){
        val HOST = "192.168.0.11"
        //val HOST = "192.168.113.19"
        //Puerto del servidor
        //Puerto del servidor
        val PUERTO = 5000
        val inp: DataInputStream
        val out: DataOutputStream

        try {
            //Creo el socket para conectarme con el cliente
            val sc = Socket(HOST, PUERTO)
            inp = DataInputStream(sc.getInputStream())
            out = DataOutputStream(sc.getOutputStream())

            //Envio un mensaje al cliente
            //out.writeUTF("¡Hola mundo desde el cliente!")
            out.writeUTF(valor)

            //Recibo el mensaje del servidor
            val mensaje: String = inp.readUTF()
            mensajeRec = mensaje
            //Compilar Mensaje
            println(mensajeRec)
            sc.close()
            compilar()
            println("Se recibio el mensaje")

        } catch (ex: IOException) {
            Logger.getLogger(MainActivity::class.java.getName()).log(Level.SEVERE, null, ex)
        }
    }

    private fun compilar(){
        try {
            var respuesta: Respuesta = Respuesta()
            var errores:List<ErrorCom> = ArrayList()
            var reader = StringReader(mensajeRec)
            var lexer = LexerComun(reader)
            var parser = ParserComun(lexer)

            try {
                parser.parse()
                errores = parser.erroresCom
                respuesta = parser.respuesta
            }catch (ex:Exception){

            }
            if (errores.isEmpty() ){
                if (respuesta != null){
                    validarRespuesta(respuesta)
                }
            }else{
                //Hay errores
            }
        }catch (e :Exception){
            println(e)
        }
    }

    private fun validarRespuesta(respuesta: Respuesta){
        listaNotas = respuesta.notas
        for (n in listaNotas){
            var nomb = n.nombre
            var num = n.nota
            println("Nota: $nomb,NumN: $num")
        }
        println("Notas recibidas")
    }
}