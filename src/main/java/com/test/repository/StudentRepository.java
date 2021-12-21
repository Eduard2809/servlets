package com.test.repository;

import com.test.model.Student;

import java.util.ArrayList;

public interface StudentRepository {

    void save(String name, int age, String username, String password);
    Student login(String username, String password);
    void delete(int id);
    void select(ArrayList<Student> students);
    void search(Student student, int id);
    void changeAge(int age, int id);


}
