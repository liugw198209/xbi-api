package models;

import play.db.jpa.*;

import java.util.List;

public class CourseLikeDAO {
    /**
     * Create a like action
     *
     * @param CourseLike model
     *
     * @return CourseLike
     */
    public static CourseLike create (CourseLike model) {
        model.emptyToNull();
        JPA.em().persist(model);
        // Flush and refresh for check
        JPA.em().flush();
        JPA.em().refresh(model);
        return model;
    }

    /**
     * Find an CourseLike by id
     *
     * @param Integer id
     *
     * @return CourseLike
     */
    public static CourseLike find(Long mid) {
        return JPA.em().find(CourseLike.class, mid);
    }

    /**
     * return likes by specified courseId and userId
     * @param courseId
     * @param userId
     * @return
     */
    public static List<CourseLike> find(Long courseId, Long userId){
    	return (List<CourseLike>) JPA.em().createQuery("SELECT m FROM " + CourseLike.TABLE + " m where course_id=" + courseId + " and user_id=" + userId + " ORDER BY like_date desc").getResultList();
    }

    /**
     * Update an CourseLike
     *
     * @param CourseLike model
     *
     * @return CourseLike
     */
    public static CourseLike update(CourseLike model) {
        return JPA.em().merge(model);
    }

    /**
     * Delete an CourseLike by id
     *
     * @param Integer id
     */
    public static void delete(Long id) {
    	CourseLike model = JPA.em().getReference(CourseLike.class, id);
        JPA.em().remove(model);
    }

    /**
     * Get the total number of all CourseLike
     *
     * @return Long
     */
    public static Long total(Long courseId) {
        return (Long) JPA.em().createQuery("SELECT count(m) FROM " + CourseLike.TABLE + " m where course_id=" + courseId + " and like_course > 0").getSingleResult();
    }
    
    /**
     * Get all CourseLike
     *
     * @return List<CourseLike>
     */
    public static List<CourseLike> all(Long courseId) {
        return (List<CourseLike>) JPA.em().createQuery("SELECT m FROM " + CourseLike.TABLE + " m where course_id=" + courseId + " ORDER BY like_date").getResultList();
    }

    /**
     * Get the page of CourseLike
     *
     * @param Integer page
     * @param Integer size
     *
     * @return List<CourseLike>
     */
    public static List<CourseLike> paginate(Long courseId, Integer page, Integer size) {
        return (List<CourseLike>) JPA.em().createQuery("SELECT m FROM " + CourseLike.TABLE + " m where course_id=" + courseId + " ORDER BY like_date").setFirstResult(page*size).setMaxResults(size).getResultList();
    }

    /**
     * Get the number of total row
     *
     * @return Long
     */
    public static Long count(Long courseId) {
        return (Long) JPA.em().createQuery("SELECT count(m) FROM " + CourseLike.TABLE + " m where course_id=" + courseId).getSingleResult();
    }
}