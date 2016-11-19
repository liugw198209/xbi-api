package controllers;

import java.util.List;

import play.*;
import play.mvc.*;
import play.libs.Json;
import play.libs.Json.*;
import play.data.Form;
import play.db.jpa.*;

import models.*;
import views.html.*;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class CourseLikeController extends Controller {
    static Form<CourseLike> likeForm = Form.form(CourseLike.class);

    /**
     * Add the content-type json to response
     *
     * @param Result httpResponse
     *
     * @return Result
     */
    public Result jsonResult(Result httpResponse) {
        response().setContentType("application/json; charset=utf-8");
        return httpResponse;
    }

    /**
     * Get the index page
     *
     * @return Result
     */
    public Result index() {
        return ok(index.render("API REST for JAVA Play Framework"));
    }

    /**
     * Get the likes with pagination
     *
     * @param Integer page
     * @param Integer size
     *
     * @return Result
     */
    @Transactional(readOnly = true)
    public Result list(Long courseId, Integer page, Integer size) {
        List models = CourseLikeService.paginate(courseId, page-1, size);
        Long count = CourseLikeService.count(courseId);

        ObjectNode result = Json.newObject();
        result.put("data", Json.toJson(models));
        result.put("total", count);
        if (page > 1) result.put("link-prev", routes.CourseLikeController.list(courseId, page-1, size).toString());
        if (page*size < count) result.put("link-next", routes.CourseLikeController.list(courseId, page+1, size).toString());
        result.put("link-self", routes.CourseLikeController.list(courseId, page, size).toString());

        return jsonResult(ok(result));
    }
    
    /**
     * Get the likes with pagination
     *
     * @param Long courseId
     *
     * @return Result
     */
    @Transactional(readOnly = true)
    public Result total(Long courseId) {
        Long likeCount = CourseLikeService.likes(courseId);

        ObjectNode result = Json.newObject();
        result.put("totalLikes", likeCount);

        return jsonResult(ok(result));
    }

    /**
     * Get one CourseLike by id
     *
     * @param Integer id
     *
     * @return Result
     */
    @Transactional(readOnly = true)
    public Result get(Long id) {
    	CourseLike like = CourseLikeService.find(id);
        if (like == null ) {
            ObjectNode result = Json.newObject();
            result.put("error", "Not found " + id);
            return jsonResult(notFound(result));
        }
        return jsonResult(ok(Json.toJson(like)));
    }

    /**
     * Create an CourseLike with the data of request
     *
     * @return Result
     */
    @Transactional
    public Result create() {
    	//Logger.info("create a comment");
        Form<CourseLike> like = likeForm.bindFromRequest();
        if (like.hasErrors()) {
            return jsonResult(badRequest(like.errorsAsJson()));
        }
        CourseLike newLike = CourseLikeService.create(like.get());
        return jsonResult(created(Json.toJson(newLike)));
    }

    /**
     * Update an CourseLike with the data of request
     *
     * @return Result
     */
    @Transactional
    public Result update() {
        Form<CourseLike> like = likeForm.bindFromRequest();
        if (like.hasErrors()) {
            return jsonResult(badRequest(like.errorsAsJson()));
        }
        CourseLike updatedLike = CourseLikeService.update(like.get());
        return jsonResult(ok(Json.toJson(updatedLike)));
    }

    /**
     * Delete an long by CourseLike id
     *
     * @param Long mid
     *
     * @return Result
     */
    @Transactional
    public Result deleteSingle(Long mid) {
        if (CourseLikeService.delete(mid)) {
            ObjectNode result = Json.newObject();
            result.put("msg", "Deleted " + mid);
            return jsonResult(ok(result));
        }
        ObjectNode result = Json.newObject();
        result.put("error", "Not found " + mid);
        return jsonResult(notFound(result));
    }
    
    /**
     * delete all CourseLike w.r.t courseId and userId
     * @param courseId
     * @param userId
     * @return
     */
    @Transactional
    public Result delete(Long courseId, String userId) {
    	if (CourseLikeService.delete(courseId, userId)) {
            ObjectNode result = Json.newObject();
            result.put("msg", "Deleted " + "courseId=" + courseId + ", userId=" + userId);
            return jsonResult(ok(result));
        }
        ObjectNode result = Json.newObject();
        result.put("error", "Not found " + "courseId=" + courseId + ", userId=" + userId);
        return jsonResult(notFound(result));	
    }
}
