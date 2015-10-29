package wan.wanmarcos.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import wan.wanmarcos.R;
import wan.wanmarcos.managers.Communicator;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.utils.ConsumerService;

public class SectionCourseTitle extends Fragment {
    private Communicator communicator;
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
        communicator=(Communicator)getActivity();
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        TextView teacherName=(TextView)view.findViewById(R.id.teacher_name);
        teacherName.setText(communicator.getStringInformation("teachername"));
        TextView courseName=(TextView)view.findViewById(R.id.course_name);
        courseName.setText(communicator.getStringInformation("coursename"));
        TextView facultyName=(TextView) view.findViewById(R.id.faculty_name);
        facultyName.setText(communicator.getStringInformation("facultyname"));
    }

}
