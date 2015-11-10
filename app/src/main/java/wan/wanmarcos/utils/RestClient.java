package wan.wanmarcos.utils;

import android.app.Activity;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by javier on 25/09/15.
 */
public class RestClient {

    private ConsumerService consumerService;

    public RestClient() {

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(Constants.WANMARCOS_API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        consumerService = retrofit.create(ConsumerService.class);
    }

    public RestClient(Context context) {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.interceptors().add(new TokenInterceptor(context));
        Retrofit retrofit = new Retrofit
                .Builder().client(okHttpClient)
                .baseUrl(Constants.WANMARCOS_API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        consumerService = retrofit.create(ConsumerService.class);
    }

    public ConsumerService getConsumerService() {
        return consumerService;
    }

}
