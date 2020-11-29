/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package camellostcpservidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Andres
 */
public class CamellosTCPServidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ServerSocket servidor;
        Socket cliente;
        int PUERTO = 5000;
        int cantidadJugadores;
        int numeroJugadores=0;
        

        System.out.println("Servidor arrancado");
        
        try{
            servidor = new ServerSocket(PUERTO);
            System.out.println("Esperando...");
            
            do {
                cliente = servidor.accept();
                numeroJugadores++;
                System.out.println("Llega el jugador "+numeroJugadores);
                DataOutputStream escribir = new DataOutputStream(cliente.getOutputStream());
                DataInputStream leer = new DataInputStream(cliente.getInputStream());
                cantidadJugadores=leer.readInt();
                escribir.writeUTF("Se han elegido "+cantidadJugadores+" jugadores");
//                if (cantidadJugadores==numeroJugadores) {
//                    escribir.writeUTF("Todos los jugadores estan listos");
//                }
                Hilo hilo = new Hilo(cliente,escribir,leer);
                hilo.start();
            } while (true);
            
        }catch(Exception e){
            System.out.println("Error en "+e);
        }
        
    }
    
}
