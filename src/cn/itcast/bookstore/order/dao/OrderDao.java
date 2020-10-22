package cn.itcast.bookstore.order.dao;

import cn.itcast.bookstore.book.domain.Book;
import cn.itcast.bookstore.category.domain.Category;
import cn.itcast.bookstore.order.domain.Order;
import cn.itcast.bookstore.order.domain.OrderItem;
import cn.itcast.bookstore.user.domain.User;
import cn.itcast.commons.CommonUtils;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDao {

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

    public void addOrder(Order order) {
        try {
            Connection connection = getConnection();
            //2，通过Connection获取一个操作sql语句的对象Statement
            Statement statement = connection.createStatement();
            //3，拼接sql语句
            Timestamp timestamp = new Timestamp(order.getOrdertime().getTime());
            String sql = "insert into orders values(" +
                    "'" + order.getOid() + "'" + "," +
                    "'" + timestamp + "'" + "," +
                    order.getTotal() + "," +
                    order.getState() + "," +
                    "'" + order.getOwner().getUid() + "'" + "," +
                    "''" +
                    ")";
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

    public void addOrderItemList(List<OrderItem> orderItemList) {
        try {
            Connection connection = getConnection();
            //2，通过Connection获取一个操作sql语句的对象Statement
            Statement statement = connection.createStatement();
            //3，拼接sql语句
           for (int i=0; i<orderItemList.size(); i++) {
                OrderItem item = orderItemList.get(i);
                String sql = "insert into orderitem values(" +
                        "'" + item.getIid() + "'" + "," +
                        item.getCount() + "," +
                        item.getSubtotal() + "," +
                        "'" + item.getOrder().getOid() + "'" + "," +
                        "'" + item.getBook().getBid() + "'" +
                        ")";
                //4，查询，返回的结果放入ResultSet对象中。
                System.out.println(sql);
                statement.executeUpdate(sql);
            }
            //7，释放资源
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Order> myOrders(String uid) {
        try {
            //1，得到Connection对象，
            Connection connection = getConnection();
            //2，通过Connection获取一个操作sql语句的对象Statement
            Statement statement = connection.createStatement();
            //3，拼接sql语句
            String sql = "select * from orders where uid = '" + uid + "'";
            //4，查询，返回的结果放入ResultSet对象中。
            ResultSet resultSet = statement.executeQuery(sql);
            //5，将游标后移一位
            List<Order> list = new ArrayList<>();
            while (resultSet.next()) {
                String oid = resultSet.getString(1);
                Timestamp orderTime = resultSet.getTimestamp(2);
                double total = resultSet.getDouble(3);
                int state = resultSet.getInt(4);
                String address = resultSet.getString(6);
                Order order = new Order();
                order.setOid(oid);
                order.setOrdertime(orderTime);
                order.setTotal(total);
                order.setState(state);
                order.setAddress(address);
                List<Map<String, Object>> mapList = myOrderItem(order);
                List<OrderItem> orderItemList = toOrderItemList(mapList, order);
                order.setOrderItemList(orderItemList);
                list.add(order);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<OrderItem> toOrderItemList(List<Map<String, Object>> mapList, Order order) {
        List<OrderItem> orderItemList = new ArrayList<>();
        for (Map<String, Object> map : mapList) {
            OrderItem item = toOrderItem(map, order);
            orderItemList.add(item);
        }
        return orderItemList;
    }

    private OrderItem toOrderItem(Map<String, Object> map, Order order) {
        OrderItem item = CommonUtils.toBean(map, OrderItem.class);
        Book book = CommonUtils.toBean(map, Book.class);
        item.setBook(book);
        return item;
    }

    public List<Map<String, Object>> myOrderItem(Order order) {
        try {
            //1，得到Connection对象，
            Connection connection = getConnection();
            //2，通过Connection获取一个操作sql语句的对象Statement
            Statement statement = connection.createStatement();
            //3，拼接sql语句
            String sql = "select * from orderitem i, book b where i.bid=b.bid and oid = '" + order.getOid() + "'";
            //4，查询，返回的结果放入ResultSet对象中。
            ResultSet resultSet = statement.executeQuery(sql);
            //5，将游标后移一位
            List<Map<String, Object>> list = new ArrayList<>();
            while (resultSet.next()) {
                Map<String, Object> map = new HashMap<>();
                map.put("iid", resultSet.getString(1));
                map.put("count", resultSet.getInt(2));
                map.put("subtotal", resultSet.getDouble(3));
                map.put("oid", resultSet.getString(4));
                map.put("bid", resultSet.getString(5));
                map.put("bid", resultSet.getString(6));
                map.put("bname", resultSet.getString(7));
                map.put("price", resultSet.getDouble(8));
                map.put("author", resultSet.getString(9));
                map.put("image", resultSet.getString(10));
                map.put("cid", resultSet.getString(11));
                list.add(map);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Order load(String oid) {
        try {
            //1，得到Connection对象，
            Connection connection = getConnection();
            //2，通过Connection获取一个操作sql语句的对象Statement
            Statement statement = connection.createStatement();
            //3，拼接sql语句
            String sql = "select * from orders where oid = '" + oid + "'";
            //4，查询，返回的结果放入ResultSet对象中。
            ResultSet resultSet = statement.executeQuery(sql);
            //5，将游标后移一位
            if (resultSet.next()) {
                Timestamp orderTime = resultSet.getTimestamp(2);
                double total = resultSet.getDouble(3);
                int state = resultSet.getInt(4);
                String address = resultSet.getString(6);
                Order order = new Order();
                order.setOid(oid);
                order.setOrdertime(orderTime);
                order.setTotal(total);
                order.setState(state);
                order.setAddress(address);
                List<Map<String, Object>> mapList = myOrderItem(order);
                List<OrderItem> orderItemList = toOrderItemList(mapList, order);
                order.setOrderItemList(orderItemList);
                return order;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
