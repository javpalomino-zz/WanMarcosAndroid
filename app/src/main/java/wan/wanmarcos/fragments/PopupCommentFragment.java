package wan.wanmarcos.fragments;

import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import wan.wanmarcos.R;
import wan.wanmarcos.managers.Click;
import wan.wanmarcos.managers.FragmentsMethods;
import wan.wanmarcos.models.Rating;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.utils.RestClient;
import wan.wanmarcos.utils.Storage;
import wan.wanmarcos.views.adapters.PopUpFragment;
import wan.wanmarcos.views.adapters.RatingListAdapter;
import wan.wanmarcos.views.widgets.CircleTransform;

/**
 * Created by soporte on 19/11/15.
 */
public class PopupCommentFragment extends DialogFragment implements FragmentsMethods{
    private TextView teacher;
    private TextView course;
    private TextView faculty;
    private RecyclerView recyclerView;
    private RatingListAdapter ratingListAdapter;
    private EditText comment;
    private ImageView backgroundHeader;
    private ImageView imageIcon;
    private Button button;
    private String token="Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI1IiwiaXNzIjoiaHR0cDpcL1wvNTIuODkuMTI0LjBcL2FwaVwvdjFcL2F1dGhlbnRpY2F0ZSIsImlhdCI6IjE0NDcxMDQ5MzQiLCJleHAiOiIxNDU1NzQ0OTM0IiwibmJmIjoiMTQ0NzEwNDkzNCIsImp0aSI6IjcxZjM2NjgwN2EwZTIyZTY1ODM0OWYzZDMyOTcxNDQ1In0.gQK_MjKSRx6BhVCsy0CyhvJTEZB-wK2EWvKKJrDpUm4";
    private PopUpFragment onClickListener;
    private RestClient restClient;
    public PopupCommentFragment() {
        // Empty constructor required for DialogFragment
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(Constants.FRAGMENT_TEACHER_COMMENT_LAYOUT, container);
        setUpElements(view);
        addListeners();
        return view;
    }

    @Override
    public void setUpElements(View view) {
        restClient=new RestClient(getActivity());
        course=(TextView)view.findViewById(R.id.teacher_comment_course);
        Call<JsonElement>jsonElementCall=restClient.getConsumerService().getDetailTeacherCourse(token, Integer.parseInt(Storage.getSingelton().getInfo(Storage.KEY_COURSE_ID)), Integer.parseInt(Storage.getSingelton().getInfo(Storage.KEY_TEACHER_ID)));
        jsonElementCall.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Response<JsonElement> response) {
                JsonObject responseBody = response.body().getAsJsonObject();
                course.setText(responseBody.get("name").getAsString());
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
        backgroundHeader=(ImageView)view.findViewById(R.id.comment_background_header);
        Picasso.with(getActivity()).load(R.mipmap.backgroundcardteacher).fit().centerCrop().into(backgroundHeader);
        imageIcon=(ImageView)view.findViewById(R.id.header_image_icon);
        Picasso.with(getActivity()).load("http://lorempixel.com/350/230/").transform(new CircleTransform()).into(imageIcon);
        teacher=(TextView)view.findViewById(R.id.teacher_comment_professor);
        teacher.setText(Storage.getSingelton().getInfo(this,Storage.KEY_TEACHER_NAME));
        faculty=(TextView)view.findViewById(R.id.teacher_comment_faculty);
        faculty.setText(Storage.getSingelton().getInfo(this,Storage.KEY_FACULTY_NAME));
        recyclerView=(RecyclerView)view.findViewById(R.id.teacher_comment_rating);
        comment=(EditText)view.findViewById(R.id.teacher_comment_post);
        button=(Button)view.findViewById(R.id.teacher_comment_action);
        setBackgroundColor(button,R.attr.colorAccent);
        ratingListAdapter=new RatingListAdapter(getActivity());
        recyclerView.setAdapter(ratingListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        getRatingData();
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
    }
    public void getRatingData(){

        ratingListAdapter.add(new Rating("Desempeño:"));
        ratingListAdapter.add(new Rating("Empeño: "));
        ratingListAdapter.add(new Rating("Entusiasmo: "));
    }
    @Override
    public void addListeners() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validations()) {
                    onClickListener.close(comment.getText().toString());
                    dismiss();
                }
            }
        });
    }
    private boolean validations(){
        if(!comment.getText().toString().equals(Constants.EMPTY_STRING)){
            if(validationRating()){
                return true;
            }
        }
        return false;
    }
    private boolean validationRating(){
        int dim=ratingListAdapter.getItemCount();
        for(int i=0;i<dim;i++){
            if(!ratingListAdapter.isChanged(i)){
                return false;
            }
        }
        return true;
    }
    private void setBackgroundColor(Button button,int resID)
    {
        TypedValue typedValue = new TypedValue();
        getActivity().getTheme().resolveAttribute(resID, typedValue, true);
        int color = typedValue.data;
        button.setBackgroundColor(color);
    }
    public void setListener(PopUpFragment listener){
        onClickListener=listener;
    }
}
