package com.example.mox.myapplication.Classes;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by MOX on 20/09/2015.
 */
public class User {
    private String first_name;
    private String last_name;
    private String email;
    private String password;

    public User(String first_name, String last_name, String email, String password){
        this.setFirst_name(first_name);
        this.setLast_name(last_name);
        this.setEmail(email);
        this.setPassword(password);
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public JSONObject creatingJSON(User user){
        JSONObject user_json = new JSONObject();
        try {
            user_json.put("first_name",user.getFirst_name());
            user_json.put("last_name",user.getLast_name());
            user_json.put("password",user.getPassword());
            user_json.put("email",user.getEmail());
            user_json.put("device_token",user.getEmail());
            user_json.put("platform","Android 6.0");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user_json;
    }
}
