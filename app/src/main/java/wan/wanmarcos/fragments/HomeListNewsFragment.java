package wan.wanmarcos.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import wan.wanmarcos.R;
import wan.wanmarcos.managers.FragmentsMethods;
import wan.wanmarcos.models.Home;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.views.adapters.HomeListAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeListNewsFragment extends Fragment implements FragmentsMethods {
    private HomeListAdapter homeListAdapter;
    private RecyclerView recyclerView;

    public HomeListNewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(Constants.FRAGMENT_LIST_NEWS_LAYOUT, container, false);
        setUpElements(view);
        addListeners();
        return view;
    }


    @Override
    public void setUpElements(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.home_list);
        homeListAdapter = new HomeListAdapter(this);
        getData();
        recyclerView.setAdapter(homeListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    @Override
    public void addListeners() {

    }

    public void getData() {
        homeListAdapter.add(new Home(0,"carlos","casa","Teacher","http://lorempixel.com/g/400/200/"));
        homeListAdapter.add(new Home(0,"Toma de Facultad","chio","Event","http://lorempixel.com/g/400/200/"));
        homeListAdapter.add(new Home(0,"Chio","fisi","Place","http://lorempixel.com/g/400/200/"));
        homeListAdapter.add(new Home(0,"SImpe","may","Teacher","http://lorempixel.com/g/400/200/"));
        homeListAdapter.add(new Home(0,"FISITONAZO","lel","Event","http://lorempixel.com/g/400/200/"));
    }
}
