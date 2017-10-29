/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fci
 */
public class db_Connection {
    private static final String DBNAME = "Sample";
    private static final String PASSWORD = "root";//mar59033311!@#mar

    public db_Connection() {
        //   
    }
    private static Connection connection = null;

    public static Connection getActiveConnection() {
System.setProperty("jdbc.drivers", "com.mysql.jdbc.Driver");
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            
            connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DBNAME, "root", PASSWORD);
           System.out.println("Connection = "+connection);
            return connection;
        } catch (ClassNotFoundException e) {
             System.out.println("Driver could not be loaded: " + e);
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(db_Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
