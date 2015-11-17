package wan.wanmarcos.utils;
import com.google.gson.JsonElement;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Iterator;
import retrofit.Response;

/**
 * Created by MOX-PC on 12/11/2015.
 */
public class ConvertResponse {
    private Response<JsonElement> response;
    private JSONObject reason;
    private ArrayList<String> keys;
    private String message;
    public ConvertResponse(Response<JsonElement> _response){
        response = _response;
        message = "";
    }
    private void setErrorBody(){
        try {
            JSONObject errorBody = new JSONObject(response.errorBody().string());
            if(errorBody.getJSONObject("error").getInt("code")==2){
                message = errorBody.getJSONObject("error").getJSONArray("reason").getString(0);
            }else{
                reason = errorBody.getJSONObject("error").getJSONObject("reason");
            }
        }catch(Throwable e){
            message += e.getMessage()+"\n";
        }
    }
    public String getMessage(){
        setErrorBody();
        if(response.code()==500){
            message="Error del Servidor. Vuelva a intentar más tarde";
        }else{
            try{
                if(reason!=null) {
                    keys = new ArrayList<String>();
                    Iterator<String> iterator = reason.keys();
                    while (iterator.hasNext()) {
                        String key = iterator.next();
                        message += reason.getJSONArray(key).getString(0)+"/";
                        keys.add(key.toString());
                    }
                }
                return message;
            }catch (Throwable e){
                message = "Error : "+e.getMessage();
                return message;
            }
        }
        return message;
    }
    public ArrayList<String> getKeys(){
        return keys;
    }
}
