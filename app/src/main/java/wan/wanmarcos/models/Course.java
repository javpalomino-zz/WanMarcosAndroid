package wan.wanmarcos.models;

import com.google.gson.JsonObject;

import java.util.ArrayList;

/**
 * Created by carlos-pc on 09/10/15.
 */
public class Course {
    private String name;
    private float rating;
    private String Faculty;
    private int id;
    private int professor_id;

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

    public int getProfessor_id() {
        return professor_id;
    }

    public void setProfessor_id(int professor_id) {
        this.professor_id = professor_id;
    }

    public Course(JsonObject object) {
        this.id=object.get("id").getAsInt();
        this.professor_id=object.get("professor_id").getAsInt();
        this.name=object.get("name").getAsString();
        if(object.get("score").isJsonNull()){
            this.rating=0;
        }
        else{
            this.rating=object.get("score").getAsFloat();
        }

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
