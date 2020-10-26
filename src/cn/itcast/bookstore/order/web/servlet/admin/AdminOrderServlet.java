package cn.itcast.bookstore.order.web.servlet.admin;

import cn.itcast.bookstore.order.domain.Order;
import cn.itcast.bookstore.order.service.OrderService;
import cn.itcast.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AdminOrderServlet extends BaseServlet {

    private OrderService service = new OrderService();

    public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Order> orderList = service.findAll();
        request.setAttribute("orderList", orderList);
        return "/adminjsps/admin/order/list.jsp";
    }

    public String notPayed(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Order> orderList = service.notPayed();
        request.setAttribute("orderList", orderList);
        return "/adminjsps/admin/order/list.jsp";
    }

    public String hasPayed(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Order> orderList = service.hasPayed();
        request.setAttribute("orderList", orderList);
        return "/adminjsps/admin/order/list.jsp";
    }

    public String notReceived(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Order> orderList = service.notReceived();
        request.setAttribute("orderList", orderList);
        return "/adminjsps/admin/order/list.jsp";
    }

    public String finished(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Order> orderList = service.finished();
        request.setAttribute("orderList", orderList);
        return "/adminjsps/admin/order/list.jsp";
    }
}
