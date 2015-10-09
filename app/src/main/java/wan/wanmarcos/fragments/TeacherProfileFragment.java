package wan.wanmarcos.fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import wan.wanmarcos.R;
import wan.wanmarcos.models.Courses;
import wan.wanmarcos.models.Rating;
import wan.wanmarcos.models.Teacher;


public class TeacherProfileFragment extends Fragment {
    View view;
    ListView listRating,listCourses;
    Teacher teacher;
    TextView name;

    public TeacherProfileFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_teacher_profile, container, false);

        setUpElements();
        return view;
    }
    public void setUpElements(){
        listCourses=(ListView)view.findViewById(R.id.teacher_courses);
        name=(TextView)view.findViewById(R.id.teacher_name);
        name.setText(teacher.getName());
        listRating=(ListView)view.findViewById(R.id.teacher_raitings);
        ImageView profilePicture=(ImageView)view.findViewById(R.id.teacher_image);
    }
    public void changeData(Teacher teacher){
        this.teacher=teacher;
    }
}
