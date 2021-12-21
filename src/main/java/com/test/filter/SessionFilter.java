package com.test.filter;

import com.test.model.Student;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class SessionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        httpServletResponse.setContentType("text/html");
        PrintWriter out = httpServletResponse.getWriter();
        Student student = null;

        HttpSession session = httpServletRequest.getSession();
        Object object = session.getAttribute("student");

        if (object != null) {
            if (object instanceof Student) {
                student = (Student) object;
                out.println(student.getUsername());
            }
        } else {
            chain.doFilter(httpServletRequest, httpServletResponse);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
