package wan.wanmarcos.fragments;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import wan.wanmarcos.R;
import wan.wanmarcos.managers.FragmentsMethods;
import wan.wanmarcos.managers.ItemAdapterListener;
import wan.wanmarcos.models.Course;
import wan.wanmarcos.models.Rating;
import wan.wanmarcos.models.Teacher;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.utils.Redirection.Redirect;
import wan.wanmarcos.utils.RestClient;
import wan.wanmarcos.utils.Storage;
import wan.wanmarcos.views.adapters.CourseListAdapter;
import wan.wanmarcos.views.adapters.RatingListAdapter;
import wan.wanmarcos.views.widgets.CircleTransform;


public class TeacherProfileFragment extends Fragment implements FragmentsMethods{
    private int current_page;
    private RecyclerView recyclerViewTeacherCourses;
    private CourseListAdapter courseListAdapter;
    private RestClient restClient;
    private String JSON_SUBJECT="subject";
    private String token="Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI1IiwiaXNzIjoiaHR0cDpcL1wvNTIuODkuMTI0LjBcL2FwaVwvdjFcL2F1dGhlbnRpY2F0ZSIsImlhdCI6IjE0NDcxMDQ5MzQiLCJleHAiOiIxNDU1NzQ0OTM0IiwibmJmIjoiMTQ0NzEwNDkzNCIsImp0aSI6IjcxZjM2NjgwN2EwZTIyZTY1ODM0OWYzZDMyOTcxNDQ1In0.gQK_MjKSRx6BhVCsy0CyhvJTEZB-wK2EWvKKJrDpUm4";
    public TeacherProfileFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(Constants.FRAGMENT_PROFILE_TEACHER_LAYOUT, container, false);
        setUpElements(view);
        addListeners();
        return view;
    }

    @Override
    public void setUpElements(View view) {
        current_page=1;
        restClient=new RestClient(getActivity());
        recyclerViewTeacherCourses=(RecyclerView)view.findViewById(R.id.course_list);
        courseListAdapter=new CourseListAdapter(this);
        getData();
        recyclerViewTeacherCourses.setAdapter(courseListAdapter);
        recyclerViewTeacherCourses.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void addListeners() {

    }
    public void getData() {
        Call<JsonElement> jsonElementCall=restClient.getConsumerService().getTeacherCourses(token,Integer.parseInt(Storage.getSingelton().getInfo(Storage.KEY_TEACHER_ID)),current_page,Constants.CANTIDAD);
        jsonElementCall.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Response<JsonElement> response) {
                JsonObject responseBody = response.body().getAsJsonObject();
                if (responseBody.has(JSON_SUBJECT)) {
                    JsonArray jsonArray = responseBody.getAsJsonArray(JSON_SUBJECT);
                    for (int i = 0; i < jsonArray.size(); i++) {
                        JsonObject storedObject = jsonArray.get(i).getAsJsonObject();
                        Course current = new Course(storedObject);
                        courseListAdapter.add(current);
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
        courseListAdapter.add(new Course("Fisica", (float) 4.0,"FISI"));
        courseListAdapter.add(new Course("Matematica", (float) 4.0,"FISI"));
        courseListAdapter.add(new Course("holi", (float) 4.0,"FISI"));
        courseListAdapter.add(new Course("holi", (float) 4.0,"FISI"));
    }
}
