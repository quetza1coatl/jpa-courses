package com.quetzalcoatl.jpacourses;

import com.quetzalcoatl.jpacourses.repository.CourseRepository;
import com.quetzalcoatl.jpacourses.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
        studentRepository.saveStudentWithPassport();

	}
}
