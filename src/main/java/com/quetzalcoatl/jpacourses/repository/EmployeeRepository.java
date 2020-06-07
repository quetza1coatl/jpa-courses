package com.quetzalcoatl.jpacourses.repository;

import com.quetzalcoatl.jpacourses.entity.Employee;
import com.quetzalcoatl.jpacourses.entity.FullTimeEmployee;
import com.quetzalcoatl.jpacourses.entity.PartTimeEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class EmployeeRepository {

    @Autowired
    private EntityManager em;

    @Transactional
    public void insert(Employee employee) {
        em.persist(employee);

    }

//    public List<Employee> getAll() {
//        return em.createQuery("SELECT emp FROM Employee emp", Employee.class).getResultList();
//    }


    public List<PartTimeEmployee> getAllPartTimeEmployee() {
        return em.createQuery("SELECT emp FROM PartTimeEmployee emp", PartTimeEmployee.class).getResultList();
    }

    public List<FullTimeEmployee> getAllFullTimeEmployee() {
        return em.createQuery("SELECT emp FROM FullTimeEmployee emp", FullTimeEmployee.class).getResultList();
    }

}

