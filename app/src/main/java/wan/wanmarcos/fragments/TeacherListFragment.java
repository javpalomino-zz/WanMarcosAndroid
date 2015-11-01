package wan.wanmarcos.fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import wan.wanmarcos.R;
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
        Toast.makeText(getActivity(),Constants.DEVICE_DENSITY+"Density,"+Constants.DEVICE_HEIGHT2+",HEIGHT"+Constants.DEVICE_WIDTH2+",WIDTH",Toast.LENGTH_LONG).show();
        return inflater.inflate(Constants.FRAGMENT_LIST_TEACHER_LAYOUT, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
