package com.quetzalcoatl.jpacourses;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpaCoursesApplication implements CommandLineRunner {

    public static void main(String[] args) {
		SpringApplication.run(JpaCoursesApplication.class, args);
	}

	@Override
	public void run(String... args){
        // TODO stub
	}
}
