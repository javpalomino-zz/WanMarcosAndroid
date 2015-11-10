package wan.wanmarcos.utils;

import com.google.gson.JsonElement;

import retrofit.Call;
import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by javier on 25/09/15.
 */
public interface ConsumerService {


    @GET(Constants.TEACHERS)
    Call<JsonElement> getTeachers(@Header("Authorization") String token,
                @Query("search_text") String search_text,
                @Query("page") int page,
                @Query("per_page") int per_page);


    @FormUrlEncoded
    @POST(Constants.SIGN_UP)
    Call<JsonElement> signUp(@Field("email") String email,
                @Field("password") String password,
                @Field("first_name") String first_name,
                @Field("last_name") String last_name,
                @Field("device_token") String device_token,
                @Field("platform") String platform);

    @FormUrlEncoded
    @POST(Constants.LOGIN)
    Call<JsonElement> login(@Field("email") String email,
               @Field("password") String password,
               @Field("device_token") String device_token,
                @Field("platform") String platform);

    @GET(Constants.USER_INFO)
    void me(@Header("Authorization") String authorization ,Callback<JsonElement> callback);


}
