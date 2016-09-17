package models;

import play.*;
import play.mvc.*;
import play.db.jpa.*;
import java.util.List;
import java.util.Date;

import javax.persistence.*;

public class CourseDAO {
    
    /**
     * Find an course by id
     *
     * @param Long id
     *
     * @return Course
     */
    public static Course find(Long id) {
        return JPA.em().find(Course.class, id);
    }
    
    /**
     * Get all courses
     *
     * @return List<Course>
     */
    public static List<Course> all() {
        return (List<Course>) JPA.em().createQuery("SELECT m FROM " + Course.TABLE + " m ORDER BY page_id").getResultList();
    }

    /**
     * Get the page of courses
     *
     * @param Integer page
     * @param Integer size
     *
     * @return List<Employee>
     */
    public static List<Course> paginate(Integer page, Integer size) {
        return (List<Course>) JPA.em().createQuery("SELECT m FROM " + Course.TABLE + " m ORDER BY page_id").setFirstResult(page*size).setMaxResults(size).getResultList();
    }

    /**
     * Get the number of total row
     *
     * @return Long
     */
    public static Long count() {
        return (Long) JPA.em().createQuery("SELECT count(m) FROM " + Course.TABLE + " m").getSingleResult();
    }
}