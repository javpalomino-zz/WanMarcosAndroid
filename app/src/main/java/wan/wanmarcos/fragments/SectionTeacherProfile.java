package wan.wanmarcos.fragments;

import android.app.Activity;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import wan.wanmarcos.R;
import wan.wanmarcos.models.Teacher;
import wan.wanmarcos.utils.Constants;

public class SectionTeacherProfile extends Fragment {
    private Teacher teacher;
    public SectionTeacherProfile(Teacher object){
        this.teacher=object;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getFragmentManager().beginTransaction().add(R.id.sub_fragment_title,new SectionTeacherTitle(teacher.getName())).commit();
        getFragmentManager().beginTransaction().add(R.id.sub_fragment_rating, new SectionTeacherRating()).commit();
        return inflater.inflate(Constants.PROFILE_INFORMATION,container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView imageView=(ImageView)view.findViewById(R.id.image_id);
        Picasso.with(view.getContext()).load("http://lorempixel.com/350/230/").into(imageView);
    }
}
