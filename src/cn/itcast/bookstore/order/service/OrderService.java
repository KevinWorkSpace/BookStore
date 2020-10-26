package cn.itcast.bookstore.order.service;

import cn.itcast.bookstore.order.dao.OrderDao;
import cn.itcast.bookstore.order.domain.Order;
import org.omg.CORBA.ORB;

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

    public void confirm(String oid) throws OrderException{
        int state = dao.getStateByOid(oid);
        if (state != 3) throw new OrderException("订单确认失败");
        dao.updateState(oid, 4);
    }

    public List<Order> findAll() {
        return dao.findAll();
    }

    public List<Order> notPayed() {
        return dao.notPayed();
    }

    public List<Order> hasPayed() {
        return dao.hasPayed();
    }

    public List<Order> notReceived() {
        return dao.notReceived();
    }

    public List<Order> finished() {
        return dao.finished();
    }
}
