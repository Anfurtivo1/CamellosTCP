/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package camellostcpcliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Andres
 */
public class CamellosTCPCliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner teclado = new Scanner (System.in);
        InetAddress direccion;
        Socket servidor;
        int cantidadJugadores;
        int PUERTO = 5000;
        String mensaje;
        
       try{
                System.out.println("Indica cuantos jugadores quereis ser");
                direccion = InetAddress.getLocalHost(); // dirección local
                servidor = new Socket(direccion, PUERTO);//Petición para el accept
                DataInputStream leer = new DataInputStream(servidor.getInputStream());
                DataOutputStream escribir = new DataOutputStream(servidor.getOutputStream());
                
                cantidadJugadores=teclado.nextInt();
                escribir.writeInt(cantidadJugadores);
                
                mensaje=leer.readUTF();
                System.out.println(mensaje);
                do {
                    mensaje=leer.readUTF();
                    System.out.println(mensaje);
               } while (!mensaje.equalsIgnoreCase("Fin"));
                
       }catch(Exception e){
           System.out.println("Error en "+e);
       }
    }
    
}
