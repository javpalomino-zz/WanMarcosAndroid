package wan.wanmarcos.views.adapters.ViewHolders;

import android.support.v7.widget.RecyclerView;
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
import wan.wanmarcos.R;
import wan.wanmarcos.models.Course;
import wan.wanmarcos.utils.RestClient;
import wan.wanmarcos.utils.Storage;
import wan.wanmarcos.views.widgets.CircleTransform;

/**
 * Created by soporte on 28/11/15.
 */
public class CourseHeaderHolder extends CustomHeaderViewHolder{
    private TextView teacherName;
    private TextView ratingTeacher;
    private ImageView teacherCardBackground;
    private ImageView teacherImage;
    private RestClient restClient;
    private boolean set;
    private String token="Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI1IiwiaXNzIjoiaHR0cDpcL1wvNTIuODkuMTI0LjBcL2FwaVwvdjFcL2F1dGhlbnRpY2F0ZSIsImlhdCI6IjE0NDcxMDQ5MzQiLCJleHAiOiIxNDU1NzQ0OTM0IiwibmJmIjoiMTQ0NzEwNDkzNCIsImp0aSI6IjcxZjM2NjgwN2EwZTIyZTY1ODM0OWYzZDMyOTcxNDQ1In0.gQK_MjKSRx6BhVCsy0CyhvJTEZB-wK2EWvKKJrDpUm4";
    public CourseHeaderHolder(View itemView) {
        super(itemView);
        set=false;
        teacherCardBackground=(ImageView)itemView.findViewById(R.id.teacher_card_background);
        teacherImage=(ImageView)itemView.findViewById(R.id.profile_teacher_image);
        teacherName=(TextView)itemView.findViewById(R.id.profile_teacher_mame);
        ratingTeacher=(TextView)itemView.findViewById(R.id.profile_teacher_rating);
        restClient=new RestClient(itemView.getContext());
    }

    @Override
    public void setElements() {
        Picasso.with(itemView.getContext()).load("https://newevolutiondesigns.com/images/freebies/google-material-design-wallpaper-17.jpg").fit().centerCrop().into(teacherCardBackground);
        Call<JsonElement> jsonElementCall=restClient.getConsumerService().getDetailTeacher(token,Storage.getSingelton().getInfo(Storage.KEY_TEACHER_ID));
        jsonElementCall.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Response<JsonElement> response) {
                JsonObject responseBody = response.body().getAsJsonObject();
                if (responseBody.get("score").isJsonNull()) {
                    ratingTeacher.setText("0");
                } else {
                    ratingTeacher.setText(responseBody.get("score").getAsFloat()+ "");
                }
                teacherName.setText(responseBody.get("first_name").getAsString() + " " + responseBody.get("last_name").getAsString());
                Picasso.with(itemView.getContext()).load(responseBody.get("image").getAsString()).transform(new CircleTransform()).into(teacherImage);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}