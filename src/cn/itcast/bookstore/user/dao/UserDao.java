package cn.itcast.bookstore.user.dao;

import cn.itcast.bookstore.user.domain.User;
import cn.itcast.jdbc.TxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private QueryRunner qr = new TxQueryRunner();

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

    public User findByUsername(String username) {
        try {
            //1，得到Connection对象，
            Connection connection = getConnection();
            //2，通过Connection获取一个操作sql语句的对象Statement
            Statement statement = connection.createStatement();
            //3，拼接sql语句
            String sql = "select * from tb_user where username = " + "'" + username + "'";
            //4，查询，返回的结果放入ResultSet对象中。
            ResultSet resultSet = statement.executeQuery(sql);
            //5，将游标后移一位
            if (resultSet.next()) {
                String uuid = resultSet.getString(1);
                String password = resultSet.getString(3);
                String email = resultSet.getString(4);
                String code = resultSet.getString(5);
                boolean state = resultSet.getBoolean(6);
                User user = new User();
                user.setUid(uuid);
                user.setUsername(username);
                user.setPassword(password);
                user.setEmail(email);
                user.setCode(code);
                user.setState(state);
                return user;
            }
            else return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User findByEmail(String email) {
        try {
            //1，得到Connection对象，
            Connection connection = getConnection();
            //2，通过Connection获取一个操作sql语句的对象Statement
            Statement statement = connection.createStatement();
            //3，拼接sql语句
            String sql = "select * from tb_user where email = " + "'" + email + "'";
            //4，查询，返回的结果放入ResultSet对象中。
            ResultSet resultSet = statement.executeQuery(sql);
            //5，将游标后移一位
            if (resultSet.next()) return new User();
            else return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void add(User user) {
        try {
            Connection connection = getConnection();
            //2，通过Connection获取一个操作sql语句的对象Statement
            Statement statement = connection.createStatement();
            //3，拼接sql语句
            String sql = "insert into tb_user values(" + "'" + user.getUid() + "'" + "," + "'" + user.getUsername() + "'" + "," + "'" +
                    user.getPassword()+ "'" + "," + "'" + user.getEmail() + "'" + "," + "'" + user.getCode() + "'" + "," + user.isState() + ")";
            //4，查询，返回的结果放入ResultSet对象中。
            System.out.println(sql);
            statement.executeUpdate(sql);
            //7，释放资源
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
