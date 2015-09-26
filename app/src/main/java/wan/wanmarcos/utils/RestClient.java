package wan.wanmarcos.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

    public ConsumerService getConsumerService() {
        return consumerService;
    }

}
