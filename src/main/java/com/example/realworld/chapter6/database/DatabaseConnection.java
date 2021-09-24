package com.example.realworld.chapter6.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseConnection {
    
    static{
        try{
            Class.forName("org.mariadb.jdbc.Driver");
        }catch(ClassNotFoundException e){
            throw new Error(e);
        }
    }

    static Connection get() throws SQLException {
        return DriverManager.getConnection("jdbc:mariadb://localhost:3306/root", "root", "root");
    }
}
