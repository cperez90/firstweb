package org.daw.firstweb.util;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Getter
public class JdbcConnector {
    private static String url = "jdbc:mysql://db:3306/movies";
    private static String user = "root";
    private static String password = "root";

   static {
        try {
            // The newInstance() call is a work around for some
            // broken Java implementations

            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception ex) {
            // handle the error
            throw new RuntimeException("No mysql jdbc driver found");
        }
   }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user,password);
    }
}
