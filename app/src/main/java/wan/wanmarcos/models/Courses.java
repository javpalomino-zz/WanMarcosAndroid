package wan.wanmarcos.models;

/**
 * Created by carlos-pc on 09/10/15.
 */
public class Courses {
    private String name;
    private String rating;
    private String Faculty;

    public Courses(String name, String rating, String faculty) {
        this.setName(name);
        this.setRating(rating);
        setFaculty(faculty);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getFaculty() {
        return Faculty;
    }

    public void setFaculty(String faculty) {
        Faculty = faculty;
    }
}
