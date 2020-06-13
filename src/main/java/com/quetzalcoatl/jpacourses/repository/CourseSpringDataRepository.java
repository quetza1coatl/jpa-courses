package com.quetzalcoatl.jpacourses.repository;

import com.quetzalcoatl.jpacourses.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;
import java.util.List;

@RepositoryRestResource(path = "courses")
public interface CourseSpringDataRepository extends JpaRepository<Course, Long> {

    //query by method name
    List<Course> findByName(String name);

    // custom JPQL query
    @Query("SELECT c FROM Course c WHERE c.id=:id")
    Course jpqlGetByIdNamedParam(@Param("id") Long id);

    // custom sql query
    @Query(value = "SELECT * FROM course WHERE course.id=?1", nativeQuery = true)
    Course nativeQueryGetByIdIndexedParam(Long id);

    // custom named query
    @Query(name = Course.GET_ALL)
    List<Course> getAllUsingNamedQuery();

    // using collection parameter
    @Query(value = "SELECT c FROM Course c WHERE c.name IN :names")
    List<Course> findCoursesByNameList(@Param("names") Collection<String> names);

}
