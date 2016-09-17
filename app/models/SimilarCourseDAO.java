package models;

import play.db.jpa.*;

import java.util.List;

import javax.persistence.*;

public class SimilarCourseDAO {
    
	private final static double MIN_SIMILARITY = 0.3;
	
	private static double thresholdSimilarity = MIN_SIMILARITY;
	
    /**
     * Get all similar courses
     *
     * @return List<SimilarCourse>
     */
    @SuppressWarnings("unchecked")
	public static List<SimilarCourse> all(Long courseId) {
        Query q = JPA.em().createNamedQuery("findSimilarCourses")
        			.setParameter("givenCourseId", courseId)
        			.setParameter("minSimilarity", thresholdSimilarity);
        
        return (List<SimilarCourse>) q.getResultList();
    }

    /**
     * Get the page of courses
     *
     * @param Integer page
     * @param Integer size
     *
     * @return List<Employee>
     */
    @SuppressWarnings("unchecked")
	public static List<SimilarCourse> paginate(Long courseId, Integer page, Integer size) {
    	Query q = JPA.em().createNamedQuery("findSimilarCourses")
    			.setParameter("givenCourseId", courseId)
    			.setParameter("minSimilarity", thresholdSimilarity)
    			.setFirstResult(page*size)
    			.setMaxResults(size);
    	
    	return (List<SimilarCourse>) q.getResultList();
    }

    /**
     * Get the number of total row
     *
     * @return Long
     */
    public static Long count(Long courseId) {
    	Query q = JPA.em().createNamedQuery("findSimilarCourses.count")
    			.setParameter("givenCourseId", courseId)
    			.setParameter("minSimilarity", thresholdSimilarity);

    	return (Long) q.getSingleResult();
    }

	public static double getThreshold() {
		return thresholdSimilarity;
	}

	public static void setThreshold(double threshold) {
		thresholdSimilarity = threshold;
	}
}