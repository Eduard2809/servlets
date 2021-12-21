package com.test.controller;

import com.test.model.Student;
import com.test.model.University;
import com.test.model.UniversityType;
import com.test.service.UniversityService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static com.test.service.StudentService.getInstance;

public class UniversityRegister extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        University university = new University();
        // Set response content type
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        university.setName(request.getParameter("name"));
        university.setStreet(request.getParameter("street"));
        UniversityType type = UniversityType.valueOf(request.getParameter("university_type"));

        UniversityService universityService = new UniversityService();
        universityService.save(university.getName(),university.getStreet(),type);
        out.println("DONE");
//        } catch (DuplicateException e) {
//            out.println("DUPLICATE USERNAME");
//            e.printStackTrace();
//        }
    }
}
