package wan.wanmarcos.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import wan.wanmarcos.R;
import wan.wanmarcos.utils.Redirection.Redirect;
import wan.wanmarcos.utils.Redirection.Redirection;
import wan.wanmarcos.utils.Constants;

public class SectionTeacherProfile extends Fragment {

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
        //Redirect.getSingletonInstance().setContent(this,Constants.PROFILE_TEACHER_TITLE,new SectionTeacherTitle());
        //Redirect.getSingletonInstance().setContent(this,Constants.PROFILE_TEACHER_RATING,new SectionTeacherRating());
        View view=inflater.inflate(Constants.PROFILE_TEACHER_INFORMATION,container,false);
        setUpElements(view);
        return view;
    }
    public void setUpElements(View view){
        //ImageView imageView=(ImageView) view.findViewById(R.id.teacher_image);
        //Picasso.with(view.getContext()).load(Redirect.getSingletonInstance().getInformation("teacherimage")).into(/mageView);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}


