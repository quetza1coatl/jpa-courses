package com.quetzalcoatl.jpacourses.repository.jpa;

import com.quetzalcoatl.jpacourses.entity.Person;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class PersonJpaRepository {

    @PersistenceContext
    EntityManager em;

    public Person findById(int id) {
        return em.find(Person.class, id);
    }

    public Person insert(Person person){
        return em.merge(person);
    }

    public Person update(Person person){
        return em.merge(person);
    }

    public void deleteById(int id){
        Person p = findById(id);
        em.remove(p);
    }

    public List<Person> findAll(){
        TypedQuery<Person> query = em.createNamedQuery("find_all_persons", Person.class);
        return query.getResultList();
    }
}
