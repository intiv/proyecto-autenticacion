/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package distribucionchat;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Inti Velasquez
 */

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Inti Velasquez
 */
public class Servidor {
     
    private void iniciarServidor(){
        try {
            // Crear el repositorio en el puerto 1099
            Registry registry = LocateRegistry.createRegistry(1099);
             
            // Crea un nuevo servicio y lo registra en el repositorio
            registry.rebind("miMensaje", new MensajeImpl());
        } catch (Exception e) {
            e.printStackTrace();
        }      
        System.out.println("El sistema esta listo");
    }

    public static void main(String[] args) {
        Servidor servidor = new Servidor();
        servidor.iniciarServidor();
    }

}   

