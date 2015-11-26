package wan.wanmarcos.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import wan.wanmarcos.R;
import wan.wanmarcos.managers.FragmentsMethods;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.views.adapters.ValuationPlaceListAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlaceProfileFragment extends Fragment implements FragmentsMethods{

    private RecyclerView recyclerViewTeacherCourses;
    private ValuationPlaceListAdapter valuationPlaceListAdapter;

    public PlaceProfileFragment() {
        // Required empty public constructor
        valuationPlaceListAdapter=new ValuationPlaceListAdapter();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(Constants.FRAGMENT_PROFILE_PLACE_LAYOUT, container, false);
        setUpElements(view);
        addListeners();
        return view;
    }


    @Override
    public void setUpElements(View view) {
        recyclerViewTeacherCourses=(RecyclerView)view.findViewById(R.id.comments_list);
        recyclerViewTeacherCourses.setAdapter(valuationPlaceListAdapter);
        recyclerViewTeacherCourses.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void addListeners() {

    }
}
