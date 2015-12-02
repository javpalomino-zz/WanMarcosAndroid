package wan.wanmarcos.views.adapters.ViewHolders;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import wan.wanmarcos.R;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.utils.RestClient;
import wan.wanmarcos.utils.Storage;
import wan.wanmarcos.views.adapters.PopUpFragment;
import wan.wanmarcos.views.widgets.CircleTransform;

/**
 * Created by soporte on 28/11/15.
 */
public class ValuationCourseHeaderHolder extends CustomHeaderViewHolder {
    private TextView teacherName;
    private TextView courseName;
    private TextView rating;
    private TextView facultyName;
    private ImageView teacherImage;
    private ImageView teacherCardBackground;
    private FloatingActionButton floatingActionButton;
    private PopUpFragment addClicked;
    private RestClient restClient;
    private String  token="Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI1IiwiaXNzIjoiaHR0cDpcL1wvNTIuODkuMTI0LjBcL2FwaVwvdjFcL2F1dGhlbnRpY2F0ZSIsImlhdCI6IjE0NDcxMDQ5MzQiLCJleHAiOiIxNDU1NzQ0OTM0IiwibmJmIjoiMTQ0NzEwNDkzNCIsImp0aSI6IjcxZjM2NjgwN2EwZTIyZTY1ODM0OWYzZDMyOTcxNDQ1In0.gQK_MjKSRx6BhVCsy0CyhvJTEZB-wK2EWvKKJrDpUm4";

    public ValuationCourseHeaderHolder(View itemView) {
        super(itemView);
        teacherCardBackground= (ImageView) itemView.findViewById(R.id.teacher_course_card_background);
        teacherName=(TextView)itemView.findViewById(R.id.profile_course_teacher_name);
        courseName=(TextView)itemView.findViewById(R.id.profile_course_course_name);
        facultyName=(TextView)itemView.findViewById(R.id.profile_course_faculty_name);
        teacherImage=(ImageView)itemView.findViewById(R.id.profile_course_teacher_image);
        floatingActionButton=(FloatingActionButton)itemView.findViewById(R.id.addComment);
        rating=(TextView)itemView.findViewById(R.id.profile_course_teacher_rating);
        restClient=new RestClient(itemView.getContext());
    }

    @Override
    public void setElements() {
        Picasso.with(itemView.getContext()).load(R.mipmap.backgroundcardteacher).fit().centerCrop().into(teacherCardBackground);
        Log.d("D", Storage.getSingelton().toString());
        Call<JsonElement>jsonElementCall=restClient.getConsumerService().getDetailTeacherCourse(token, Integer.parseInt(Storage.getSingelton().getInfo(Storage.KEY_COURSE_ID)),Integer.parseInt(Storage.getSingelton().getInfo(Storage.KEY_TEACHER_ID)));
        jsonElementCall.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Response<JsonElement> response, Retrofit retrofit) {
                JsonObject responseBody = response.body().getAsJsonObject();
                courseName.setText(responseBody.get("name").getAsString());
                if (responseBody.get("score").isJsonNull()) {
                    rating.setText("" + 0.0);
                } else {
                    rating.setText(responseBody.get("score").getAsFloat() + "");
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
        Picasso.with(itemView.getContext()).load("http://lorempixel.com/g/400/200/").transform(new CircleTransform()).into(teacherImage);
        facultyName.setText("d");
        teacherName.setText("carlos");
        addListeners();
    }
    public void addListeners(){
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addClicked.popUp();
            }
        });
    }

    public void setListener(PopUpFragment valuationListAdapter) {
        addClicked=valuationListAdapter;

    }
}
