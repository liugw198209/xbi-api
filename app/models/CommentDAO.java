package models;

import play.*;
import play.mvc.*;
import play.db.jpa.*;

import java.util.List;
import java.util.Date;
import java.util.Map;

import javax.persistence.*;

public class CommentDAO {
    /**
     * Create an Comment
     *
     * @param Comment model
     *
     * @return Comment
     */
    public static Comment create (Comment model) {
        model.emptyToNull();
        JPA.em().persist(model);
        // Flush and refresh for check
        JPA.em().flush();
        JPA.em().refresh(model);
        return model;
    }

    /**
     * Find an comment by id
     *
     * @param Integer id
     *
     * @return Comment
     */
    public static Comment find(Long mid) {
        return JPA.em().find(Comment.class, mid);
    }

    /**
     * return comments by specified courseId and userId
     * @param courseId
     * @param userId
     * @return
     */
    public static List<Comment> find(Long courseId, String userId){
    	return (List<Comment>) JPA.em().createQuery("SELECT m FROM " + Comment.TABLE + " m where course_id=" + courseId + " and user_id='" + userId + "' ORDER BY comment_date desc").getResultList();
    }

    /**
     * Update an Comment
     *
     * @param Comment model
     *
     * @return Comment
     */
    public static Comment update(Comment model) {
    	//Logger.info("id="+model.id+",course="+model.courseId+", comment="+model.comment+", userid="+model.userId+", username="+model.userName);
        return JPA.em().merge(model);
    }

    /**
     * Delete an comment by id
     *
     * @param Integer id
     */
    public static void delete(Long id) {
        Comment model = JPA.em().getReference(Comment.class, id);
        JPA.em().remove(model);
    }

    /**
     * Get all comments
     *
     * @return List<Comment>
     */
    public static List<Comment> all(Long courseId) {
        return (List<Comment>) JPA.em().createQuery("SELECT m FROM " + Comment.TABLE + " m where course_id=" + courseId + " ORDER BY comment_date").getResultList();
    }

    /**
     * Get the page of comments
     *
     * @param Integer page
     * @param Integer size
     *
     * @return List<Comment>
     */
    public static List<Comment> paginate(Long courseId, Integer page, Integer size) {
        return (List<Comment>) JPA.em().createQuery("SELECT m FROM " + Comment.TABLE + " m where course_id=" + courseId + " ORDER BY comment_date").setFirstResult(page*size).setMaxResults(size).getResultList();
    }

    /**
     * Get the number of total row
     *
     * @return Long
     */
    public static Long count(Long courseId) {
        return (Long) JPA.em().createQuery("SELECT count(m) FROM " + Comment.TABLE + " m where course_id=" + courseId).getSingleResult();
    }
    
    /**
     * Get average rating of a course
     *
     * @return Object[]
     */
    public static Object[] rating(Long courseId) {
    	TypedQuery<Object[]> q = JPA.em().createQuery("SELECT avg(rating), count(m) FROM " + Comment.TABLE + " m where course_id=" + courseId, Object[].class);
    	return q.getSingleResult();
    }
}