package com.quetzalcoatl.jpacourses.jdbc;

import com.quetzalcoatl.jpacourses.entity.Person;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class PersonJDBCDao {

    private JdbcTemplate template;
    private static final RowMapper<Person> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Person.class);

    public PersonJDBCDao(JdbcTemplate template) {
        this.template = template;
    }

    // Custom RowMapper. For example, if column names in the table and entity fields are different.
    private static final RowMapper<Person> CUSTOM_ROW_MAPPER = (resultSet, rowNum) -> {
        Person p = new Person();
        p.setId(resultSet.getInt("id"));
        p.setName(resultSet.getString("name"));
        p.setLocation(resultSet.getString("planet_location"));
        p.setBirthDate(resultSet.getTimestamp("birth_date"));
        return p;
    };


    public List<Person> getAll(){
        return template.query("SELECT * FROM person", ROW_MAPPER);
    }

    // special method to get object, 2nd param - arguments for query.
    public Person findById(int id){
        return template.queryForObject("SELECT * FROM person WHERE id=?", new Object[]{id}, ROW_MAPPER);
    }

    public List<Person> getAllByName(String name){
        return template.query("SELECT * FROM person WHERE name=?", new Object[]{name}, ROW_MAPPER);
    }

    // use 'update' to delete. Arguments can be passed as var arg, without array creation.
    public boolean deleteById(int id){
        return template.update("DELETE FROM person WHERE id=?", id) != 0;
    }

    public boolean deleteByIdOrName(int id, String name){
        return template.update("DELETE FROM person WHERE id=? OR name =?", id, name) != 0;
    }

    public boolean insert(Person p){
        return template.update(
                "INSERT INTO" +
                        " PERSON(id, name, location, birth_date)" +
                        " VALUES(?,?,?,?)",
                p.getId(), p.getName(), p.getLocation(), new Timestamp(p.getBirthDate().getTime())
        ) != 0;
    }

    public boolean update(Person p){
        return template.update(
                "UPDATE PERSON " +
                        "SET name=?, location=?, birth_date=? " +
                        "WHERE id=?",
                p.getName(), p.getLocation(), new Timestamp(p.getBirthDate().getTime()), p.getId()
                ) != 0;
    }
}
