package wan.wanmarcos.fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import wan.wanmarcos.R;
import wan.wanmarcos.models.Course;
import wan.wanmarcos.utils.Constants;


public class SectionCourseProfile extends Fragment {
    private Course course;

    public SectionCourseProfile(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(Constants.PROFILE_COURSE_INFORMATION,container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
