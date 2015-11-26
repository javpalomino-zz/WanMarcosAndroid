package wan.wanmarcos.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.nio.InvalidMarkException;

import wan.wanmarcos.R;
import wan.wanmarcos.managers.Click;
import wan.wanmarcos.managers.FragmentsMethods;
import wan.wanmarcos.models.Rating;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.utils.Storage;
import wan.wanmarcos.views.adapters.RatingListAdapter;
import wan.wanmarcos.views.widgets.CircleTransform;

/**
 * Created by soporte on 19/11/15.
 */
public class TeacherPopupComment extends DialogFragment implements FragmentsMethods{
    private ImageView backgroundHeader;
    private ImageView imageIcon;
    private TextView teacher;
    private TextView course;
    private TextView faculty;
    private RecyclerView recyclerView;
    private RatingListAdapter ratingListAdapter;
    private EditText comment;
    private Button button;
    private Click onClickListener;
    public TeacherPopupComment() {
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
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {

            dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        }
    }

    @Override
    public void setUpElements(View view) {
        backgroundHeader = (ImageView) view.findViewById(R.id.comment_background_header);
        Picasso.with(getActivity()).load(R.mipmap.testimage).fit().centerCrop().into(backgroundHeader);
        imageIcon = (ImageView)view.findViewById(R.id.header_image_icon);
        Picasso.with(getActivity()).load(Storage.getSingelton().getInfo(this,Storage.KEY_TEACHER_IMAGE)).transform(new CircleTransform()).into(imageIcon);
        teacher=(TextView)view.findViewById(R.id.teacher_comment_professor);
        teacher.setText(Storage.getSingelton().getInfo(this,Storage.KEY_TEACHER_NAME));
        course=(TextView)view.findViewById(R.id.teacher_comment_course);
        course.setText(Storage.getSingelton().getInfo(this,Storage.KEY_COURSE_NAME));
        faculty=(TextView)view.findViewById(R.id.teacher_comment_faculty);
        faculty.setText(Storage.getSingelton().getInfo(this,Storage.KEY_FACULTY_NAME));
        recyclerView=(RecyclerView)view.findViewById(R.id.teacher_comment_rating);
        comment=(EditText)view.findViewById(R.id.teacher_comment_post);
        button=(Button)view.findViewById(R.id.teacher_comment_action);
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
                if(validations()){
                    onClickListener.click(comment.getText().toString());
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
    public void setListener(Click listener){
        onClickListener=listener;
    }
}
