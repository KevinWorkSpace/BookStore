package cn.itcast.bookstore.category.web.servlet;

import cn.itcast.bookstore.category.service.CategoryService;
import cn.itcast.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CategoryServlet extends BaseServlet {

    private CategoryService service = new CategoryService();

    public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("categoryList", service.findAll());
        return "f:/jsps/left.jsp";
    }
}
