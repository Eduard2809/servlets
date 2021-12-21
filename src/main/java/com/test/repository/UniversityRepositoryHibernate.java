package com.test.repository;

import com.test.model.University;
import com.test.model.UniversityType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class UniversityRepositoryHibernate implements UniversityRepository {

    //    private static SessionFactory factory;
    public void save(String name, String street, UniversityType type) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        University u1 = new University(name,street,type);
        session.save(u1);
        t.commit();
        factory.close();
        session.close();

    }



//    public UniversityRepositoryHibernate() {
//        try {
//            factory = new AnnotationConfiguration().configure().addAnnotatedClass(University.class).buildSessionFactory();
//        } catch (Throwable ex) {
//            System.err.println("Failed to create sessionFactory object." + ex);
//            throw new ExceptionInInitializerError(ex);
//        }
//    }
//}
//    public void save(String name, String street){
//        Session session = factory.openSession();
//        Transaction tx = null;
//
//        try {
//            tx = session.beginTransaction();
//            University university = new University(name, street);
//            session.save(university);
//            tx.commit();
//        } catch (HibernateException e) {
//            if (tx!=null) tx.rollback();
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//    }
}
