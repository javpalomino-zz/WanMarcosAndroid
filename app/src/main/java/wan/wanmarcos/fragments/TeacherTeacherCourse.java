package wan.wanmarcos.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import wan.wanmarcos.R;
import wan.wanmarcos.models.Course;
import wan.wanmarcos.utils.Constants;


public class TeacherTeacherCourse extends Fragment {
    private Course course;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getFragmentManager().beginTransaction().add(R.id.sub_fragment_course_profile,new SectionCourseProfile(course)).commit();
        getFragmentManager().beginTransaction().add(R.id.sub_fragment_valuations_course,new SectionValuationsCourse()).commit();
        return inflater.inflate(Constants.FRAGMENT_TEACHER_COURSE_LAYOUT,container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    public void changeData(Course object){
        this.course=object;
    }
}
