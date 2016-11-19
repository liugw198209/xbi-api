package models;

import javax.persistence.*;

import play.api.Logger;
import play.data.validation.Constraints;
import play.data.format.*;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name="le_scourse_comment")
public class Comment {
    public static String TABLE = Comment.class.getSimpleName();

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Long id;

    @Constraints.Required
    @Column(name = "course_id")
    public Long courseId;
    
    @Constraints.Required
    @Column(name = "user_id")
    public String userId;
    
    @Column(name = "user_name")
    public String userName;
    
    @Constraints.Required
    @Column(name = "comment")
    public String comment;
    
    @Constraints.Required
    @Column(name = "rating")
    public Float rating;
    
    @Column(name = "comment_date")
    public Date commentDate;
    
    public Comment() {
    }
    public Comment(Long courseId, String comment, Float rating) { 
    	this.courseId = courseId;
    	this.comment = comment;
    	this.rating = rating;
    	this.commentDate = new Timestamp(new Date().getTime());
    }

    /**
     * Set all empty values to null
     */
    public void emptyToNull() {
        if (userName != null && userName.isEmpty()) userName = null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Comment aux = (Comment) obj;

        if (id != null && aux.id != null)
            return (id == aux.id);
        else
            return false;
    }

}