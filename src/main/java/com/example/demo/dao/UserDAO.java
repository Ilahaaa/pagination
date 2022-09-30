package com.example.demo.dao;

import com.example.demo.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    static Connection connection;
    static PreparedStatement ps;
    private Connection conn;

    public UserDAO(Connection conn) {
        super();
        this.conn=conn;
    }



    public List<User> getAllUsers( int page, int npp ){
        ArrayList<User> list=new ArrayList<User>();

        int start = (page - 1) * npp;
        try {
            connection = DbConnection.connectDb();
            ps=connection.prepareStatement("select * from users limit "+npp+" OFFSET " + start);
            ResultSet rs=ps.executeQuery();

            while (rs.next()){
                User u=new User();
                u.setId(rs.getInt("id"));
                u.setName(rs.getString("name"));
                u.setSurname(rs.getString("surname"));
                u.setAge(rs.getInt("age"));

                list.add(u);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }


    public int total() throws SQLException {
        connection = DbConnection.connectDb();
        ps=connection.prepareStatement("select count(*) as total from users");
        ResultSet rs = ps.executeQuery();
        rs.next();
        int count = rs.getInt("total") ;
        rs.close() ;
      return count;
    }

    public String pagination(int total, int p ){
        String output = "";
        for ( int i=1; i<=total; i++){
            if( p == i ) {
                output += " <a style=\"color:red;\">" + i + "</a>";
            }else{
                output += " <a href=\"?page=" + i + "\">" + i + "</a>";
            }
        }
        return output;
    }



}
