package com.quetzalcoatl.jpacourses.repository;

import com.quetzalcoatl.jpacourses.entity.Course;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseSpringDataRepositoryTest {

    @Autowired
    CourseSpringDataRepository repository;


    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    void findById() {
        Optional<Course> courseOptional = repository.findById(1003L);
        assertTrue(courseOptional.isPresent());
    }

    @Test
    void findByIdCourseNotPresent() {
        Optional<Course> courseOptional = repository.findById(2003L);
        assertFalse(courseOptional.isPresent());
    }

    @Test
    void play(){

        Course hibernate_course = repository.save(new Course("Hibernate course"));
        hibernate_course.setName("Hibernate course - updated");
        repository.save(hibernate_course);

        logger.info("Courses: {}", repository.findAll());
        logger.info("Courses count: {}", repository.count());
        assertEquals(4, repository.count());

    }

    @Test
    void sort(){
        Sort sort2 = Sort.by(Sort.DEFAULT_DIRECTION, "id");
        Sort sort = Sort.by(Sort.Direction.DESC, "name", "createdTime").and(sort2);
        List<Course> all = repository.findAll(sort);
        logger.info("Sorted courses: {}", all);
        assertEquals(1002L, all.get(0).getId());
    }

    @Test
    void pagination(){
        PageRequest pageRequest = PageRequest.of(0, 3);
        Page<Course> firstPage = repository.findAll(pageRequest);
        logger.info("First page: {}", firstPage.getContent());
        Pageable second = firstPage.nextPageable();
        Page<Course> secondPage = repository.findAll(second);
        logger.info("Second page: {}", secondPage.getContent());
    }


    @Test
    void findByName(){
        List<Course> bio = repository.findByName("Bio");
        logger.info("Bio course: {}", bio);
        assertEquals(1, bio.size());
        assertEquals(1001L, bio.get(0).getId());
    }

    @Test
    void nativeCustomQuery(){
        Long id = 1001L;
        Course course = repository.nativeQueryGetByIdIndexedParam(id);
        assertEquals(id, course.getId());
    }

    @Test
    void jpqlCustomQuery(){
        Long id = 1001L;
        Course course = repository.jpqlGetByIdNamedParam(id);
        assertEquals(id, course.getId());
    }


    @Test
    void namedQuery(){
        List<Course> courses = repository.getAllUsingNamedQuery();
        assertEquals(9, courses.size());
    }

    @Test
    void collectionParameter(){
        List<String> names = Arrays.asList("Bio", "Deep learning");
        List<Course> courses = repository.findCoursesByNameList(names);
        logger.info("Courses by names: {}", courses);
        assertEquals(2, courses.size());
    }




}