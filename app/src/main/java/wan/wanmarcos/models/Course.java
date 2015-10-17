package wan.wanmarcos.models;

/**
 * Created by carlos-pc on 09/10/15.
 */
public class Course {
    private String name;
    private float rating;
    private String Faculty;
    private String teacher;


    public Course(String name, float rating, String faculty,String teacher) {
        this.setName(name);
        this.rating=rating;
        setFaculty(faculty);
        this.teacher=teacher;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getFaculty() {
        return Faculty;
    }

    public void setFaculty(String faculty) {
        Faculty = faculty;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}
