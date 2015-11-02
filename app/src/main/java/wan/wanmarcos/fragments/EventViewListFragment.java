package wan.wanmarcos.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
        String[] description={"The showcase of inmortals","30 enter , just ONE leaves","The biggest Party Of the Summer","Who will be the soul survivir?"};
        String[] places={"Skydome" , "Thunderdome "," Barclays Center","Madison Square Garden"};
        int[] startYear={115,115,155,115};
        int[] startMonth={3,0,7,10};
        int[] startDay={1,25,23,22};
        int[] startHour={17,18,18,18};
        int[] startMinute={0,0,0,0};
        int[] startSecond={0,0,0,0};
        String[] startDates={"April 1st , 2015" , "January 25th , 2015" , "August 23rd , 2015" , "November 22nd , 2015"};
        String[] startTimes={"6:00 pm","7:00 pm","7:00 pm","7:00 pm"};

        for (int i=0;i<icons.length && i<titles.length;i++)
        {
            Event current = new Event();
            current.setIconId(icons[i]);
            current.setName(titles[i]);
            current.setDescription(description[i]);
            current.setReferencePlace(places[i]);
            Calendar cal = new GregorianCalendar();
            cal.set(startYear[i], startMonth[i], startDay[i], startHour[i], startMinute[i], startSecond[i]);
            current.setStartDateTime(cal);
            data.add(current);
        }
        return data;
    }

    @Override
    public void itemClicked(View view, int position) {
        EventsActivity.getInstance().toEventPage(eventListAdapter.getItemAtPos(position));

    }

}
