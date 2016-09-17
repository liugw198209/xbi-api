package models;

import javax.persistence.*;

import play.data.validation.Constraints;
import play.data.format.*;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Entity
@Table(name="le_scourse")
public class Course{
    public static String TABLE = Course.class.getSimpleName();

    @Id
    @Column(name = "page_id")
    public Long courseId;

    @Constraints.Required
    @Column(name = "title1")
    public String title;
    
    @Column(name = "site_id")
    public Long siteId;

    @Column(name = "url")
    public String courseURL;

    @Column(name = "category1")
    public String mainCategory;

    @Column(name = "category2")
    public String subCategory;

    @Column(name = "description")
    public String description;

    @Column(name = "update_date")
    public Date updateDate;

    public Course() {}
    
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Course aux = (Course) obj;

        if (courseId != null && aux.courseId != null)
            return (courseId == aux.courseId);
        else
            return (title.equals(aux.title));
    }
}