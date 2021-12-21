package com.test.repository;

import com.test.model.Student;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StudentRepositoryHibernate implements StudentRepository{
    private static SessionFactory factory;

    public StudentRepositoryHibernate(){
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public void save(String name, int age, String username, String password){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Student student = new Student(name, age, username, password);
            session.save(student);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void changeAge(int age, int id){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Student student = (Student) session.get(Student.class, id);
            student.setAge(age);
            session.update(student);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    public Student login(String username, String password){
        Student student = null;
        Session session = factory.openSession();
        Transaction tx = null;
        String hql = "FROM Student WHERE username = :username AND password = :password";
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("username",username);
            query.setParameter("password",password);
            List students = query.getResultList();
            for (Iterator iterator = students.iterator(); iterator.hasNext();){
                student = (Student) iterator.next();
                student.setUsername(username);
                student.setPassword(password);
            }
            tx.commit();

        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return student;
    }

    @Override
    public void delete(int id) {
        Transaction tx = null;

        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            Student student = (Student) session.get(Student.class, id);
            session.delete(student);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void select(ArrayList<Student> students) {
        Session session = factory.openSession();

        try {
            List list = session.createQuery("FROM Student").list();
            for (Iterator iterator = list.iterator(); iterator.hasNext();){
                Student student = (Student) iterator.next();
                students.add(new Student(student.getId(),student.getName(),student.getAge()));
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void search(Student student, int id) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            Query query = session.createQuery("FROM Student WHERE id = :id");
            query.setParameter("id",id);
            List list = query.getResultList();
            for (Iterator iterator = list.iterator(); iterator.hasNext();){
                Student student1 = (Student) iterator.next();
                student.setName(student1.getName());
                student.setAge(student1.getAge());
            }
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
