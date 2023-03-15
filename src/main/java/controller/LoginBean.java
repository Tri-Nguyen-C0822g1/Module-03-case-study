package controller;

import model.User;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginBean implements Serializable {
    public LoginBean() {
    }

    public User checkLogin(String username, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/casestudy";
            Connection con = DriverManager.getConnection(url, "root", "admin");
            String sql = "select * from Registration where username = ? and password = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            User user = null;
            if (rs.next()) {
                user = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getBoolean(4));
            }
            rs.close();
            stm.close();
            con.close();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public boolean insert(String username, String password, String lastname, boolean roles) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/casestudy";
            Connection con = DriverManager.getConnection(url, "root", "admin");
            String sql = "Insert into Registration(username, password, lastname, isAdmin) values (?, ?, ?, ?)";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            stm.setString(3, lastname);
            stm.setBoolean(4, roles);
            int result = stm.executeUpdate();
            stm.close();
            con.close();
            if (result > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
