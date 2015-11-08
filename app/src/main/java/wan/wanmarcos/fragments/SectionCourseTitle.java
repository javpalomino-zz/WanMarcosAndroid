package wan.wanmarcos.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import wan.wanmarcos.R;
import wan.wanmarcos.utils.Redirection.Redirect;
import wan.wanmarcos.utils.Redirection.Redirection;
import wan.wanmarcos.utils.Constants;

public class SectionCourseTitle extends Fragment {
    private TextView teacherName;
    private TextView courseName;
    private TextView facultyName;
    public SectionCourseTitle(){
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(Constants.COURSE_TITLE,container,false);
        setUpElements(view);
        return view;
    }
    public void setUpElements(View view){
        teacherName=(TextView)view.findViewById(R.id.teacher_name);
        teacherName.setText(Redirect.getSingletonInstance().getInformation("teachername"));
        courseName=(TextView)view.findViewById(R.id.course_name);
        courseName.setText(Redirect.getSingletonInstance().getInformation("coursename"));
        facultyName=(TextView)view.findViewById(R.id.faculty_name);
        facultyName.setText(Redirect.getSingletonInstance().getInformation("coursefaculty"));
    }

}
