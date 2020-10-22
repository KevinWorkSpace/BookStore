package cn.itcast.bookstore.order.web.servlet;

import cn.itcast.bookstore.cart.domain.Cart;
import cn.itcast.bookstore.cart.domain.CartItem;
import cn.itcast.bookstore.order.domain.Order;
import cn.itcast.bookstore.order.domain.OrderItem;
import cn.itcast.bookstore.order.service.OrderException;
import cn.itcast.bookstore.order.service.OrderService;
import cn.itcast.bookstore.user.domain.User;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class OrderServlet extends BaseServlet {

    private OrderService service = new OrderService();

    public String confirm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oid = request.getParameter("oid");
        try {
            service.confirm(oid);
            request.setAttribute("msg", "恭喜， 确认成功");
        } catch (OrderException e) {
            request.setAttribute("msg", e.getMessage());
        }
        return "f:/jsps/msg.jsp";
    }

    public String load(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oid = request.getParameter("oid");
        request.setAttribute("order", service.load(oid));
        return "f:/jsps/order/desc.jsp";
    }

    public String myOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("session_user");
        request.setAttribute("myOrderList", service.myOrders(user.getUid()));
        return "f:/jsps/order/list.jsp";
    }

    public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        Order order = new Order();
        order.setOid(CommonUtils.uuid());
        order.setOrdertime(new Date());
        order.setState(1);
        User user = (User) request.getSession().getAttribute("session_user");
        order.setOwner(user);
        order.setTotal(cart.getTotal());
        List<OrderItem> list = new ArrayList<>();
        for (CartItem cartItem : cart.getCartItems()) {
            OrderItem oi = new OrderItem();
            oi.setIid(CommonUtils.uuid());
            oi.setCount(cartItem.getCount());
            oi.setBook(cartItem.getBook());
            oi.setOrder(order);
            oi.setSubtotal(cartItem.getSubtotal());
            list.add(oi);
        }
        order.setOrderItemList(list);
        cart.clear();
        service.add(order);
        request.setAttribute("order", order);
        return "jsps/order/desc.jsp";
    }
}
