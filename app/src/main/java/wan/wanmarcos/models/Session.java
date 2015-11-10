package wan.wanmarcos.models;

import android.content.SharedPreferences;

/**
 * Created by javier on 26/09/15.
 */
public class Session {
    private String token;

    private static Session session = null;

    public static Session getSession(SharedPreferences preferences){
        if(session == null){
            session = new Session();
        }
        String storedToken = preferences.getString("token", null);
        session.setToken(storedToken);
        return session;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
