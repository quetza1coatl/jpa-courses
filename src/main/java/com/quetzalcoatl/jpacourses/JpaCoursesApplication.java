package com.quetzalcoatl.jpacourses;

import com.quetzalcoatl.jpacourses.jdbc.PersonJDBCDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpaCoursesApplication implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(JpaCoursesApplication.class);
	private final PersonJDBCDao repository;

    public JpaCoursesApplication(PersonJDBCDao repository) {
        this.repository = repository;
    }


    public static void main(String[] args) {
		SpringApplication.run(JpaCoursesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
       repository.getAll().forEach(p -> logger.info(p.toString()));
	}
}
