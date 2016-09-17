package models;

import play.*;
import play.mvc.*;
import play.db.jpa.*;
import java.util.List;

public class SimilarCourseService {
    
	public static void setSimilarityThreshold(Double minSimilarity){
		SimilarCourseDAO.setThreshold(minSimilarity);
	}
	
	public static double getSimilarityThreshold(){
		return SimilarCourseDAO.getThreshold();
	}
	
    /**
     * Get all similar courses related to the given course id
     *
     * @param Long courseId
     * @return List<SimilarCourse>
     */
    public static List<SimilarCourse> all(Long courseId) {
        return SimilarCourseDAO.all(courseId);
    }

    /**
     * Get the page of courses
     *
     * @param Integer page
     * @param Integer size
     *
     * @return List<SimilarCourse>
     */
    public static List<SimilarCourse> paginate(Long courseId, Integer page, Integer size) {
        return SimilarCourseDAO.paginate(courseId, page, size);
    }

    /**
     * Get the number of total of courses
     *
     * @return Long
     */
    public static Long count(Long courseId) {
        return SimilarCourseDAO.count(courseId);
    }
}