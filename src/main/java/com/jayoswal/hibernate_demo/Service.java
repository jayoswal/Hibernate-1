package com.jayoswal.hibernate_demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    private Repository repository;

    public Employee saveEmployee(Employee employee){
        Employee saved = repository.save(employee);
        return saved;
    }

    public void showELC() {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        System.out.println("First GET");
        Employee employee = session.get(Employee.class, 1);
        Boolean exists1 = session.contains(employee);
        System.out.println("Exists :" + exists1);

        // session clear -> goes into detached state
        System.out.println("Clearing Session");
        session.clear();
        Boolean exists2 = session.contains(employee);
        System.out.println("Exists :" + exists2);

        // remove -> goes into removed state
        System.out.println("Removing employee object");
        session.remove(employee);
        Boolean exists3 = session.contains(employee);
        System.out.println("Exists :" + exists3);

    }
}
