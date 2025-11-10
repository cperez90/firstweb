package org.daw.firstweb.util;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;

@Getter
public class JdbcConnector {
    private static String url = "jdbc:mysql://db:3306/movies";
    private static String user = "root";
    private static String password = "root";

    public Connection getJdbc(){
        try {
            // The newInstance() call is a work around for some
            // broken Java implementations

            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url,user,password);
        } catch (Exception ex) {
            // handle the error
            throw new RuntimeException("No mysql jdbc driver found");
        }
    }
}
