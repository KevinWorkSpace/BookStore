package cn.itcast.bookstore.user.web.servlet;

import cn.itcast.bookstore.cart.domain.Cart;
import cn.itcast.bookstore.user.domain.User;
import cn.itcast.bookstore.user.service.UserException;
import cn.itcast.bookstore.user.service.UserService;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class UserServlet extends BaseServlet {

    private UserService userService = new UserService();

    public String quit(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getSession().invalidate();
        return "r:/index.jsp";
    }

    public String login(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        User form = CommonUtils.toBean(req.getParameterMap(), User.class);
        try {
            User user = userService.login(form);
            req.getSession().setAttribute("session_user", user);
            req.getSession().setAttribute("cart", new Cart());
            return "r:/index.jsp";
        } catch (UserException e) {
            req.setAttribute("msg", e.getMessage());
            req.setAttribute("form", form);
            return "f:/jsps/user/login.jsp";
        }

    }

    public String register(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        User form = CommonUtils.toBean(req.getParameterMap(), User.class);
        form.setUid(CommonUtils.uuid());
        form.setCode(CommonUtils.uuid() + CommonUtils.uuid());
        Map<String, String> errors = new HashMap<>();
        String name = form.getUsername();
        if (name == null || name.trim().isEmpty()) {
            errors.put("username", "username can't be empty");
        }
        else if (name.length() < 3 || name.length() > 10) {
            errors.put("username", "the length of username must from 3 to 10");
        }
        String password = form.getPassword();
        if (password == null || password.trim().isEmpty()) {
            errors.put("password", "password can't be empty");
        }
        else if (password.length() < 3 || password.length() > 10) {
            errors.put("password", "the length of password must from 3 to 10");
        }
        String email = form.getEmail();
        if (email == null || email.trim().isEmpty()) {
            errors.put("email", "email can't be empty");
        }
        if (errors.size() > 0) {
            req.setAttribute("errors", errors);
            req.setAttribute("form", form);
            return "f:/jsps/user/regist.jsp";
        }
        try {
            userService.regist(form);
        } catch (UserException e) {
            req.setAttribute("msg", e.getMessage());
            req.setAttribute("form", form);
            return "f:/jsps/user/regist.jsp";
        }
        req.setAttribute("msg", "regist success, please active the email");
        return "f:/jsps/msg.jsp";
    }
}
