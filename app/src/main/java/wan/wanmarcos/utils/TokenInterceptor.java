package wan.wanmarcos.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.JsonElement;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import retrofit.Call;

/**
 * Created by javier on 09/11/15.
 */
public class TokenInterceptor implements Interceptor{
    Context context;
    RestClient restClient;
    SharedPreferences preferences;

    public TokenInterceptor(Context context){
        this.context = context;
        restClient =  new RestClient();
        preferences = context.getSharedPreferences(Constants.PREFERENCES, context.MODE_PRIVATE);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request(), newRequest;

        String token = preferences.getString("token", null);

        Call<JsonElement> refresh =  restClient.getConsumerService().resfreshToken(Constants.HEADER + token);
        retrofit.Response<JsonElement>  resp = refresh.execute();
        String newToken = resp.body().getAsJsonObject().get("token").toString();

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("token",newToken);
        editor.apply();



        newRequest = request.newBuilder()
                .removeHeader("Authorization")
                .addHeader("Authorization", Constants.HEADER + newToken)
                .build();

        return chain.proceed(newRequest);
    }
}
