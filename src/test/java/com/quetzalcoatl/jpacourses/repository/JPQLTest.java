package com.quetzalcoatl.jpacourses.repository;

import com.quetzalcoatl.jpacourses.entity.Course;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;


@SpringBootTest
class JPQLTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    @Test
    void JpqlBasic() {
        Query query = em.createQuery("SELECT c FROM Course c");
        List result = query.getResultList();
        result.forEach( r -> logger.info(r.toString()));
    }

    @Test
    void JpqlNamedBasic() {
        Query query = em.createNamedQuery(Course.GET_ALL);
        List result = query.getResultList();
        result.forEach( r -> logger.info(r.toString()));
    }

    @Test
    void JpqlTyped() {
        TypedQuery<Course> query = em.createQuery("SELECT c FROM Course c", Course.class);
        List<Course> result = query.getResultList();
        result.forEach( r -> logger.info(r.toString()));
    }

    @Test
    void JpqlNamedTyped() {
        TypedQuery<Course> query = em.createNamedQuery(Course.GET_ALL, Course.class);
        List<Course> result = query.getResultList();
        result.forEach( r -> logger.info(r.toString()));
    }

    @Test
    void JpqlWhere() {
        TypedQuery<Course> query = em.createQuery("SELECT c FROM Course c WHERE c.name like '%Upd%'", Course.class);
        List<Course> result = query.getResultList();
        result.forEach( r -> logger.info(r.toString()));
    }



}