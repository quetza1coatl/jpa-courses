package com.quetzalcoatl.jpacourses.repository;

import com.quetzalcoatl.jpacourses.entity.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    CourseRepository repository;

    @Test
    void findById() {
        assertEquals("Bio", repository.findById(1001L).getName());
    }

    @Test
    @DirtiesContext
    void deleteById(){
        repository.deleteById(1001L);
        assertNull(repository.findById(1001L));
    }

    @Test
    @DirtiesContext
    void updateCourse(){
        Course course = repository.findById(1001L);
        course.setName("Bio - updated");
        repository.save(course);

        Course courseUpdated = repository.findById(1001L);
        assertEquals("Bio - updated", courseUpdated.getName());
    }


}