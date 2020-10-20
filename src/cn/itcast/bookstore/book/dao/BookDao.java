package cn.itcast.bookstore.book.dao;

import cn.itcast.bookstore.book.domain.Book;
import cn.itcast.bookstore.category.domain.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao {

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

    public List<Book> findAll() {
        try {
            //1，得到Connection对象，
            Connection connection = getConnection();
            //2，通过Connection获取一个操作sql语句的对象Statement
            Statement statement = connection.createStatement();
            //3，拼接sql语句
            String sql = "select * from book";
            //4，查询，返回的结果放入ResultSet对象中。
            ResultSet resultSet = statement.executeQuery(sql);
            //5，将游标后移一位
            List<Book> list = new ArrayList<>();
            while (resultSet.next()) {
                String bid = resultSet.getString(1);
                String bname = resultSet.getString(2);
                double price = resultSet.getDouble(3);
                String author = resultSet.getString(4);
                String image = resultSet.getString(5);
                String cid = resultSet.getString(6);
                Category category = new Category();
                category.setCid(cid);
                Book book = new Book();
                book.setBid(bid);
                book.setBname(bname);
                book.setPrice(price);
                book.setAuthor(author);
                book.setImage(image);
                book.setCategory(category);
                list.add(book);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
