//Nama:Weddy Alvino
//NRP:1872063
package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {
    public static Connection getConnection(){
        Connection conn = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tubespbo",
                    "root",
                    ""
            );
        }
        catch (ClassNotFoundException | SQLException ex){
            System.out.println(ex.getMessage());
        }
        return conn;
    }
}
