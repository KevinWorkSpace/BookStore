package cn.itcast.bookstore.category.web.servlet.admin;

import cn.itcast.bookstore.category.domain.Category;
import cn.itcast.bookstore.category.service.CategoryService;
import cn.itcast.bookstore.category.service.DelectException;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AdminCategoryServlet extends BaseServlet {

    private CategoryService service = new CategoryService();

    public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> list = service.findAll();
        request.setAttribute("categoryList", list);
        return "f:/adminjsps/admin/category/list.jsp";
    }

    public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Category category = CommonUtils.toBean(request.getParameterMap(), Category.class);
        category.setCid(CommonUtils.uuid());
        service.add(category);
        return findAll(request, response);
    }

    public String del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = request.getParameter("cid");
        Category category = new Category();
        category.setCid(cid);
        try {
            service.del(category);
        } catch (DelectException e) {
            request.setAttribute("msg", e.getMessage());
            return "f:/adminjsps/admin/msg.jsp";
        }
        return findAll(request, response);
    }

    public String editPre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = request.getParameter("id");
        Category category = service.findById(cid);
        System.out.println(category.getCid() + " " + category.getCname());
        request.setAttribute("category", category);
        return "f:/adminjsps/admin/category/mod.jsp";
    }

    public String edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = request.getParameter("cid");
        String cname = request.getParameter("cname");
        System.out.println(cid + "===" + cname);
        Category category = new Category();
        category.setCid(cid);
        category.setCname(cname);
        service.edit(category);
        return findAll(request, response);
    }
}
