package com.quetzalcoatl.jpacourses.entity;

import javax.persistence.*;

@Entity
public class Passport {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String number;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "passport")
    private Student student;

    protected Passport() {
    }


    public Passport(String number) {
        this.number = number;
    }


    public long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Passport{" +
                "id=" + id +
                ", number='" + number + '\'' +
                '}';
    }
}
