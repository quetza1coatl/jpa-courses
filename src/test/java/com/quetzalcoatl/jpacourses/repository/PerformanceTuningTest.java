package com.quetzalcoatl.jpacourses.repository;

import com.quetzalcoatl.jpacourses.entity.Course;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;


@SpringBootTest
class PerformanceTuningTest {

    @Autowired
    EntityManager em;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    @Transactional
    void creatingNPlusOneProblem() {
        TypedQuery<Course> query = em.createNamedQuery(Course.GET_ALL, Course.class);
        List<Course> courses = query.getResultList();
        courses.forEach( course -> logger.info("Course -> {} Students -> {}", course, course.getStudents()));
    }

    @Test
    @Transactional
    void solvingNPlusOneProblem_EntityGraph() {
        EntityGraph<Course> entityGraph = em.createEntityGraph(Course.class);
        entityGraph.addSubgraph("students");

        TypedQuery<Course> query = em.createNamedQuery(Course.GET_ALL, Course.class);
        List<Course> courses = query
                .setHint("javax.persistence.loadgraph", entityGraph)
                .getResultList();
        courses.forEach( course -> logger.info("Course -> {} Students -> {}", course, course.getStudents()));
    }

    @Test
    @Transactional
    void solvingNPlusOneProblem_JoinFetch() {
        TypedQuery<Course> query = em.createNamedQuery(Course.GET_ALL_WITH_JOINED_STUDENTS, Course.class);
        List<Course> courses = query.getResultList();
        courses.forEach( course -> logger.info("Course -> {} Students -> {}", course, course.getStudents()));
    }
}