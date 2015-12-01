package wan.wanmarcos.fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

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
    private TextView textMap;
    private TextView textList;
    private View mapLine;
    private View listLine;
    private Place_SectionListFragment place_sectionListFragment;
    private Place_SectionMap place_sectionMap;
    private View mView;

    public PlaceListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(Constants.FRAGMENT_LIST_PLACE_LAYOUT, container, false);
        mView=view;
        setUpElements(view);
        addListeners();
        return view;
    }


    @Override
    public void setUpElements(View view) {
        flagList=true;
        flagMap=true;
        textList=(TextView)view.findViewById(R.id.place_button_list);
        textMap=(TextView)view.findViewById(R.id.place_button_map);
        setBackgroundColor(textList,R.attr.colorPrimary);
        setBackgroundColor(textMap, R.attr.colorPrimary);
        mapLine=view.findViewById(R.id.map_active_line);
        mapLine.bringToFront();
        listLine=view.findViewById(R.id.list_active_line);
        listLine.bringToFront();
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
        textMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMap();
            }
        });
        textList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showList();
            }
        });
    }

    private void setMapActive()
    {
        listLine.setVisibility(View.INVISIBLE);
        setTextColor(textList, R.color.tabUnselectedTextColor);
        mapLine.setVisibility(View.VISIBLE);
        setTextColor(textMap,R.color.pureWhite);
    }
    private void setListActive()
    {
        mapLine.setVisibility(View.INVISIBLE);
        setTextColor(textMap, R.color.tabUnselectedTextColor);
        listLine.setVisibility(View.VISIBLE);
        setTextColor(textList, R.color.pureWhite);
    }

    private void setBackgroundColor(TextView textView,int resID)
    {
        TypedValue typedValue = new TypedValue();
        getActivity().getTheme().resolveAttribute(resID, typedValue, true);
        int color = typedValue.data;
        textView.setBackgroundColor(color);
    }

    private void setTextColor(TextView textView, int resID)
    {
        textView.setTextColor(getResources().getColor(resID));
    }

    private void showMap(){
        if(flagMap){
            setMapActive();
            flagList=true;
            FragmentTransaction fm = getFragmentManager().beginTransaction();
            fm.hide(place_sectionListFragment).show(place_sectionMap);
            fm.commit();
            flagMap=false;
        }

    }
    private void showList(){
        if(flagList){
            setListActive();
            flagMap=true;
            FragmentTransaction fm = getFragmentManager().beginTransaction();
            fm.hide(place_sectionMap).show(place_sectionListFragment);
            fm.commit();
            flagList=false;
        }
    }
}
