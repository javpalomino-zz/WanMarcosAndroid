package wan.wanmarcos.models;

import android.util.Log;

import com.google.gson.JsonObject;

/**
 * Created by carlos-pc on 29/11/15.
 */
public class Home {
    private static final String JSON_TEACHER = "professors";
    private static final String JSON_EVENT = "events";
    private static final String JSON_PLACE = "places";
    private int id;
    private String name;
    private String description;
    private String type;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Home(JsonObject jsonObject, String jsonType){
        type=jsonType;
        id=jsonObject.get("id").getAsInt();
        if(type.equals(JSON_EVENT)){
            name=jsonObject.get("title").getAsString();
        }
        else if(type.equals(JSON_PLACE)){
            name=jsonObject.get("name").getAsString();
        }
        else{
            name=jsonObject.get("first_name").getAsString()+" "+jsonObject.get("last_name").getAsString();
        }
        if(!jsonObject.get("image").isJsonNull()){
            url=jsonObject.get("image").getAsString();
        }
        else{
            url="http://lorempixel.com/400/200/sports/1/";
        }
        description="No Descripcion";

    }


    public Home(int id, String name, String description, String type, String url) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
