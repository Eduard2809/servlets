package com.test.controller;

import com.test.exceptions.NotFoundException;
import com.test.model.Student;
import com.test.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class UserPage extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.sendRedirect("userPage.html");

    }
}

