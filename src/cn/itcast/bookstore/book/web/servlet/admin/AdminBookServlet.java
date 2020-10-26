package cn.itcast.bookstore.book.web.servlet.admin;

import cn.itcast.bookstore.book.domain.Book;
import cn.itcast.bookstore.book.service.BookService;
import cn.itcast.bookstore.category.domain.Category;
import cn.itcast.bookstore.category.service.CategoryService;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdminBookServlet extends BaseServlet {

    private BookService service = new BookService();
    private CategoryService categoryService = new CategoryService();

    public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Book> list = service.findAll();
        System.out.println(list);
        request.setAttribute("bookList", list);
        return "f:/adminjsps/admin/book/list.jsp";
    }

    public String load(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Book book = service.load(request.getParameter("bid"));
        List<Category> list = categoryService.findAll();
        request.setAttribute("categoryList", list);
        request.setAttribute("book", book);
        return "f:/adminjsps/admin/book/desc.jsp";
    }

    public String addPre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> list = categoryService.findAll();
        request.setAttribute("categoryList", list);
        return "f:/adminjsps/admin/book/add.jsp";
    }

    public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bid = request.getParameter("bid");
        service.delete(bid);
        return findAll(request, response);
    }

    public String edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Book book = CommonUtils.toBean(request.getParameterMap(), Book.class);
        Category category = CommonUtils.toBean(request.getParameterMap(), Category.class);
        book.setCategory(category);
        service.edit(book);
        return findAll(request, response);
    }
}
