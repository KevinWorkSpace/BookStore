package cn.itcast.bookstore.book.web.servlet;

import cn.itcast.bookstore.book.service.BookService;
import cn.itcast.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BookServlet extends BaseServlet {

    private BookService service = new BookService();

    public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("bookList", service.findAll());
        return "f:/jsps/book/list.jsp";
    }

    public String findByCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = request.getParameter("cid");
        request.setAttribute("bookList", service.findByCategory(cid));
        return "f:/jsps/book/list.jsp";
    }

    public String load(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bid = request.getParameter("bid");
        request.setAttribute("book", service.load(bid));
        return "f:/jsps/book/desc.jsp";
    }
}
