package wan.wanmarcos.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import wan.wanmarcos.R;
import wan.wanmarcos.models.Course;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.utils.Redirection.Redirect;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeacherCourseProfileFragment extends Fragment {


    public TeacherCourseProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        Redirect.getSingletonInstance().setContent(this,Constants.PROFILE_CORUSE_ID,new SectionCourseProfile());
        Redirect.getSingletonInstance().setContent(this,Constants.VALUATION_LIST_ID,new SectionValuationsCourse());
        return inflater.inflate(Constants.FRAGMENT_TEACHER_COURSE_LAYOUT, container, false);
    }

    @Override
    public void onResume() {
        Redirect.getSingletonInstance().setActivity((AppCompatActivity) getActivity(), R.id.home_fragment);
        super.onResume();
    }
}
