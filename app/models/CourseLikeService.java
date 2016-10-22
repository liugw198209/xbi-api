package models;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class CourseLikeService {
    /**
     * Create an CourseLike
     *
     * @param CourseLike data
     *
     * @return CourseLike
     */
    public static CourseLike create(CourseLike data) {
    	data.likeDate = new Timestamp(new Date().getTime());
        return CourseLikeDAO.create(data);
    }

    /**
     * Update a CourseLike
     *
     * @param CourseLike data
     *
     * @return CourseLike
     */
    public static CourseLike update(CourseLike data) {
    	data.likeDate = new Timestamp(new Date().getTime());
        return CourseLikeDAO.update(data);
    }

    /**
     * Find an CourseLike by id
     *
     * @param Long id
     *
     * @return CourseLike
     */
    public static CourseLike find(Long id) {
        return CourseLikeDAO.find(id);
    }

    /**
     * Delete an CourseLike by id
     *
     * @param Integer id
     */
    public static Boolean delete(Long mid) {
    	CourseLike like = CourseLikeDAO.find(mid);
        if (like != null) {
        	CourseLikeDAO.delete(like.id);
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * delete all CourseLike w.r.t courseId and userId
     * @param courseId
     * @param userId
     * @return
     */
    public static Boolean delete(Long courseId, Long userId){
    	List<CourseLike> likes = CourseLikeDAO.find(courseId, userId);
        if (likes != null && likes.size() > 0) {
        	likes.forEach(like -> {CourseLikeDAO.delete(like.id);});
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Get the number of total of CourseLike
     *
     * @return Long
     */
    public static Long likes(Long courseId) {
        return CourseLikeDAO.total(courseId);
    }

    /**
     * Get all CourseLike
     *
     * @return List<CourseLike>
     */
    public static List<CourseLike> all(Long courseId) {
        return CourseLikeDAO.all(courseId);
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
        return CourseLikeDAO.paginate(courseId, page, size);
    }

    /**
     * Get the number of total of CourseLike
     *
     * @return Long
     */
    public static Long count(Long courseId) {
        return CourseLikeDAO.count(courseId);
    }
}