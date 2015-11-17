package wan.wanmarcos.models;

import android.util.Log;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by carlos-pc on 06/10/15.
 */
public class Teacher {
    private int id;
    private String firstName;
    private String lastName;
    private int raiting;
    private List<String> faculties;
    private String description;
    private String imageUrl;

    public Teacher(JsonObject object){
        this.id=object.get("id").getAsInt();
        this.firstName=object.get("first_name").getAsString();
        this.lastName=object.get("last_name").getAsString();
        this.imageUrl=object.get("image").getAsString();
        this.faculties=new ArrayList<>();
    }

    public Teacher(int id, String firstName, String lastName, int raiting, List<String> faculties, String description, String imageUrl) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.raiting = raiting;
        this.faculties = faculties;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public String getName(){
        return lastName+firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getRaiting() {
        return raiting;
    }

    public void setRaiting(int raiting) {
        this.raiting = raiting;
    }

    public String getFaculties() {
        String fac="";
        int i=faculties.size();
        if(i>0){
            fac=fac+faculties.get(0).toString();
            for(int j=1;j<i;j++){
                fac=fac+", "+faculties.get(j).toString();
            }
        }
        return fac;
    }

    public void setFaculties(List<String> faculties) {
        this.faculties = faculties;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
