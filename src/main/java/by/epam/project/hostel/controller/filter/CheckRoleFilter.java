package by.epam.project.hostel.controller.filter;

import by.epam.project.hostel.entity.User;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class CheckRoleFilter implements Filter {

    private static final String USER = "user";
    private static final String ADMIN = "ADMIN";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        User user = (User) httpRequest.getSession().getAttribute(USER);
        if (user != null && String.valueOf(user.getRole()).equals(ADMIN)) {
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_NOT_FOUND);
        }

    }

    @Override
    public void destroy() {

    }
}
