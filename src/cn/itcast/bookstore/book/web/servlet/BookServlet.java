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
}
