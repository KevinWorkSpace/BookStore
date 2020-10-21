package cn.itcast.bookstore.cart.web.servlet;

import cn.itcast.bookstore.book.domain.Book;
import cn.itcast.bookstore.book.service.BookService;
import cn.itcast.bookstore.cart.domain.Cart;
import cn.itcast.bookstore.cart.domain.CartItem;
import cn.itcast.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CartServlet extends BaseServlet {

    public String add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        String bid = req.getParameter("bid");
        Book book = new BookService().load(bid);
        CartItem item = new CartItem();
        item.setBook(book);
        int count = Integer.parseInt(req.getParameter("count"));
        item.setCount(count);
        cart.add(item);
        return "f:/jsps/cart/list.jsp";
    }

    public String delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        String bid = req.getParameter("bid");
        cart.delete(bid);
        return "f:/jsps/cart/list.jsp";
    }

    public String clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        cart.clear();
        return "f:/jsps/cart/list.jsp";
    }

}
