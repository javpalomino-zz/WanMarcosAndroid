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
import wan.wanmarcos.managers.Communicator;
import wan.wanmarcos.models.Teacher;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.views.widgets.CircleTransform;

public class SectionTeacherProfile extends Fragment {
    private Communicator communicator;

    public SectionTeacherProfile(){

    }
    public static final SectionTeacherProfile newInstance(){
        return new SectionTeacherProfile();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(Constants.PROFILE_TEACHER_INFORMATION,container,false);
        setUpElements(view);
        return view;
    }
    public void setUpElements(View view){
        communicator=(Communicator)getActivity();
        ImageView imageView=(ImageView) view.findViewById(R.id.teacher_image);
        Picasso.with(view.getContext()).load(communicator.getStringInformation("imageurl")).into(imageView);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}


