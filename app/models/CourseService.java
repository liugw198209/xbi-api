package models;

import play.*;
import play.mvc.*;
import play.db.jpa.*;
import java.util.List;

public class CourseService {
    
    /**
     * Find an course by id
     *
     * @param Long id
     *
     * @return Course
     */
    public static Course find(Long id) {
        return CourseDAO.find(id);
    }

    /**
     * Get all courses
     *
     * @return List<Course>
     */
    public static List<Course> all() {
        return CourseDAO.all();
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
        return CourseDAO.paginate(page, size);
    }

    /**
     * Get the number of total of courses
     *
     * @return Long
     */
    public static Long count() {
        return CourseDAO.count();
    }
}