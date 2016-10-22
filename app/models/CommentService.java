package models;

import play.*;
import play.mvc.*;
import play.db.jpa.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class CommentService {
    /**
     * Create an comment
     *
     * @param Comment data
     *
     * @return Comment
     */
    public static Comment create(Comment data) {
    	data.commentDate = new Timestamp(new Date().getTime());
        return CommentDAO.create(data);
    }

    /**
     * Update an comment
     *
     * @param Comment data
     *
     * @return Comment
     */
    public static Comment update(Comment data) {
    	data.commentDate = new Timestamp(new Date().getTime());
        return CommentDAO.update(data);
    }

    /**
     * Find an comment by id
     *
     * @param Long id
     *
     * @return Comment
     */
    public static Comment find(Long id) {
        return CommentDAO.find(id);
    }

    /**
     * Delete an comment by id
     *
     * @param Integer id
     */
    public static Boolean delete(Long mid) {
        Comment comment = CommentDAO.find(mid);
        if (comment != null) {
            CommentDAO.delete(comment.id);
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * delete all comments w.r.t courseId and userId
     * @param courseId
     * @param userId
     * @return
     */
    public static Boolean delete(Long courseId, Long userId){
    	List<Comment> comments = CommentDAO.find(courseId, userId);
        if (comments != null && comments.size() > 0) {
        	comments.forEach(comment -> {CommentDAO.delete(comment.id);});
            return true;
        } else {
            return false;
        }
    }

    /**
     * Get all comments
     *
     * @return List<Comment>
     */
    public static List<Comment> all(Long courseId) {
        return CommentDAO.all(courseId);
    }

    /**
     * Get the page of Comments
     *
     * @param Integer page
     * @param Integer size
     *
     * @return List<Comment>
     */
    public static List<Comment> paginate(Long courseId, Integer page, Integer size) {
        return CommentDAO.paginate(courseId, page, size);
    }

    /**
     * Get the number of total of comments
     *
     * @return Long
     */
    public static Long count(Long courseId) {
        return CommentDAO.count(courseId);
    }
    
    /**
     * Get average rating of a course
     *
     * @return Object[]
     */
    public static Object[] rating(Long courseId) {
        Object[] rlt = CommentDAO.rating(courseId);
        return rlt;
    }
}