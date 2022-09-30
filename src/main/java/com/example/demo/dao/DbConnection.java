package com.example.demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static String name="postgres";
    private static String password="ilaha2401";
private static  String url="jdbc:postgresql://localhost:5432/postgres";
private static Connection conn=null;
public static Connection connectDb() throws SQLException{
    try {
        Class.forName("org.postgresql.Driver") ;
        conn= DriverManager.getConnection(url,name,password);

    } catch (ClassNotFoundException e) {
       e.printStackTrace();
    }
return conn;
}

}
