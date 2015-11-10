package wan.wanmarcos.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import wan.wanmarcos.activities.MainActivity;
import wan.wanmarcos.utils.Constants;

/**
 * Created by javier on 26/09/15.
 */
public class User {
    private int id;
    private static String token;
    private String first_name;
    private String last_name;

    private String device_token;
    private String platform;

    @Override
    public String toString(){
        return  "User[ first_name: "+first_name+" last_name: "+last_name+"]";
    }
    public static String getToken(){
        Context context = MainActivity.getContext();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

        return token= Constants.HEADER+preferences.getAll();
    }

}
