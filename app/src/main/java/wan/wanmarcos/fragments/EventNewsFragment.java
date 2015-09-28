package wan.wanmarcos.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import wan.wanmarcos.R;
import wan.wanmarcos.models.Event;
import wan.wanmarcos.views.adapters.EventNewsPagerAdapter;


public class EventNewsFragment extends Fragment {

    View view;
    ViewPager eventNewsPager;
    List<Event> events;

    public EventNewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_event_news, container, false);

        setUpElements();
        getNewsEvents();
        addListeners();

        return view;
    }

    private void setUpElements(){
        eventNewsPager = (ViewPager)view.findViewById(R.id.view_pager_event_news);
    }

    private void getNewsEvents(){
        events = new ArrayList<Event>();
        events.add(new Event("Eneisoft","http://www.eneisoft.org/img/comparte/eneisoft-twitter.jpg"));
        events.add(new Event("ACM ICPC","http://asmarterplanet.com/studentsfor/files/2013/05/ACM-Logo1.jpg"));
        events.add(new Event("Game Jam","http://www.gamejam.es/images/ggj_logo.png"));

        eventNewsPager.setAdapter(new EventNewsPagerAdapter((FragmentActivity)getActivity(),events,this));
    }

    private void addListeners(){

    }
}
