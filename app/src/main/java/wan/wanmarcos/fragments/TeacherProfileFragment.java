package wan.wanmarcos.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import wan.wanmarcos.R;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.utils.Redirection.Redirect;


public class TeacherProfileFragment extends Fragment {

    public TeacherProfileFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(Constants.FRAGMENT_PROFILE_TEACHER_LAYOUT, container, false);
        //Redirect.getSingletonInstance().setContent(this,Constants.PROFILE_TEACHER_ID,new SectionTeacherProfile());
        //Redirect.getSingletonInstance().setContent(this,Constants.COURSE_LIST_ID,new SectionTeacherCourses());
        return view;
    }
    @Override
    public void onResume() {
        //Redirect.getSingletonInstance().setActivity((AppCompatActivity) getActivity(), R.id.home_fragment);
        super.onResume();
    }
}
