package com.quetzalcoatl.jpacourses.repository;

import com.quetzalcoatl.jpacourses.entity.Course;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;


@SpringBootTest
class NativeQueryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    @Test
    void NativeBasic() {
        Query query = em.createNativeQuery("SELECT * FROM course", Course.class);
        List<Course> result = query.getResultList();
        result.forEach( r -> logger.info(r.toString()));
    }

    @Test
    void NativeWhereWithPosParams() {
        Query query = em.createNativeQuery("SELECT * FROM course WHERE id = ?", Course.class);
        query.setParameter(1, 1002L);
        Course result = ( Course) query.getSingleResult();
        logger.info(result.toString());
    }

    @Test
    void NativeWhereWithNamedParams() {
        Query query = em.createNativeQuery("SELECT * FROM course WHERE id = :id", Course.class);
        query.setParameter("id", 1002L);
        Course result = ( Course) query.getSingleResult();
        logger.info(result.toString());
    }

    @Test
    @Transactional
    void updateCreatedTime(){
        Query query = em.createNativeQuery("UPDATE course SET created_time=now()");
        int rowsUpdated = query.executeUpdate();
        logger.info("Was updated {} rows", rowsUpdated);
    }


}
