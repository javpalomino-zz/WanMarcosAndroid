package wan.wanmarcos.utils;

import com.google.gson.JsonElement;
import com.squareup.okhttp.RequestBody;

import java.util.Calendar;
import java.util.Map;

import retrofit.Call;
import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.PartMap;
import retrofit.http.Path;
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
    @GET(Constants.TEACHER_DETAIL)
    Call<JsonElement> getDetailTeacher(@Header("Authorization") String token,
                                       @Path("id")String professor_id);

    @GET(Constants.TEACHER_COURSES)
    Call<JsonElement> getTeacherCourses(@Header("Authorization") String token,
                                        @Query("professor_id") int id,
                                        @Query("page") int page,
                                        @Query("per_page") int per_page);

    @GET(Constants.TEACHER_COURSE_DETAIL)
    Call<JsonElement> getDetailTeacherCourse(@Header("Authorization") String token,
                                             @Path("subject_id") int subject_id,
                                             @Query("professor_id")int professor_id);
    @GET(Constants.COURSE_COMMENTS)
    Call<JsonElement> getCourseComments(@Header("Authorization") String token,
                                        @Query("professor_id")int professor_id,
                                        @Query("subject_id") int subject_id,
                                        @Query("page") int page_id,
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
    Call<JsonElement> me(@Header("Authorization") String authorization);

    @GET(Constants.EVENTS)
    Call<JsonElement> getEvents(@Header("Authorization") String authorization ,
                                @Query("search_text") String search_text,
                                @Query("page") int page,
                                @Query("per_page") int per_page);
    @GET(Constants.EVENTS_DETAIL)
    Call<JsonElement> getEventDetail(@Header("Authorization") String authorization ,
                                     @Path("id") int eventId);
    @Multipart
    @POST(Constants.CHANGE_PROFILE_PHOTO)
    Call<JsonElement> changeProfilePhoto(@Header("Authorization") String authorization ,@Part("image\"; filename=\"image.jpg\" ")RequestBody image);

    @FormUrlEncoded
    @POST(Constants.CHANGE_PROFILE_INFORMATION)
    Call<JsonElement> changeProfileInformation(@Header("Authorization") String authorization ,@Field("faculty_id") int faculty, @Field("degree_id") int degree );
    @Multipart
    @POST(Constants.EVENTS)
    Call<JsonElement> suggestEvent(@Header("Authorization") String authorization,
                                   @Part("name") String event_name,
                                   @Part("description") String event_description,
                                   @Part("starts_at")long event_startCal,
                                   @Part("ends_at")long event_endCal,
                                   @Part("website")String event_link,
                                   @Part("image\"; filename=\"image.jpg\" ")RequestBody image);
    @Multipart
    @POST(Constants.EVENTS)
    Call<JsonElement> suggestEventwithSchedule(@Header("Authorization") String authorization,
                                   @Part("name") String event_name,
                                   @Part("description") String event_description,
                                   @Part("starts_at")long event_startCal,
                                   @Part("ends_at")long event_endCal,
                                   @Part("website")String event_link,
                                   @Part("place_id") int place,
                                   @Part("category_id") int category,
                                   @Part("image\"; filename=\"image.jpg\" ")RequestBody image,
                                   @PartMap Map<String,RequestBody> schedule);
    @FormUrlEncoded
    @POST(Constants.SUGGESTIONS)
    Call<JsonElement> suggestions(@Header("Authorization") String authorization,@Field("message") String message);

    @POST(Constants.REFRESH)
    Call<JsonElement> resfreshToken(@Header("Authorization") String authorization);


    @GET(Constants.AUTOCOMPLETE_FACULTIES)
    Call<JsonElement> autocompleteFaculties(@Query("search_text") String searchText);
    @GET(Constants.AUTOCOMPLETE_CARREERS)
    Call<JsonElement> autocompleteCarrers(@Query("search_text") String searchText);
    @GET(Constants.AUTOCOMPLETE_PLACES)
    Call<JsonElement> autocompletePlaces(@Query("search_text") String searchText);
    @GET(Constants.AUTOCOMPLETE_CATEGORIES)
    Call<JsonElement> autocompleteCategories(@Query("search_text") String searchText);



}
