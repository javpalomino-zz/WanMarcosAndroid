package wan.wanmarcos.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import wan.wanmarcos.R;
import wan.wanmarcos.managers.FragmentsMethods;
import wan.wanmarcos.models.Place;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.views.adapters.PlaceListAdapter;


public class Place_SectionListFragment extends Fragment implements FragmentsMethods{
    private RecyclerView recyclerView;
    private PlaceListAdapter placeListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        placeListAdapter=new PlaceListAdapter(getActivity());
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(Constants.FRAGMENT_LIST_PLACE_LAYOUT, container, false);
        setUpElements(view);
        addListeners();
        return view;
    }

    @Override
    public void setUpElements(View view) {
        recyclerView=(RecyclerView)view.findViewById(R.id.places_list);
        addPlaces();
        recyclerView.setAdapter(placeListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void addPlaces() {
        placeListAdapter.add(new Place("http://lorempixel.com/400/200/",0,"Tortitas Fritas","Al frente de chio",34,(float)4.5,"Rica comida :3"));
        placeListAdapter.add(new Place("http://lorempixel.com/400/200/",1,"Bebidas Personales","Encima de la loma",25,(float)2.5,"Ambiente comodo"));
        placeListAdapter.add(new Place("http://lorempixel.com/400/200/",2,"Desarrollo personal","Odonto",100,(float)1.0,"o thi"));
        placeListAdapter.add(new Place("http://lorempixel.com/400/200/",2,"Desarrollo personal","Odonto",10,(float)1.0,"o thi"));
        placeListAdapter.add(new Place("http://lorempixel.com/400/200/",2,"Desarrollo personal","Odonto",130,(float)1.0,"o thi"));
        placeListAdapter.add(new Place("http://lorempixel.com/400/200/",2,"Desarrollo personal","Odonto",10000000,(float)1.0,"o thi"));
    }

    @Override
    public void addListeners() {

    }
}
