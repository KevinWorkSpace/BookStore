package cn.itcast.bookstore.book.web.servlet.admin;

import cn.itcast.bookstore.book.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminAddBookServlet extends HttpServlet {

    private BookService service = new BookService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
