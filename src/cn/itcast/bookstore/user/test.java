package cn.itcast.bookstore.user;

import cn.itcast.bookstore.user.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class test {

    public static void main(String[] args) {
        try {
            //1，得到Connection对象，
            Connection connection = getConnection();
            //2，通过Connection获取一个操作sql语句的对象Statement
            Statement statement = connection.createStatement();
            //3，拼接sql语句
            String sql = "select * from tb_user where username = " + "'dsaf'";
            //4，查询，返回的结果放入ResultSet对象中。
            ResultSet resultSet = statement.executeQuery(sql);
            //5，将游标后移一位

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore", "root", "Zhchming1");
            return connection;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
