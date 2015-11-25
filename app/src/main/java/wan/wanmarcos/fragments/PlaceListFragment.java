package wan.wanmarcos.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import wan.wanmarcos.R;
import wan.wanmarcos.managers.FragmentsMethods;
import wan.wanmarcos.utils.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlaceListFragment extends Fragment implements FragmentsMethods{

    private Button buttonMap;
    private Button buttonList;

    public PlaceListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(Constants.FRAGMENT_LIST_TEACHER_LAYOUT, container, false);
        setUpElements(view);
        addListeners();
        return view;
    }


    @Override
    public void setUpElements(View view) {
        buttonList=(Button)view.findViewById(R.id.place_button_list);
        buttonMap=(Button)view.findViewById(R.id.place_button_map);
    }

    @Override
    public void addListeners() {
        buttonMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        buttonList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    //TODO
    //http://developer.android.com/intl/es/reference/android/support/v4/app/FragmentTabHost.html
}
