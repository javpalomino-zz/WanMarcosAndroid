package wan.wanmarcos.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import wan.wanmarcos.R;
import wan.wanmarcos.models.Course;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.utils.Redirection.Redirect;


public class SectionCourseProfile extends Fragment {

    public SectionCourseProfile(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Redirect.getSingletonInstance().setContent(this,Constants.PROFILE_COURSE_TITLE,new SectionCourseTitle());
        Redirect.getSingletonInstance().setContent(this,Constants.PROFILE_COURSE_RATING,new SectionCourseRating());
        return inflater.inflate(Constants.PROFILE_COURSE_INFORMATION,container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
