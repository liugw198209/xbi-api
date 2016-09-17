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

public class SimilarController extends Controller {
    
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
     * Get the similar courses with pagination
     *
     * @param Integer page
     * @param Integer size
     *
     * @return Result
     */
    @Transactional(readOnly = true)
    public Result list(Long courseId, Integer page, Integer size, Double minSimilarity) {
    	SimilarCourseService.setSimilarityThreshold(minSimilarity);//forDebug
        List models = SimilarCourseService.paginate(courseId, page-1, size);
        Long count = SimilarCourseService.count(courseId);

        ObjectNode result = Json.newObject();
        result.put("data", Json.toJson(models));
        result.put("total", count);
        if (page > 1) result.put("link-prev", routes.CourseController.list(page-1, size).toString());
        if (page*size < count) result.put("link-next", routes.CourseController.list(page+1, size).toString());
        result.put("link-self", routes.SimilarController.list(courseId, page, size, minSimilarity).toString());

        return jsonResult(ok(result));
    }
    
//    /**
//     * Get the similar courses with pagination
//     *
//     * @param Integer page
//     * @param Integer size
//     *
//     * @return Result
//     */
//    @Transactional(readOnly = true)
//    public Result list(Long courseId, Integer page, Integer size) {
//        List models = SimilarCourseService.paginate(courseId, page-1, size);
//        Long count = SimilarCourseService.count(courseId);
//
//        ObjectNode result = Json.newObject();
//        result.put("data", Json.toJson(models));
//        result.put("total", count);
//        if (page > 1) result.put("link-prev", routes.CourseController.list(page-1, size).toString());
//        if (page*size < count) result.put("link-next", routes.CourseController.list(page+1, size).toString());
//        result.put("link-self", routes.SimilarController.list(courseId, page, size).toString());
//
//        return jsonResult(ok(result));
//    }
    
}
