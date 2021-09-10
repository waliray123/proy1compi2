package com.example.proy1c2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TableLayout
import com.example.proy1c2.Analizadores.Comunicacion.LexerComun
import com.example.proy1c2.Analizadores.Comunicacion.ParserComun
import com.example.proy1c2.Analizadores.ErrorCom
import com.example.proy1c2.ControlUI.TableDynamic
import com.example.proy1c2.Objetos.Comunicacion.Respuesta
import com.example.proy1c2.Objetos.Comunicacion.ValorR
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.IOException
import java.io.StringReader
import java.net.Socket
import java.util.logging.Level
import java.util.logging.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private var tipoTabla: String = ""
    private var mensajeRec: String = ""
    private lateinit var table : TableLayout
    private lateinit var tableDy : TableDynamic
    private var pistasLista = ArrayList<String>()
    private var nombrePistaRep: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Inicializar tabla
        table =  findViewById(R.id.tableL)
        tableDy = TableDynamic(table,applicationContext)
        val itemH = arrayOf("Nombre","Cantidad")
        tableDy.addHeader(itemH)

        var buttonPista: Button =  findViewById(R.id.button5)
        buttonPista.setOnClickListener{
            solicitarPistas(it)
        }
        var buttonLista: Button =  findViewById(R.id.button6)
        buttonLista.setOnClickListener{
            solicitarListas(it)
        }

        var buttonVer: Button = findViewById(R.id.button8)
        buttonVer.setOnClickListener {
            compilarMensaje(mensajeRec)
        }

        var buttonRep: Button = findViewById(R.id.button7)
        buttonRep.setOnClickListener {
            solicitarReproduccion()
        }
    }

    private fun solicitarPistas(view: View?) {
        var ed1: EditText = findViewById(R.id.editTextTextPersonName2)
        var textoEntrada = ed1.text.toString()

        var valorEnviar:String = "<solicitud> \n <tipo> pista </tipo> <nombre>\"$textoEntrada\"</nombre> \n </solicitud>"
        enviarSolicitudServer(valorEnviar)
        var valorRecibido:String = mensajeRec
        //Leer el valor recibido y mostrar en la tabla

    }

    private fun solicitarListas(view: View?) {
        var ed1: EditText = findViewById(R.id.editTextTextPersonName2)
        var textoEntrada = ed1.text.toString()

        var valorEnviar:String = "<solicitud> \n <tipo> lista </tipo> <nombre>\"$textoEntrada\"</nombre> \n </solicitud>"
        enviarSolicitudServer(valorEnviar)
        var valorRecibido:String = mensajeRec
        //Leer el valor recibido y mostrar en la tabla
    }

    private fun enviarSolicitudServer(valor:String){
        CoroutineScope(Dispatchers.IO).launch {
            client2(valor)
        }
        //compilarMensaje(mensajeRec)
    }

    private fun compilarMensaje(mensaje:String){
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
        var tipo = respuesta.tipo
        when(tipo){
            "pista" ->{
                //Es una pista
                tipoTabla = "pista"
                mostrarPistaUnica(respuesta);
            }
            "pistas" ->{
                //Son varias pistas
                tipoTabla = "pistas"
                mostrarPistas(respuesta.valores)
            }
            "lista" ->{
                //Es una lista
                tipoTabla = "lista"
                mostrarPistasDeUnaLista(respuesta.valores)
            }
            "listas" ->{
                //Son varias listas
                tipoTabla = "listas"
                mostrarListas(respuesta.valores)
            }
        }
    }

    private fun mostrarPistas(datos:List<ValorR>){
        val datosT = java.util.ArrayList<Array<String>>()
        for(dato in datos){
            var nomb = dato.nombre.replace("\"","")
            //Cantidad es duracion
            var cantidad = dato.cantidad
            println("Nombre: $nomb")
            println("Cantidad: $cantidad")
            //Insertar las pistas en la tabla

            val item = arrayOf(nomb, cantidad)
            datosT.add(item)
        }
        reiniciarTabla("Nombre Pista","Duracion")
        tableDy.addData(datosT)
    }

    private fun mostrarListas(datos:List<ValorR>){
        val datosT = java.util.ArrayList<Array<String>>()
        for(dato in datos){
            var nomb = dato.nombre.replace("\"","")
            //Cantidad es numero de canciones
            var cantidad = dato.cantidad
            println("Nombre: $nomb")
            println("Cantidad: $cantidad")
            val item = arrayOf(nomb, cantidad)
            datosT.add(item)
        }
        reiniciarTabla("Nombre Lista","Cantidad Pistas")
        tableDy.addData(datosT)
    }

    private fun mostrarPistasDeUnaLista(datos:List<ValorR>){
        pistasLista = ArrayList<String>()
        val datosT = java.util.ArrayList<Array<String>>()
        for(dato in datos){
            var nomb = dato.nombre.replace("\"","")
            println("Nombre: $nomb")
            val item = arrayOf(nomb,"")
            datosT.add(item)
            //Agregar dato nombre a la lista de pistas para reproducir luego
            pistasLista.add(nomb)
        }
        reiniciarTabla("Nombre Pista","")
        tableDy.addData(datosT)
    }

    private fun mostrarPistaUnica(respuesta:Respuesta){
        pistasLista = ArrayList<String>()
        var datos = respuesta.notas
        val datosT = java.util.ArrayList<Array<String>>()
        for(dato in datos){
            var notaD = dato.nombre.replace("\"","")
            var octaD = dato.octava
            var tiemD = dato.tiempo
            var canaD = dato.canal
            val itemT = arrayOf(notaD,octaD.toString(),tiemD.toString(),canaD.toString())
            datosT.add(itemT)
            //Agregar dato nombre a la lista de pistas para reproducir luego
            nombrePistaRep = respuesta.nombV
        }
        reiniciarTabla2("Nota","Octava","Tiempo","Canal")
        tableDy.addData(datosT)
    }

    private fun solicitarReproduccion(){
        if(pistasLista.isEmpty()){
            if(nombrePistaRep.isEmpty()){
                //No hay nada por reproducir
                crearDialog("Fallo Reproduccion","No hay ni pista ni lista para reproducir")
            }else{
                //Hay una pista por reproducir
                val intent : Intent = Intent(this,RepPistaActivity::class.java)
                intent.putExtra("Nombre",nombrePistaRep)
                startActivity(intent)

            }
        }else{
            //Hay pistas en la lista que reproducir
            val intent : Intent = Intent(this,RepListaActivity::class.java)
            intent.putExtra("Nombre",nombrePistaRep)
            startActivity(intent)
        }
    }

    private fun crearDialog(titulo:String,mensaje:String){
        val build1 = android.app.AlertDialog.Builder(this)
        build1.setTitle(titulo)
        build1.setMessage(mensaje)
        build1.setNeutralButton(
            "Cancelar"
        ) { dialogInterface, i -> dialogInterface.cancel() }
        val dialog = build1.create()
        dialog.show()
    }

    private fun reiniciarTabla(h1:String,h2:String){
        nombrePistaRep = ""
        tableDy.eliminarTodasLasFilas()
        val itemH = arrayOf(h1,h2)
        tableDy.addHeader(itemH)
    }

    private fun reiniciarTabla2(h1:String,h2:String,h3:String,h4:String){
        pistasLista = ArrayList<String>()
        tableDy.eliminarTodasLasFilas()
        val itemH = arrayOf(h1,h2,h3,h4)
        tableDy.addHeader(itemH)
    }

    private suspend fun client2(valor: String){
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
        } catch (ex: IOException) {
            Logger.getLogger(MainActivity::class.java.getName()).log(Level.SEVERE, null, ex)
        }
    }

}