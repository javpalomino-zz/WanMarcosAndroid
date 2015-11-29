package wan.wanmarcos.fragments;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import wan.wanmarcos.R;
import wan.wanmarcos.managers.Click;
import wan.wanmarcos.managers.FragmentsMethods;
import wan.wanmarcos.models.Rating;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.utils.Storage;
import wan.wanmarcos.views.adapters.PopUpFragment;
import wan.wanmarcos.views.adapters.RatingListAdapter;

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
    private Button button;
    private PopUpFragment onClickListener;
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
    public void setListener(PopUpFragment listener){
        onClickListener=listener;
    }
}
