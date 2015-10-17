package wan.wanmarcos.fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import java.util.ArrayList;

import wan.wanmarcos.R;
import wan.wanmarcos.managers.Communicator;
import wan.wanmarcos.models.Teacher;
import wan.wanmarcos.utils.Constants;



public class TeacherListFragment extends Fragment {
    public TeacherListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getFragmentManager().beginTransaction().add(R.id.sub_fragment_teacher_list,new SectionListTeachers()).commit();
        return inflater.inflate(Constants.FRAGMENT_LIST_TEACHER_LAYOUT, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
