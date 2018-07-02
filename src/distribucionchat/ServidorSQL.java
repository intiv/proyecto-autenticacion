/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distribucionchat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.UUID;

public class ServidorSQL {

    public static void main(String[] args) {
        ServidorSQL server = new ServidorSQL();
        server.Check_Data();
    }

    public void Check_Data() {
        DB_Connection.DB_Connection obj_DB_Connection = new DB_Connection.DB_Connection();
        Connection connection = obj_DB_Connection.get_connection();
        PreparedStatement ps = null;
        try {
            String query = "select * from test1";
            ps = connection.prepareStatement(query);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                System.out.println("name- " + rs.getString("name"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public int createUser(String nombreCompleto, String loginName, String password){
        /*
            uuid
            nombre completo
            login name
            password
            fecha de creacion
            fecha de accesos al sistema
        
        */
        
        return -1;
      
    }
    
    public UUID loginRequest(String loginName, String password){
        UUID temp = UUID.randomUUID();
        
        
        return temp;
    }
    
    public ArrayList<String> lookupUser(UUID uuid){
        ArrayList temp = new ArrayList();
        
        
        return temp;
        
    }
    
    public ArrayList<String> lookupUser(String username){
        ArrayList temp = new ArrayList();
        
        
        return temp;
        
    }
    
    public int deleteUser(String username, String password){
        
        
        return -1;
    }
    
    
    public int updateLoginUser(String newloginName, String oldLoginName){
        
        return -1;
    }
    
    public ArrayList<ArrayList<String>> getUsers (){
        ArrayList<ArrayList<String>> temp = new ArrayList();
        
        
        return temp;
    }
    
    
}
