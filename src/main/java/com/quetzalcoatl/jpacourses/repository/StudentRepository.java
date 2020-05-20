package com.quetzalcoatl.jpacourses.repository;

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

}
