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

public class CommentController extends Controller {
    static Form<Comment> commentForm = Form.form(Comment.class);

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
     * Get the comments with pagination
     *
     * @param Integer page
     * @param Integer size
     *
     * @return Result
     */
    @Transactional(readOnly = true)
    public Result list(Long courseId, Integer page, Integer size) {
        List models = CommentService.paginate(courseId, page-1, size);
        Long count = CommentService.count(courseId);

        ObjectNode result = Json.newObject();
        result.put("data", Json.toJson(models));
        result.put("total", count);
        if (page > 1) result.put("link-prev", routes.CommentController.list(courseId, page-1, size).toString());
        if (page*size < count) result.put("link-next", routes.CommentController.list(courseId, page+1, size).toString());
        result.put("link-self", routes.CommentController.list(courseId, page, size).toString());

        return jsonResult(ok(result));
    }

    /**
     * Get one comment by id
     *
     * @param Integer id
     *
     * @return Result
     */
    @Transactional(readOnly = true)
    public Result get(Long id) {
        Comment comment = CommentService.find(id);
        if (comment == null ) {
            ObjectNode result = Json.newObject();
            result.put("error", "Not found " + id);
            return jsonResult(notFound(result));
        }
        return jsonResult(ok(Json.toJson(comment)));
    }

    /**
     * Create an comment with the data of request
     *
     * @return Result
     */
    @Transactional
    public Result create() {
    	//Logger.info("create a comment");
        Form<Comment> comment = commentForm.bindFromRequest();
        if (comment.hasErrors()) {
            return jsonResult(badRequest(comment.errorsAsJson()));
        }
        Comment newComment = CommentService.create(comment.get());
        return jsonResult(created(Json.toJson(newComment)));
    }

    /**
     * Update an employee with the data of request
     *
     * @return Result
     */
    @Transactional
    public Result update() {
        Form<Comment> comment = commentForm.bindFromRequest();
        if (comment.hasErrors()) {
            return jsonResult(badRequest(comment.errorsAsJson()));
        }
        Comment updatedComment = CommentService.update(comment.get());
        return jsonResult(ok(Json.toJson(updatedComment)));
    }

    /**
     * Delete an long by comment id
     *
     * @param Long mid
     *
     * @return Result
     */
    @Transactional
    public Result deleteSingle(Long mid) {
        if (CommentService.delete(mid)) {
            ObjectNode result = Json.newObject();
            result.put("msg", "Deleted " + mid);
            return jsonResult(ok(result));
        }
        ObjectNode result = Json.newObject();
        result.put("error", "Not found " + mid);
        return jsonResult(notFound(result));
    }
    
    /**
     * delete all comments w.r.t courseId and userId
     * @param courseId
     * @param userId
     * @return
     */
    @Transactional
    public Result delete(Long courseId, Long userId) {
    	if (CommentService.delete(courseId, userId)) {
            ObjectNode result = Json.newObject();
            result.put("msg", "Deleted " + "courseId=" + courseId + ", userId=" + userId);
            return jsonResult(ok(result));
        }
        ObjectNode result = Json.newObject();
        result.put("error", "Not found " + "courseId=" + courseId + ", userId=" + userId);
        return jsonResult(notFound(result));	
    }
}
