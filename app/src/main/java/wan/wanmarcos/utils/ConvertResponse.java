package wan.wanmarcos.utils;

import org.json.JSONObject;

import java.util.Iterator;

/**
 * Created by MOX-PC on 12/11/2015.
 */
public class ConvertResponse {
    public static String getMessageAutenticate(JSONObject response){
        try {
            if(response.getJSONObject("error").getInt("code")==1) {
                boolean email = response.getJSONObject("error").getJSONObject("reason").has("email");
                boolean password = response.getJSONObject("error").getJSONObject("reason").has("password");
                if (email && password) {
                    return response.getJSONObject("error").getString("suggestion");
                } else {
                    if (email) {
                        return response.getJSONObject("error").getJSONObject("reason").getString("email");
                    } else {
                        if (password) {
                            return response.getJSONObject("error").getJSONObject("reason").getString("password");
                        } else {
                            return response.getJSONObject("error").getString("message");
                        }
                    }
                }
            }else{
                return response.getJSONObject("error").getString("reason");
            }
        }catch(Throwable e){
               return "Entro al catch: "+e.getMessage().toString();
        }
    }
}
