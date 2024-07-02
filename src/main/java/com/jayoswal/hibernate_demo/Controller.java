package com.jayoswal.hibernate_demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class Controller {

    @Autowired
    private EmployeeService service;

    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        // code here to get session
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Boolean exists = session.contains(employee);
        System.out.println("BEFORE SAVING");
        System.out.println("Persistent Context have employee? -> " + exists);


            Employee saved = service.saveEmployee(employee);
            System.out.println("AFTER SAVING");
            System.out.println("Persistent Context have employee? -> " + exists);
            return new ResponseEntity<Employee>(saved, HttpStatus.CREATED);

    }

    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") int employeeId) {
        // code here to get session
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Employee e1 =  session.get(Employee.class, employeeId);
        Boolean exists = session.contains(e1);

        System.out.println("FIRST GET");
        System.out.println("Persistent Context have 1 id? -> " + exists);

        Employee e2 =  session.get(Employee.class, employeeId);
        Boolean exists2 = session.contains(e2);

        System.out.println("SECOND GET");
        System.out.println("Persistent Context have 1 id? -> " + exists2);

        return new ResponseEntity<Employee>(e2, HttpStatus.OK);
    }

    @PostMapping("hibernate")
    public ResponseEntity<Employee> saveEmployeeByHibernate(@RequestBody Employee employee) {
        // code here to get session
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();

        Boolean exists = session.contains(employee);
        System.out.println("BEFORE SAVING");
        System.out.println("Persistent Context have employee? -> " + exists);

        transaction.begin();
        session.save(employee);
        transaction.commit();

        System.out.println("AFTER SAVING");
        System.out.println("Persistent Context have employee? -> " + exists);
        return new ResponseEntity<>(null, HttpStatus.CREATED);

    }

    @GetMapping("showELC")
    public void showELC(){
        service.showELC();
    }
}
