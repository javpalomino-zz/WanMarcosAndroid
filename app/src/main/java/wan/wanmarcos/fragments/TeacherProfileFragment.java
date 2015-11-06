package wan.wanmarcos.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import wan.wanmarcos.R;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.utils.Redirection.Redirect;


public class TeacherProfileFragment extends Fragment {

    public TeacherProfileFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(Constants.FRAGMENT_PROFILE_TEACHER_LAYOUT, container, false);
        FrameLayout childFragContainer = (FrameLayout) view.findViewById(R.id.sub_fragment_teacher_list);
        FragmentManager fragmentManager=getChildFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        /*if(Redirect.getSingletonInstance().getFirst()){
            fragmentTransaction.replace(childFragContainer.getId(),new SectionListTeachers());
        }
        else{
            fragmentTransaction.add(childFragContainer.getId(),new SectionListTeachers());
        }
        fragmentTransaction.commit();*/
        return view;
    }
}
