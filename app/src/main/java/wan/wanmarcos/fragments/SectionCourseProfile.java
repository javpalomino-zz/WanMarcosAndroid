package wan.wanmarcos.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import wan.wanmarcos.R;
import wan.wanmarcos.managers.Communicator;
import wan.wanmarcos.models.Course;
import wan.wanmarcos.utils.Constants;


public class SectionCourseProfile extends Fragment {
    private Course course;
    public SectionCourseProfile(Course object){
        this.course=object;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //getFragmentManager().beginTransaction().add(R.id.sub_fragment_title,new SectionCourseTitle(course.getName(),course.getTeacher())).commit();
        //getFragmentManager().beginTransaction().add(R.id.sub_fragment_rating, new SectionCourseRating()).commit();
        return inflater.inflate(Constants.PROFILE_INFORMATION,container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //ImageView imageView=(ImageView)view.findViewById(R.id.image_id);
        //Picasso.with(view.getContext()).load("http://lorempixel.com/350/230/").into(imageView);
    }
}
