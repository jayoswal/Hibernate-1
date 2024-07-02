package com.jayoswal.hibernate_demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@org.springframework.stereotype.Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private AddressService addressService;

    //@Transactional(propagation = Propagation.REQUIRED)
    public Employee saveEmployee(Employee employee) {
        Employee saved = repository.save(employee);

        if(saved.getName() != null) {
            // throw new RuntimeException();
        }

        Address address = new Address();
        address.setAddress("Satara");
        address.setEmployee(saved);
//        try {
//            addressService.addNew(address);
//        }
//        catch (DataIntegrityViolationException e) {
//            // we try to save employee in adress which doe not exisits yet
//            System.out.println(e.getClass());
//        }

        // remove transactional in this method at line 19 saveEmployee()
        addressService.addNewMandatory(address);

        // make this transactional as required at line 19
        addressService.addNewNever(address);

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
