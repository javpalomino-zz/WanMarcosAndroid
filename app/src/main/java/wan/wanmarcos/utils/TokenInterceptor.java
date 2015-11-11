package wan.wanmarcos.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import retrofit.Call;
import wan.wanmarcos.models.Session;

/**
 * Created by javier on 09/11/15.
 */
public class TokenInterceptor implements Interceptor{
    Context context;
    RestClient restClient;
    SharedPreferences preferences;
    Session session;

    public TokenInterceptor(Context context){
        this.context = context;
        restClient =  new RestClient();
        preferences = context.getSharedPreferences(Constants.PREFERENCES, context.MODE_PRIVATE);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        try{
            Request request = chain.request(), newRequest;

            session = Session.getSession(preferences);

            Call<JsonElement> refresh =  restClient.getConsumerService().resfreshToken(Constants.HEADER + session.getToken());
            retrofit.Response<JsonElement>  resp = refresh.execute();
            if(resp.body()==null){
                System.out.println(resp.errorBody().string());
            }
            String newToken = resp.body().getAsJsonObject().get("token").toString();

            session.setToken(newToken);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("token",newToken);
            editor.apply();



            newRequest = request.newBuilder()
                    .removeHeader("Authorization")
                    .addHeader("Authorization", Constants.HEADER + newToken)
                    .build();

            return chain.proceed(newRequest);
        }catch (Throwable e){
            final Activity activity = (Activity) context;
                    activity.runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(activity, "No hay conexión a internet", Toast.LENGTH_SHORT).show();
                }
            });
            return null;
        }
    }
}
