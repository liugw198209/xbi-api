package models;

import java.sql.Date;

import javax.persistence.*;

import play.data.validation.Constraints;

@Entity
@Table(name="v_le_similar_course")
@NamedQueries({
@NamedQuery(
      name="findSimilarCourses",
      query="SELECT s FROM SimilarCourse s WHERE course_id = :givenCourseId and similarity >= :minSimilarity ORDER BY similarity DESC"
),
@NamedQuery(
	      name="findSimilarCourses.count",
	      query="SELECT count(s) FROM SimilarCourse s WHERE course_id = :givenCourseId and similarity >= :minSimilarity"
)
})
public class SimilarCourse{
    public static String TABLE = SimilarCourse.class.getSimpleName();

    @Id  
    @Column(name = "page_id")
    public Long courseId;

    @Constraints.Required
    @Column(name = "course_id")
    public Long givenCourseId;

    @Constraints.Required
    @Column(name = "title1")
    public String title;
    
    @Column(name = "similarity")
    public Double similarity;
    
//    @Column(name = "site_id")
//    public Long siteId;

    @Column(name = "url")
    public String courseURL;

    @Column(name = "category1")
    public String mainCategory;

    @Column(name = "category2")
    public String subCategory;

//    @Column(name = "description")
//    public String description;
//
//    @Column(name = "update_date")
//    public Date updateDate;

    public SimilarCourse() {}
    
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        SimilarCourse aux = (SimilarCourse) obj;

        if (courseId != null && aux.courseId != null)
            return (courseId == aux.courseId);
        else
            return (title.equals(aux.title));
    }

}