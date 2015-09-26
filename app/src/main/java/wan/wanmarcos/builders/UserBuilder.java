package wan.wanmarcos.builders;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import wan.wanmarcos.models.User;

/**
 * Created by javier on 26/09/15.
 */
public class UserBuilder {
    public UserBuilder(){

    }
    public User build(JsonObject userObject){
        Gson gson = new Gson();
        return gson.fromJson(userObject,User.class);
    }
}
