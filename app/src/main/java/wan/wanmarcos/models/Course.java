package wan.wanmarcos.models;

/**
 * Created by carlos-pc on 09/10/15.
 */
public class Course {
    private String name;
    private float rating;
    private String Faculty;
    private int id;

    public Course(String name, float rating, String faculty, int id) {
        this.name = name;
        this.rating = rating;
        Faculty = faculty;
        this.id = id;
    }

    public Course(String name, float rating, String faculty) {
        this.setName(name);
        this.rating=rating;
        setFaculty(faculty);
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
