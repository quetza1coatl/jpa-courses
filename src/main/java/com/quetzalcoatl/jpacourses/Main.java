package com.quetzalcoatl.jpacourses;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("hsqldb");

    public static void main(String[] args) {
        System.out.println(FACTORY.isOpen());
    }
}
