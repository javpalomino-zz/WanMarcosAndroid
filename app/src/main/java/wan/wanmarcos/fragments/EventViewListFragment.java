package wan.wanmarcos.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import wan.wanmarcos.R;
import wan.wanmarcos.activities.EventsActivity;
import wan.wanmarcos.models.Event;
import wan.wanmarcos.views.adapters.EventListAdapter;

/**
 * Created by postgrado on 17/10/15.
 */
public class EventViewListFragment extends Fragment implements EventListAdapter.ClickListener{

    private RecyclerView recyclerView;
    private EventListAdapter eventListAdapter;

    private Button btnNewEvent;

    private FragmentActivity myContext;

    public  EventViewListFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout =inflater.inflate(R.layout.fragment_events_list, container, false);
        setUpElements(layout);
        addListeners();
        return layout;
    }

    private void setUpElements(View layout)
    {
        recyclerView = (RecyclerView) layout.findViewById(R.id.eventList);
        eventListAdapter = new EventListAdapter(getActivity(),getData());
        eventListAdapter.setClickListener(this);
        recyclerView.setAdapter(eventListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        btnNewEvent =(Button)layout.findViewById(R.id.newEventButton);
    }

    private void addListeners()
    {
        addNewEventListener();
    }

    private void addNewEventListener()
    {
        btnNewEvent.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                EventsActivity.getInstance().toNewEventForm();
            }
        });
    }

    public static List<Event> getData()
    {
        List<Event> data=new ArrayList<>();

        int[] icons = {R.mipmap.wm17,R.mipmap.royalrumble,R.mipmap.summerslam,R.mipmap.survivorseries};
        String[] titles={"Wrestlemania","Royal Rumble","SummerSlam","Survivor Series"};
        String[] startDates={"April 1st , 2015" , "January 25th , 2015" , "August 23rd , 2015" , "November 22nd , 2015"};
        String[] startTimes={"6:00 pm","7:00 pm","7:00 pm","7:00 pm"};

        for (int i=0;i<icons.length && i<titles.length;i++)
        {
            Event current = new Event();
            current.setIconId(icons[i]);
            current.setName(titles[i]);
            current.setStartDate(startDates[i]);
            current.setStartTime(startTimes[i]);
            data.add(current);
        }
        return data;
    }

    @Override
    public void itemClicked(View view, int position) {
        switch (position){
            case 0 :break;
            case 1:break;
            case 2:break;
            case 3:break;
        }

    }

}
