package com.quetzalcoatl.jpacourses.repository;

import com.quetzalcoatl.jpacourses.entity.Address;
import com.quetzalcoatl.jpacourses.entity.Passport;
import com.quetzalcoatl.jpacourses.entity.Student;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
class StudentRepositoryTest {

    @Autowired
    StudentRepository repository;

    @Autowired
    EntityManager em;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    @Transactional
    void setAddressDetails(){
        Student student = em.find(Student.class, 2001L);
        student.setAddress(new Address("BLR", "Minsk", "220006"));
        em.flush();
        logger.info(student.toString());
        logger.info(student.getAddress().toString());
    }

    @Test
    @Transactional
    void retrieveStudentAndPassport(){
        Student student = em.find(Student.class, 2001L);
        logger.info(student.toString());
        logger.info(student.getPassport().toString());
    }

    @Test
    @Transactional
    void retrievePassportAndAssociatedStudent(){
        Passport passport = em.find(Passport.class, 3003L);
        logger.info(passport.toString());
        logger.info(passport.getStudent().toString());
    }

    @Test
    @Transactional
    void retrieveStudentAndCourses(){
        Student student = repository.findById(2001L);
        logger.info("student ->{}", student);
        logger.info("courses -> {}", student.getCourses());
    }

    @Test
    void someTest(){
        repository.someOperationWithPersistenceContext();
    }

}