package com.test.filter;

import com.test.exceptions.NotFoundException;
import static com.test.service.StudentService.getInstance;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CookieFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        httpServletResponse.setContentType("text/html");

        boolean s = false;


        Cookie[] cookies = httpServletRequest.getCookies();

        for (Cookie a : cookies) {
            try {
                if (a.getName().equals("username") && getInstance().getByUsername(a.getValue())) {
                    s = true;
                    break;
                }
            } catch (NotFoundException e) {

                e.printStackTrace();
            }
        }
        if (s) {
            filterChain.doFilter(httpServletRequest,httpServletResponse);
        }else{
            httpServletResponse.sendRedirect("StudentLogin");
        }
    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }


    @Override
    public void destroy() {

    }
}
