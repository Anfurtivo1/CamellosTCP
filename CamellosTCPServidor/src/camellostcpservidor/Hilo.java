/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package camellostcpservidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 *
 * @author Andres
 */
public class Hilo extends Thread{
    
    private Socket cliente;
    private DataOutputStream escribir;
    private DataInputStream leer;

    public Hilo(Socket cliente, DataOutputStream escribir, DataInputStream leer) {
        this.cliente = cliente;
        this.escribir = escribir;
        this.leer = leer;
    }
    
    @Override
    public void run(){
        
        int avanzada;
        Camello camello = new Camello();
        try{
            escribir = new DataOutputStream(cliente.getOutputStream());
            leer = new DataInputStream(cliente.getInputStream());
            escribir.writeUTF("Se ha iniciado el hilo");
            do {
                avanzada= (int) Math.floor(Math.random() * 100 + 1);
                camello.setAvanzada(avanzada+camello.getAvanzada());

                escribir.writeUTF("Tu camello ha avanzado "+avanzada+" ahora tiene "+camello.getAvanzada());
                Thread.sleep(1000);
            } while (camello.getAvanzada()<=100);
            escribir.writeUTF("Fin");
        }catch(Exception e){
            System.out.println("Error en "+e);
        }
    }
    
}
