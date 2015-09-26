package wan.wanmarcos.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import wan.wanmarcos.models.Session;
import wan.wanmarcos.models.User;
import wan.wanmarcos.models.Error;

/**
 * Created by javier on 26/09/15.
 */
public class Builder {
    Gson gson = new Gson();;
    public Builder(){

    }

    public User buildUser(JsonObject userObject){
        return gson.fromJson(userObject, User.class);
    }

    public Error buildError(JsonObject errorObject){
        return gson.fromJson(errorObject, Error.class);
    }

    public Session buildSession(JsonObject sessionObject){
        return gson.fromJson(sessionObject, Session.class);
    }
}
