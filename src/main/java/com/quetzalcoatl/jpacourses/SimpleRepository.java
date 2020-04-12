package com.quetzalcoatl.jpacourses;


import javax.persistence.*;
import java.util.List;

class SimpleRepository {
    private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("hsqldb");

    void create(int id, String name, int age) {

        EntityManager em = FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = em.getTransaction();
            transaction.begin();

            SimpleEntity entity = new SimpleEntity();
            entity.setId(id);

            // Persist takes an entity instance, adds it to the context and makes that instance managed
            // (future updates to the entity before commit will be tracked). After commit, name and age also will be
            // put to the DB. But in this case Hibernate makes 2 queries instead of 1 (insert and then update with name and age).
            // Make sure at least id is set before persist!
            em.persist(entity);

            entity.setName(name);
            entity.setAge(age);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            em.close();
        }


    }

    List<SimpleEntity> getAll() {

        List<SimpleEntity> entities = null;

        EntityManager em = FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = em.getTransaction();
            transaction.begin();

            TypedQuery<SimpleEntity> query = em.createQuery("SELECT e FROM SimpleEntity e", SimpleEntity.class);
            entities = query.getResultList();

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            em.close();
        }


        return entities;
    }

    void delete(int id) {

        EntityManager em = FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = em.getTransaction();
            transaction.begin();

            SimpleEntity entity = em.find(SimpleEntity.class, id);
            em.remove(entity);

            transaction.commit();


        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            em.close();
        }

    }

    void update(int id, String name, int age){

        EntityManager em = FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = em.getTransaction();
            transaction.begin();

            //returns a managed copy from Persistence Context
            SimpleEntity entity = em.find(SimpleEntity.class, id);
            entity.setName(name);
            entity.setAge(age);

            transaction.commit();

        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            em.close();
        }
    }

}
