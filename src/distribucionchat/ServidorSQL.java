/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distribucionchat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class ServidorSQL {

    public static void main(String[] args) {
        ServidorSQL server = new ServidorSQL();
        //server.Check_Data();
    }

    public void Check_Data() {
        DB_Connection.DB_Connection obj_DB_Connection = new DB_Connection.DB_Connection();
        Connection connection = obj_DB_Connection.get_connection();
        PreparedStatement ps = null;
        try {
            String query = "select FirstName,LastName from test2 where FirstName='Mario'";
            ps = connection.prepareStatement(query);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                System.out.println("FirstName - " + rs.getString("FirstName"));
                System.out.println("LastName - " + rs.getString("LastName"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void createUser(String nombreCompleto, String loginName, String password) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/M/dd");
        String date = sdf.format(new Date());
        UUID uuid = UUID.randomUUID();
        DB_Connection.DB_Connection obj_DB_Connection = new DB_Connection.DB_Connection();
        Connection connection = obj_DB_Connection.get_connection();
        PreparedStatement ps = null;
        try {
            String query = "insert into Usuarios Values ('" + uuid + "', '" + nombreCompleto + "', '" + loginName + "', '" + password + "', " + date + ")";
            ps = connection.prepareStatement(query);
            ps.executeUpdate();
            System.out.println("Usuario agregado con exito");

        } catch (SQLException e) {
            System.err.println("Usuario no agregado");
        }

    }

    public String loginRequest(String loginName, String password) {
        String temp = "Invalid User";

        DB_Connection.DB_Connection obj_DB_Connection = new DB_Connection.DB_Connection();
        Connection connection = obj_DB_Connection.get_connection();
        PreparedStatement ps = null;
        try {
            String query = "select UUID from Usuarios where LoginName='" + loginName + "' and Password='" + password + "'";
            ps = connection.prepareStatement(query);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            temp = rs.getString("UUID");

        } catch (SQLException e) {
            System.out.println(e);
        }

        return temp;
    }

    public ArrayList<String> lookupUser(UUID uuid) {
        ArrayList<String> temp = new ArrayList();

        DB_Connection.DB_Connection obj_DB_Connection = new DB_Connection.DB_Connection();
        Connection connection = obj_DB_Connection.get_connection();
        PreparedStatement ps = null;
        try {
            String query = "select UUID,NombreCompleto,LoginName,FechaCreacion from Usuarios where UUID='" + uuid + "'";
            ps = connection.prepareStatement(query);
            ps.execute();
            ResultSet rs = ps.getResultSet();

            temp.add(rs.getString("UUID"));
            temp.add(rs.getString("NombreCompleto"));
            temp.add(rs.getString("LoginName"));

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/M/dd");
            String date = sdf.format(rs.getDate("FechaCreacion"));

            temp.add(date);

        } catch (SQLException e) {
            System.out.println(e);
        }

        return temp;

    }

    public ArrayList<String> lookupUser(String username) {
        ArrayList<String> temp = new ArrayList();

        DB_Connection.DB_Connection obj_DB_Connection = new DB_Connection.DB_Connection();
        Connection connection = obj_DB_Connection.get_connection();
        PreparedStatement ps = null;
        try {
            String query = "select UUID,NombreCompleto,LoginName,FechaCreacion from Usuarios where LoginName='" + username + "'";
            ps = connection.prepareStatement(query);
            ps.execute();
            ResultSet rs = ps.getResultSet();

            temp.add(rs.getString("UUID"));
            temp.add(rs.getString("NombreCompleto"));
            temp.add(rs.getString("LoginName"));

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/M/dd");
            String date = sdf.format(rs.getDate("FechaCreacion"));

            temp.add(date);

        } catch (SQLException e) {
            System.out.println(e);
        }

        return temp;

    }

    public int deleteUser(String username, String password) {
        DB_Connection.DB_Connection obj_DB_Connection = new DB_Connection.DB_Connection();
        Connection connection = obj_DB_Connection.get_connection();
        PreparedStatement ps = null;
        try {
            String query = "delete from Usuarios where LoginName='"+username+"'";
            ps = connection.prepareStatement(query);
            ps.executeUpdate();
            System.out.println("Usuario Eliminado con exito");
        } catch (SQLException e) {
            System.err.println("Usuario No Eliminado");
        }
        return -1;
    }

    public int updateLoginUser(String newloginName, String oldLoginName) {
        DB_Connection.DB_Connection obj_DB_Connection = new DB_Connection.DB_Connection();
        Connection connection = obj_DB_Connection.get_connection();
        PreparedStatement ps = null;
        try {
            String query = "update Usuarios set LoginName='"+newloginName+"' where LoginName='"+oldLoginName+"'";
            ps = connection.prepareStatement(query);
            ps.executeUpdate();
            System.out.println("Cambio realizado con exito");
        } catch (SQLException e) {
            System.err.println("No se logro realizar el cambio");
        }
        
        return -1;
    }

    public ArrayList<String> getUsers() {

        ArrayList<String> temp = new ArrayList();
        String user = "";

        DB_Connection.DB_Connection obj_DB_Connection = new DB_Connection.DB_Connection();
        Connection connection = obj_DB_Connection.get_connection();
        PreparedStatement ps = null;
        try {
            String query = "select UUID,NombreCompleto,LoginName,FechaCreacion from Usuarios";
            ps = connection.prepareStatement(query);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {

                temp.add(rs.getString("UUID"));
                temp.add(rs.getString("NombreCompleto"));
                temp.add(rs.getString("LoginName"));
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/M/dd");
                String date = sdf.format(rs.getDate("FechaCreacion"));
                temp.add(date);

                System.out.println("UUID -> " + rs.getString("UUID"));
                System.out.println("NombreCompleto-> " + rs.getString("NombreCompleto"));
                System.out.println("LoginName -> " + rs.getString("LoginName"));
                System.out.println("Fecha de Creacion -> " + rs.getDate("FechaCreacion"));

            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return temp;
    }

}
