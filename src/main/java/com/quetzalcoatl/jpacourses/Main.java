package com.quetzalcoatl.jpacourses;


public class Main {

    public static void main(String[] args) {
        SimpleRepository repository = new SimpleRepository();
        //create
        repository.create(1, "Paul", 31);
        repository.create(2, "Ralf", 22);
        repository.create(3, "Betty", 37);
        repository.create(4, "Hanna", 21);
        //get all
        repository.getAll().forEach(System.out::println);
        //changes
        repository.update(1, "new_name", 66);
        repository.delete(2);
        //get all
        repository.getAll().forEach(System.out::println);




    }
}
