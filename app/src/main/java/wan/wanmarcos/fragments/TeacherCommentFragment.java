package wan.wanmarcos.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import wan.wanmarcos.R;
import wan.wanmarcos.managers.FragmentsMethods;
import wan.wanmarcos.utils.Constants;

/**
 * Created by carlos-pc on 16/11/15.
 */
public class TeacherCommentFragment extends Fragment implements FragmentsMethods{
    private Button button;
    private TextView teacherName;
    private TextView courseName;
    private TextView facultyName;
    private EditText comment;
    private RecyclerView recyclerViewRatings;

    public TeacherCommentFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(Constants.FRAGMENT_TEACHER_COMMENT_LAYOUT, container, false);
        setUpElements(view);
        addListeners();
        return view;
    }

    @Override
    public void setUpElements(View view) {
        button=(Button)view.findViewById(R.id.popup_comment_post);
        teacherName=(TextView)view.findViewById(R.id.popup_comment_teacher_name);
        courseName=(TextView)view.findViewById(R.id.popup_comment_course_name);
        facultyName=(TextView)view.findViewById(R.id.popup_comment_faculty_name);
        comment=(EditText)view.findViewById(R.id.popup_comment_text);
    }

    @Override
    public void addListeners() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
