package com.quetzalcoatl.jpacourses.jdbc;

import com.quetzalcoatl.jpacourses.entity.Person;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonJDBCDao {

    private JdbcTemplate template;

    public PersonJDBCDao(JdbcTemplate template) {
        this.template = template;
    }

    public List<Person> getAll(){
        return template.query("SELECT * FROM person", new BeanPropertyRowMapper(Person.class));

    }
}
