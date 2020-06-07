package com.quetzalcoatl.jpacourses;

import com.quetzalcoatl.jpacourses.entity.*;
import com.quetzalcoatl.jpacourses.repository.CourseRepository;
import com.quetzalcoatl.jpacourses.repository.EmployeeRepository;
import com.quetzalcoatl.jpacourses.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class JpaCoursesApplication implements CommandLineRunner {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

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

        Student student = new Student("Student_1");
        Course course = new Course("Microservices");
        studentRepository.insertStudentAndCourse(student, course);

        employeeRepository.insert(new FullTimeEmployee("Mike", new BigDecimal(10000)));
        employeeRepository.insert(new PartTimeEmployee("Jane", new BigDecimal(50)));
//        logger.info("Employees: {}", employeeRepository.getAll());
        logger.info("Full time Employees: {}", employeeRepository.getAllFullTimeEmployee());
        logger.info("Part time Employees: {}", employeeRepository.getAllPartTimeEmployee());

	}
}
