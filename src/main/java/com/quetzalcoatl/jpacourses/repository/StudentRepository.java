package com.quetzalcoatl.jpacourses.repository;

import com.quetzalcoatl.jpacourses.entity.Course;
import com.quetzalcoatl.jpacourses.entity.Passport;
import com.quetzalcoatl.jpacourses.entity.Student;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class StudentRepository {

    @PersistenceContext
    private EntityManager em;

    public Student findById(Long id){
        return em.find(Student.class, id);
    }

    @Transactional
    public void deleteById(Long id){
        Student s = findById(id);
        em.remove(s);
    }

    @Transactional
    public Student save(Student student){
        if(student.getId() == null){
            em.persist(student);
        }else{
            em.merge(student);
        }

        return student;
    }

    //like a test
    @Transactional
    public void saveStudentWithPassport(){
        Passport pass = new Passport("761PZ126311");
        em.persist(pass);

        Student student = new Student("Tommy");
        student.setPassport(pass);
        em.persist(student);

    }

    @Transactional
    public void someOperationWithPersistenceContext(){ // Start transaction, creating a persistence context ( = hibernate 'session').

        //Persistence Context with all managed entity:

        Student student = em.find(Student.class, 2001L); //Persistence context: student

        // if Passport is LAZY - without transaction we will catch an exception.
        Passport passport = student.getPassport(); //Persistence context: student, passport

        passport.setNumber("ZZZZ00000"); //Persistence context: student, passport++

        student.setName("MariaDB)"); //Persistence context: student++, passport++

    } // End of transaction. Entities was updated.

    @Transactional
    public void insertStudentAndCourse(Student student, Course course){
        em.persist(student);
        em.persist(course);
        student.addCourse(course);
        course.addStudent(student);
    }

}
