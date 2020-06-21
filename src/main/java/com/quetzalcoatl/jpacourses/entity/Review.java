package com.quetzalcoatl.jpacourses.entity;

import javax.persistence.*;

@Entity
public class Review {
    @Id
    @GeneratedValue
    private long id;

    private String description;

    @Enumerated(EnumType.STRING)
    private ReviewRating rating;

    @ManyToOne
    private Course course;

    protected Review() {
    }


    public Review(String description, ReviewRating rating) {
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

    public ReviewRating getRating() {
        return rating;
    }

    public void setRating(ReviewRating rating) {
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
