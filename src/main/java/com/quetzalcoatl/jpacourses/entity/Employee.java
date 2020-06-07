package com.quetzalcoatl.jpacourses.entity;

import javax.persistence.*;

//default strategy
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//custom column name for type (DTYPE - by default)
//@DiscriminatorColumn(name = "EmployeeType")

//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

//@Inheritance(strategy = InheritanceType.JOINED)

@MappedSuperclass
//@Entity
public abstract class Employee {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;


    protected Employee() {
    }


    public Employee(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
