package com.quetzalcoatl.jpacourses;

import com.quetzalcoatl.jpacourses.entity.Review;
import com.quetzalcoatl.jpacourses.repository.CourseRepository;
import com.quetzalcoatl.jpacourses.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class JpaCoursesApplication implements CommandLineRunner {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentRepository studentRepository;

    public static void main(String[] args) {
		SpringApplication.run(JpaCoursesApplication.class, args);
	}

	@Override
	public void run(String... args){
//    	courseRepository.play();
//        studentRepository.saveStudentWithPassport();
        List<Review> reviews = new ArrayList<>();
        reviews.add(new Review("Not bad, but...", "4.4"));
        reviews.add(new Review("What a perfect english!!!", "4.7"));
        courseRepository.addReviewsForCourse(1003L, reviews);

	}
}
