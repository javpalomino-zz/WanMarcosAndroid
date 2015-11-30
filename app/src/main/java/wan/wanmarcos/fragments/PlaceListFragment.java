package wan.wanmarcos.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import wan.wanmarcos.R;
import wan.wanmarcos.managers.FragmentsMethods;
import wan.wanmarcos.managers.ItemAdapterListener;
import wan.wanmarcos.models.Place;
import wan.wanmarcos.utils.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlaceListFragment extends Fragment implements FragmentsMethods{
    private boolean flagList;
    private boolean flagMap;
    private Button buttonMap;
    private Button buttonList;
    private Place_SectionListFragment place_sectionListFragment;
    private Place_SectionMap place_sectionMap;

    public PlaceListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(Constants.FRAGMENT_LIST_PLACE_LAYOUT, container, false);
        setUpElements(view);
        addListeners();
        return view;
    }


    @Override
    public void setUpElements(View view) {
        flagList=true;
        flagMap=true;
        buttonList=(Button)view.findViewById(R.id.place_button_list);
        buttonMap=(Button)view.findViewById(R.id.place_button_map);
        place_sectionListFragment=new Place_SectionListFragment();
        place_sectionMap=new Place_SectionMap();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(R.id.tabbedFragment,place_sectionListFragment,Place_SectionListFragment.class.getName());
        ft.add(R.id.tabbedFragment, place_sectionMap, Place_SectionMap.class.getName());
        ft.commit();
        showMap();
    }

    @Override
    public void addListeners() {
        buttonMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMap();
            }
        });
        buttonList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showList();
            }
        });
    }

    private void showMap(){
        if(flagMap){
            flagList=true;
            FragmentTransaction fm = getFragmentManager().beginTransaction();
            fm.hide(place_sectionListFragment).show(place_sectionMap);
            fm.commit();
            flagMap=false;
        }

    }
    private void showList(){
        if(flagList){
            flagMap=true;
            FragmentTransaction fm = getFragmentManager().beginTransaction();
            fm.hide(place_sectionMap).show(place_sectionListFragment);
            fm.commit();
            flagList=false;
        }
    }
}
