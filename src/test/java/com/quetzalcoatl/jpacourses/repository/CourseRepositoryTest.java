package com.quetzalcoatl.jpacourses.repository;

import com.quetzalcoatl.jpacourses.entity.Course;
import com.quetzalcoatl.jpacourses.entity.Review;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    CourseRepository repository;

    @Autowired
    EntityManager em;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

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

    @Test
    @Transactional
    void retrieveReviewsForCourse(){
        Course course = repository.findById(1003L);
        logger.info("{}", course.getReviews());
    }

    @Test
    void retrieveCourseForReview(){
        Review review = em.find(Review.class, 4003L);
        logger.info("{}", review.getCourse());
    }

}