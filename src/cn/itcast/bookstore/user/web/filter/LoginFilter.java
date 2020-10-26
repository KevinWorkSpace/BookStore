package cn.itcast.bookstore.user.web.filter;

import cn.itcast.bookstore.user.domain.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        User user = (User) httpServletRequest.getSession().getAttribute("session_user");
        if (user != null) {
            chain.doFilter(req, resp);
        }
        else {
            httpServletRequest.setAttribute("msg", "您还没有登陆");
            httpServletRequest.getRequestDispatcher("/jsps/user/login.jsp").forward(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
