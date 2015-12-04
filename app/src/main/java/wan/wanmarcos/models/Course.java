package wan.wanmarcos.models;

import android.util.Log;

import com.google.gson.JsonObject;

import java.util.ArrayList;

/**
 * Created by carlos-pc on 09/10/15.
 */
public class Course {
    private String name;
    private float rating;
    private String faculty;
    private int id;

    public Course(String name, float rating, String faculty, int id) {
        this.name = name;
        this.rating = rating;
        this.faculty = faculty;
        this.id = id;
    }

    public Course(String name, float rating, String faculty) {
        this.setName(name);
        this.rating=rating;
        setFaculty(faculty);
    }


    public Course(JsonObject object) {
        this.id=object.get("id").getAsInt();
        this.name=object.get("name").getAsString();
        if(object.get("score").isJsonNull()){
            this.rating=0;
        }
        else{
            this.rating=object.get("score").getAsFloat();
        }
        this.faculty=object.get("faculty").getAsJsonObject().get("name").getAsString();

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
        return faculty;
    }

    public void setFaculty(String faculty) {
        faculty = faculty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
