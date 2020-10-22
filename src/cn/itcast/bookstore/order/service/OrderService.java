package cn.itcast.bookstore.order.service;

import cn.itcast.bookstore.order.dao.OrderDao;
import cn.itcast.bookstore.order.domain.Order;

import java.util.List;

public class OrderService {

    private OrderDao dao = new OrderDao();

    public void add(Order order) {
        dao.addOrder(order);
        dao.addOrderItemList(order.getOrderItemList());
    }

    public List<Order> myOrders(String uid) {
        return dao.myOrders(uid);
    }

    public Order load(String oid) {
        return dao.load(oid);
    }
}
