package com.quetzalcoatl.jpacourses.repository;

import com.quetzalcoatl.jpacourses.entity.Course;
import com.quetzalcoatl.jpacourses.entity.Student;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

import static org.junit.Assert.assertEquals;


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
    @Transactional
    void JpqlWhere() {
        TypedQuery<Course> query = em.createQuery("SELECT c FROM Course c WHERE c.name like '%Upd%'", Course.class);
        List<Course> result = query.getResultList();
        result.forEach( r -> logger.info(r.toString()));
    }

    @Test
    void selectCoursesWithoutStudent(){
        TypedQuery<Course> query = em.createQuery("SELECT c FROM Course c WHERE c.students IS EMPTY", Course.class);
        List<Course> courses = query.getResultList();
        assertEquals(1, courses.size());
        assertEquals(1003L, (long) courses.get(0).getId());
        logger.info("Courses: {}", courses);
    }

    @Test
    void selectCoursesWith2AndMoreStudents(){
        TypedQuery<Course> query = em.createQuery("SELECT c FROM Course c WHERE size(c.students) >= 2", Course.class);
        List<Course> courses = query.getResultList();
        assertEquals(1, courses.size());
        assertEquals(1001L, (long) courses.get(0).getId());
        logger.info("Courses: {}", courses);
    }

    @Test
    void selectCoursesOrderByNumberOfStudents(){
        TypedQuery<Course> query = em.createQuery("SELECT c FROM Course c ORDER BY size(c.students) DESC", Course.class);
        List<Course> courses = query.getResultList();
        assertEquals(3, courses.size());
        assertEquals(1001L, (long)courses.get(0).getId());
        logger.info("Courses: {}", courses);
    }

    @Test
    void selectStudentsWithPassportNumberLike(){
        TypedQuery<Student> query = em.createQuery("SELECT s FROM Student s WHERE s.passport.number LIKE '%12%'", Student.class);
        List<Student> students = query.getResultList();
        assertEquals(2, students.size());
          logger.info("Students: {}", students);
    }

    @Test
    @Transactional
    void join(){
        Query query = em.createQuery("SELECT c, s FROM Course c JOIN c.students s");
        List<Object[]> list = query.getResultList();
        logger.info("List size - {}", list.size());
        for(Object[] result : list){
            Course c = (Course)result[0];
            Student s = (Student) result[1];
            logger.info("Course:  {}", c);
            logger.info("Student  {} with Passport {}", s, s.getPassport());
        }
    }

    @Test
    void leftJoin(){
        Query query = em.createQuery("SELECT c, s FROM Course c LEFT JOIN c.students s");
        List<Object[]> list = query.getResultList();
        logger.info("List size - {}", list.size());
        for(Object[] result : list){
            logger.info("Course: {} Student: {}", result[0], result[1]);
        }
    }


    @Test
    void crossJoin(){
        Query query = em.createQuery("SELECT c, s FROM Course c, Student s");
        List<Object[]> list = query.getResultList();
        logger.info("List size - {}", list.size());
        for(Object[] result : list){
            logger.info("Course: {} Student: {}", result[0], result[1]);
        }
    }



}