package com.quetzalcoatl.jpacourses.entity;

import javax.persistence.*;

@Entity
public class Review {
    @Id
    @GeneratedValue
    private long id;

    private String description;

    private String rating;

    @ManyToOne
    private Course course;

    protected Review() {
    }


    public Review(String description, String rating) {
        this.description = description;
        this.rating = rating;
    }


    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", rating='" + rating + '\'' +
                '}';
    }
}
