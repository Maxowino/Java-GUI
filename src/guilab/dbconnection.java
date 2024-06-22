/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package guilab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbconnection {
    private static dbconnection instance;
    private Connection connection;
    private String url = "jdbc:mysql://localhost:3306/formdb";
    private String username = "root";
    private String password = "";

    private dbconnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database Connection Successfully Established");
        } catch (ClassNotFoundException ex) {
            System.out.println("Database Driver Class Not Found : " + ex.getMessage());
            throw new SQLException("Driver not found", ex);
        } catch (SQLException ex) {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
            throw ex;
        }
    }

    public Connection getConnection() throws SQLException {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(url, username, password);
                System.out.println("Database Connection Reestablished");
            }
        } catch (SQLException ex) {
            System.out.println("Failed to establish database connection: " + ex.getMessage());
            throw ex;
        }
        return connection;
    }

    public static dbconnection getInstance() throws SQLException {
        if (instance == null || instance.getConnection() == null || instance.getConnection().isClosed()) {
            instance = new dbconnection();
        }
        return instance;
    }
}
