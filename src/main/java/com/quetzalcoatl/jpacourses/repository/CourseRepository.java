package com.quetzalcoatl.jpacourses.repository;

import com.quetzalcoatl.jpacourses.entity.Course;
import com.quetzalcoatl.jpacourses.entity.Review;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CourseRepository {

    @PersistenceContext
    private EntityManager em;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public Course findById(Long id) {
        return em.find(Course.class, id);
    }

    @Transactional
    public void deleteById(Long id) {
        Course c = findById(id);
        em.remove(c);
    }

    @Transactional
    public Course save(Course course) {
        if (course.getId() == null) {
            em.persist(course);
        } else {
            em.merge(course);
        }

        return course;
    }

    @Transactional
    public void play() {

        /* changes after persist */
        Course c1 = new Course("New course_1");
        em.persist(c1);
        c1.setName("Updated course_1"); // new data will be saved to DB after transaction commit


        /* merge */
        Course c2 = new Course("New course_2");
        Course c2_copy = em.merge(c2); // returns managed copy. Original isn't managed.
        c2.setName("Updated course_2"); // won't be saved to DB
        c2_copy.setName("Updated copy of course_2"); // will be saved to DB after commit


        /* flush, detach, clear */
        Course c3 = new Course("New course_3");
        Course c4 = new Course("New course_4");
        em.persist(c3);
        em.persist(c4);
        // Synchronize the persistence context to the DB.
        em.flush();
        // remove c3 entity from persistence context. All changes after flush operation won't be tracked.
        em.detach(c3);
//        em.clear(); // clear persistence context
        c3.setName("Updated course_3"); // won't be saved to DB
        c4.setName("Updated course_4"); // will be saved to DB.


        /* refresh */
        Course c5 = new Course("New course_5");
        em.persist(c5);
        c5.setName("Updated course_5");
        em.flush();
        c5.setName("New value after flush for course_5");
        // after this operation c5.name will be "Updated course_5" again
        em.refresh(c5);
    }

    @Transactional
    public void addReviewsForCourse(Long courseId, List<Review> reviews) {

        Course course = findById(courseId);
        logger.info("{}", course.getReviews());
        reviews.forEach( review -> {
            course.addReview(review);
            review.setCourse(course);
            em.persist(review);
        });
    }
}
