/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distribucionchat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ServidorSQL {

    public static void main(String[] args) {
        ServidorSQL obj_DAO = new ServidorSQL();
        obj_DAO.Check_Data();
    }

    public void Check_Data() {
        DB_Connection.DB_Connection obj_DB_Connection = new DB_Connection.DB_Connection();
        Connection connection = obj_DB_Connection.get_connection();
        PreparedStatement ps = null;
        try {
            String query = "select * from test1";
            ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("name- " + rs.getString("name"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
