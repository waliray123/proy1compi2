/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import ControlBack.ControlServidor;
import GUI.JFPrincipal;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user-ubunto
 */
public class Servidor {

    public static void main(String[] args) {
        
        JFPrincipal principal = new JFPrincipal();
        principal.setVisible(true);
        
        ServerSocket servidor = null;
        Socket sc = null;
        DataInputStream in;
        DataOutputStream out;

        //puerto de nuestro servidor
        final int PUERTO = 5000;

        try {
            //Creamos el socket del servidor
            servidor = new ServerSocket(PUERTO);
            System.out.println("Servidor iniciado");

            //Siempre estara escuchando peticiones
            while (true) {

                //Espero a que un cliente se conecte
                sc = servidor.accept();

                System.out.println("Cliente conectado");
                in = new DataInputStream(sc.getInputStream());
                out = new DataOutputStream(sc.getOutputStream());

                //Leo el mensaje que me envia
                String mensaje = in.readUTF();
                //Leer mensaje y validarlo 
                ControlServidor controlServ = new ControlServidor(mensaje);
                controlServ.compilarMensaje();
                String respuesta = controlServ.getRespuesta();
                
                System.out.println(mensaje);

                //Le envio un mensaje
                //Enviar respuesta
//                out.writeUTF("¡Hola mundo desde el servidor!");
                out.writeUTF(respuesta);

                //Cierro el socket
                sc.close();
                System.out.println("Cliente desconectado");

            }

        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
