package wan.wanmarcos.utils;

import android.app.DownloadManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.squareup.okhttp.Authenticator;
import com.squareup.okhttp.Request;

import org.json.JSONObject;

import java.io.IOException;
import java.net.Proxy;

import retrofit.Call;
import retrofit.Callback;
import com.squareup.okhttp.Response;

import retrofit.Retrofit;
import wan.wanmarcos.activities.MainActivity;

/**
 * Created by Cieza on 08/11/2015.
 */
public class TokenAuthenticator implements Authenticator{
    public Request authenticate(Proxy proxy, Response response) throws IOException {

        Context context = MainActivity.getContext();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String token = sharedPreferences.getString("token", null);
        RestClient restClient =  new RestClient();
        Call<JsonElement> refresh =  restClient.getConsumerService().resfreshToken(Constants.HEADER+token);
        retrofit.Response<JsonElement>  resp = refresh.execute();
        String newToken = resp.body().getAsJsonObject().get("token").toString();

        return response.request().newBuilder().header("Authorization",Constants.HEADER+newToken).build();
    }

    @Override
    public Request authenticateProxy(Proxy proxy, Response response) throws IOException {
        return null;
    }
}
