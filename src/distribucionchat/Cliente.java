/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package distribucionchat;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Inti Velasquez
 */
public class Cliente {    
    private Mensaje mensaje = null; 
    private ClienteGUI cGUI = null;
    private String nombre = null;
    private int ID = -1;
    
    Cliente() {}
    
    Cliente(ClienteGUI cGUI) {
        this.cGUI = cGUI;
        this.nombre = JOptionPane.showInputDialog(cGUI, "Ingrese nombre");
        
    }
    
    Cliente(ClienteGUI cGUI, int id, String name){
        this.cGUI = cGUI;
        this.ID = id;
        this.nombre = name;
    }
    
    public void iniciar() {
    try {
            this.ID = Integer.parseInt(JOptionPane.showInputDialog("Ingrese ID (Numero unico distinto de 0)"));
            this.cGUI.setTitle(this.nombre+": "+this.ID);
            Registry myRegistry = LocateRegistry.getRegistry("127.0.0.1", 1099);                         
            this.mensaje = (Mensaje) myRegistry.lookup("miMensaje");                        
            if (this.cGUI == null)
                mensaje.registrar(new MensajeImpl());            
            else
                mensaje.registrar(new MensajeImpl(this.cGUI, this.ID));            
        } catch (RemoteException ex) {
            System.out.println("");
        } catch (NotBoundException ex) {
            System.out.println("");
        }
    }
    public void enviarMensaje(String message) {
        try {
            String[] sub = {message.substring(0, message.indexOf(",")), message.substring(message.indexOf(",")+1, message.length())};
            System.out.println("Array: "+Arrays.toString(sub));
            
            this.mensaje.publicar(this.nombre+": "+sub[1], Integer.parseInt(sub[0]));
            if(Integer.parseInt(sub[0]) == 0){
                this.cGUI.actualizarTexto(this.nombre+": "+message.substring(message.indexOf(",")+1, message.length()));
            }
        } catch (RemoteException ex) {
            System.out.println("");
        }
    }
     
    private void prueba(){        
            Scanner scn = new Scanner(System.in);
            String texto = "";
            while (true) {
                System.out.print("Ingrese texto: ");                 
                texto = scn.nextLine();            
                try {
                    mensaje.publicar(texto, this.ID);
                } catch (RemoteException ex) {
                    System.out.println("");
                }
            }                           
    }  
        
    public static void main(String[] args) throws RemoteException, NotBoundException {        
        Cliente cliente = new Cliente();
        cliente.iniciar();
        cliente.prueba();
    }
}   

