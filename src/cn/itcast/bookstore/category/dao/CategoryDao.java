package cn.itcast.bookstore.category.dao;

import cn.itcast.bookstore.category.domain.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {

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

    public List<Category> findAll() {
        try {
            //1，得到Connection对象，
            Connection connection = getConnection();
            //2，通过Connection获取一个操作sql语句的对象Statement
            Statement statement = connection.createStatement();
            //3，拼接sql语句
            String sql = "select * from category";
            //4，查询，返回的结果放入ResultSet对象中。
            ResultSet resultSet = statement.executeQuery(sql);
            //5，将游标后移一位
            List<Category> list = new ArrayList<>();
            while (resultSet.next()) {
                String cid = resultSet.getString(1);
                String cname = resultSet.getString(2);
                Category category = new Category();
                category.setCid(cid);
                category.setCname(cname);
                list.add(category);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
