package wan.wanmarcos.models;

import android.util.Log;

import com.google.gson.JsonArray;
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
    private float raiting;
    private List<String> courses;
    private String description;
    private String imageUrl;

    public Teacher(JsonObject object){
        this.id=object.get("id").getAsInt();
        this.firstName=object.get("first_name").getAsString();
        this.lastName=object.get("last_name").getAsString();
        this.imageUrl=object.get("image").getAsString();
        if(object.get("score").isJsonNull()){
            this.raiting=0;
        }
        else{
            this.raiting=object.get("score").getAsFloat();
        }
        this.courses=new ArrayList<>();

        if(object.get("subjects").getAsJsonArray().size()==0){
            this.courses.add("Ningun curso registrado.");
        }
        else{
            JsonArray jsonElements=object.get("subjects").getAsJsonArray();
            int j=jsonElements.size();
            for(int i=0;i<j;i++){
                this.courses.add(jsonElements.get(i).getAsJsonObject().get("name").getAsString());
            }

        }
    }

    public Teacher(int id, String firstName, String lastName, int raiting, List<String> faculties, String description, String imageUrl) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.raiting = raiting;
        this.courses= faculties;
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

    public String getRaiting() {
        return String.valueOf(raiting);
    }

    public void setRaiting(int raiting) {
        this.raiting = raiting;
    }

    public String getFaculties() {
        String fac="";
        int i=courses.size();
        if(i>0){
            fac=fac+courses.get(0).toString();
            for(int j=1;j<i;j++){
                fac=fac+", "+courses.get(j).toString();
            }
        }
        return fac;
    }

    public void setFaculties(List<String> faculties) {
        this.courses = faculties;
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
