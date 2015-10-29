package wan.wanmarcos.fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import wan.wanmarcos.R;
import wan.wanmarcos.models.Course;
import wan.wanmarcos.models.Rating;
import wan.wanmarcos.models.Teacher;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.views.adapters.CourseListAdapter;
import wan.wanmarcos.views.adapters.RatingListAdapter;


public class TeacherProfileFragment extends Fragment {
    private Teacher teacher;

    public TeacherProfileFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(Constants.FRAGMENT_PROFILE_TEACHER_LAYOUT, container, false);
    }
    public void changeData(Teacher object){
        this.teacher=object;
    }

}
