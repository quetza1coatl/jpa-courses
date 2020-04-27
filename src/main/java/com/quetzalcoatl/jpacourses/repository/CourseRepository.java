package com.quetzalcoatl.jpacourses.repository;

import com.quetzalcoatl.jpacourses.entity.Course;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CourseRepository {

    @PersistenceContext
    private EntityManager em;

    public Course findById(Long id){
        return em.find(Course.class, id);
    }

    @Transactional
    public void deleteById(Long id){
        Course c = findById(id);
        em.remove(c);
    }

    @Transactional
    public Course save(Course course){
        if(course.getId() == null){
            em.persist(course);
        }else{
            em.merge(course);
        }

        return course;
    }
}
