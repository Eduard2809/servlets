package com.test.service;

import com.test.exceptions.DuplicateException;
import com.test.exceptions.NotFoundException;
import com.test.model.Student;
import com.test.repository.StudentRepository;
import com.test.repository.StudentRepositoryHibernate;
import com.test.repository.StudentRepositoryJdbc;

import java.util.ArrayList;

public class StudentService {

    private static StudentService instance;
    private StudentRepository studentRepository;

    private StudentService() {
        studentRepository = new StudentRepositoryHibernate();
    }

    public static StudentService getInstance() {
        if (instance == null) {
            instance = new StudentService();
        }
        return instance;
    }

    public void saveH(String name, int age, String username, String password) {
        studentRepository.save(name, age, username, password);
    }

    public void save(String name, int age, String username, String password) throws DuplicateException {
        StudentRepositoryJdbc sr = new StudentRepositoryJdbc();
        Student student = sr.getByUsername(username);
        if (student != null) {
            throw new DuplicateException();
        }
        sr.save(name, age, username, password);
    }

    public boolean getByUsername(String username) throws NotFoundException {
        StudentRepositoryJdbc sr = new StudentRepositoryJdbc();
        Student student = sr.getByUsername(username);
        if (student == null) {
            throw new NotFoundException();
        }
        return true;
    }

    public void loginH(String username, String password) throws NotFoundException {
        Student student = studentRepository.login(username, password);
        if (student == null) {
            throw new NotFoundException();
        }
    }

    public void login(String username, String password) throws NotFoundException {
        StudentRepository sr4 = new StudentRepositoryJdbc();
        Student student = sr4.login(username, password);
        if (student == null) {
            throw new NotFoundException();
        }
    }

    public void delete(int id) {
        StudentRepository sr1 = new StudentRepositoryJdbc();
        sr1.delete(id);
    }

    public void deleteH(int id) {
        studentRepository.delete(id);
    }

    public void select(ArrayList<Student> st) {
        StudentRepository sr1 = new StudentRepositoryJdbc();
        sr1.select(st);
    }

    public void selectH(ArrayList<Student> st) {
        studentRepository.select(st);
    }

    public void search(Student student, int id) {
        StudentRepository sr2 = new StudentRepositoryJdbc();
        sr2.search(student, id);
    }

    public void searchH(Student student, int id) {
        studentRepository.search(student, id);
    }

    public void changeAge(int age, int id) {
        StudentRepository sr3 = new StudentRepositoryJdbc();
        sr3.changeAge(age, id);
    }

    public void changeAgeH(int age, int id){
        studentRepository.changeAge(age,id);
    }
}
