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

public class CourseController extends Controller {
    static Form<Course> courseForm = Form.form(Course.class);

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
     * Get the courses with pagination
     *
     * @param Integer page
     * @param Integer size
     *
     * @return Result
     */
    @Transactional(readOnly = true)
    public Result list(Integer page, Integer size) {
        List models = CourseService.paginate(page-1, size);
        Long count = CourseService.count();

        ObjectNode result = Json.newObject();
        result.put("data", Json.toJson(models));
        result.put("total", count);
        if (page > 1) result.put("link-prev", routes.CourseController.list(page-1, size).toString());
        if (page*size < count) result.put("link-next", routes.CourseController.list(page+1, size).toString());
        result.put("link-self", routes.CourseController.list(page, size).toString());

        return jsonResult(ok(result));
    }

    /**
     * Get one course by id
     *
     * @param Long id
     *
     * @return Result
     */
    @Transactional(readOnly = true)
    public Result get(Long id) {
        Course course = CourseService.find(id);
        if (course == null ) {
            ObjectNode result = Json.newObject();
            result.put("error", "Not found " + id);
            return jsonResult(notFound(result));
        }
        return jsonResult(ok(Json.toJson(course)));
    }
}
