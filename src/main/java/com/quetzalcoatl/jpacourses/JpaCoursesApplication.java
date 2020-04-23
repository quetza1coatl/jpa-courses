package com.quetzalcoatl.jpacourses;

import com.quetzalcoatl.jpacourses.entity.Person;
import com.quetzalcoatl.jpacourses.repository.jpa.PersonJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Date;

@SpringBootApplication
public class JpaCoursesApplication implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(JpaCoursesApplication.class);
	private final PersonJpaRepository repository;

    public JpaCoursesApplication(PersonJpaRepository repository) {
        this.repository = repository;
    }


    public static void main(String[] args) {
		SpringApplication.run(JpaCoursesApplication.class, args);
	}

	@Override
	public void run(String... args){
        logger.info("********GET ALL USERS********");
        repository.findAll().forEach(p -> logger.info(p.toString()));

        int id = 1001;
        logger.info("********GET USER WITH ID = {}********", id);
        logger.info("User: {}",repository.findById(id));

        logger.info("********DELETE USER WITH ID = {}********", id);
        repository.deleteById(id);
        logger.info("User with id = {} was deleted", id);

        logger.info("********CREATE NEW USER********");
        logger.info("A new user was created = {}",
                repository.insert(new Person("Matthew", "Deimos", new Date())));

        int updId = 1003;
        logger.info("********UPDATE USER WITH ID = {}********", updId);
        logger.info("User with id {} was updated = {}", updId, repository.update(new Person(updId, "Charlie", "Venus", new Date())));

	}
}
