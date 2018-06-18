/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package distribucionchat;

/**
 *
 * @author Inti Velasquez
 */
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ListIterator;
import java.util.Vector;



public class MensajeImpl extends UnicastRemoteObject implements Mensaje {               
    private Vector<Mensaje> clienteObj = null;
    private ClienteGUI cGUI = null;
    private int clienteID = -1;
    
    public MensajeImpl() throws RemoteException {
        clienteObj = new Vector<Mensaje>();
    }
    
    public MensajeImpl(ClienteGUI cGUI, int id) throws RemoteException {
        clienteObj = new Vector<Mensaje>();
        this.cGUI = cGUI;
        this.clienteID = id;
    }
    
    public void publicar(String mensaje, int id) throws RemoteException {        
        System.out.println("Mensaje: " + mensaje);      
        ListIterator cliOI =clienteObj.listIterator();
        if(id == 0 ){
            while (cliOI.hasNext()) {
               ((Mensaje)cliOI.next()).publicarACliente(mensaje);
            } 
        }else{
            while (cliOI.hasNext()) {
                Mensaje obj = (Mensaje)cliOI.next();
                
                if(obj.getID() == id){
                    (obj).publicarACliente(mensaje);
                    break;
                }
            }
            
        }
    }
    
    @Override
    public void publicarACliente(String mensaje) throws RemoteException {
        cGUI.actualizarTexto(mensaje);
    }
    
    @Override
    public void registrar(Mensaje mensajeObj) throws RemoteException {
        clienteObj.add(mensajeObj);
    }
    
    @Override
    public int getID() throws RemoteException{
        return this.clienteID;
    }
}