package wan.wanmarcos.utils;

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
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setAuthenticator(new TokenAuthenticator());
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
