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
@Table(name="le_scourse_like")
public class CourseLike {
    public static String TABLE = CourseLike.class.getSimpleName();

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
    @Column(name = "like_course")
    public Short like_course;
    
    @Column(name = "like_date")
    public Date likeDate;
    
    public CourseLike() {
    }
    public CourseLike(Long courseId, Short like) { 
    	this.courseId = courseId;
    	this.like_course = like;
    	this.likeDate = new Timestamp(new Date().getTime());
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
        CourseLike aux = (CourseLike) obj;

        if (id != null && aux.id != null)
            return (id == aux.id);
        else
            return false;
    }

}