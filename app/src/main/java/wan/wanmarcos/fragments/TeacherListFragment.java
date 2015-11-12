package wan.wanmarcos.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.SearchView;
import android.widget.Toast;


import wan.wanmarcos.R;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.utils.Redirection.Redirect;


public class TeacherListFragment extends Fragment {
    private SearchView searchView;
    public TeacherListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(Constants.FRAGMENT_LIST_TEACHER_LAYOUT, container, false);
        setUpElements(view);
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.sub_fragment_teacher_list,new SectionListTeachers());
        fragmentTransaction.commit();
        //Redirect.getSingelton().setContent(this, Constants.TEACHER_LIST_ID, new SectionListTeachers());
        return view;
    }
    public void setUpElements(View view){
        searchView=(SearchView)view.findViewById(R.id.searchViewTeachers);
        searchView.setFocusable(false);

    }
}
