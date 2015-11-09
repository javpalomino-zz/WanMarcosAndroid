package wan.wanmarcos.fragments;


import android.os.Debug;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import wan.wanmarcos.R;
import wan.wanmarcos.activities.EventsActivity;
import wan.wanmarcos.models.Event;
import wan.wanmarcos.utils.Builder;
import wan.wanmarcos.utils.RestClient;
import wan.wanmarcos.views.adapters.EventListAdapter;

/**
 * Created by postgrado on 17/10/15.
 */
public class EventViewListFragment extends Fragment implements EventListAdapter.ClickListener{

    private RestClient restClient;
    private Builder builder;
    private String token = "Bearer {eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyIiwiaXNzIjoiaHR0cDpcL1wvNTIuODkuMTI0LjBcL2FwaVwvdjFcL2F1dGhlbnRpY2F0ZSIsImlhdCI6IjE0NDcwNDQ5OTYiLCJleHAiOiIxNDU1Njg0OTk2IiwibmJmIjoiMTQ0NzA0NDk5NiIsImp0aSI6IjU1NzgxZTZlMjdhNWE2MzY4MzJiYTkyMWVhNGE1MzQyIn0.BTfVAF4vNoTnFq7jDU4r_JrDKRO4MC4h28SOXPBty3I}";

    private boolean userScrolled = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;

    private LinearLayoutManager mLayoutManager;

    private RecyclerView recyclerView;
    private EventListAdapter eventListAdapter;

    private Button btnNewEvent;

    FloatingActionButton suggestFAB;

    private int currentPage=1;


    public  EventViewListFragment(){
        restClient = new RestClient();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        System.out.println("Se llamo al onCreate de EventViewListFragment");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        System.out.println("se llamo al onCreateView de EvENT List Fragment");
        // Inflate the layout for this fragment
        View layout =inflater.inflate(R.layout.fragment_events_list, container, false);
        setUpElements(layout);
        addListeners();
        return layout;
    }

    private void setUpElements(View layout)
    {
        recyclerView = (RecyclerView) layout.findViewById(R.id.eventList);
        eventListAdapter = new EventListAdapter(getActivity());
        eventListAdapter.setClickListener(this);
        getInitialData();
        recyclerView.setAdapter(eventListAdapter);
        mLayoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        suggestFAB = (FloatingActionButton)  layout.findViewById(R.id.suggestFAB);
        builder = new Builder();
    }

    private void addListeners()
    {
        addNewEventListener();
        addScrollBottomListener();
    }



    private void addNewEventListener()
    {
        /*btnNewEvent.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                EventsActivity.getInstance().toNewEventForm();
            }
        });*/

        suggestFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventsActivity.getInstance().toNewEventForm();
                currentPage=1;
            }
        });
    }

    public void getInitialData()
    {
        getEvents();
    }

    @Override
    public void itemClicked(View view, int position) {
        EventsActivity.getInstance().toEventPage(eventListAdapter.getItemAtPos(position));
        currentPage=1;

    }
    private void addScrollBottomListener() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    userScrolled = true;

                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx,
                                   int dy) {

                super.onScrolled(recyclerView, dx, dy);
                // Here get the child count, item count and visibleitems
                // from layout manager

                visibleItemCount = mLayoutManager.getChildCount();
                totalItemCount = mLayoutManager.getItemCount();
                pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();
                if (userScrolled && (visibleItemCount + pastVisiblesItems) == totalItemCount) {
                    userScrolled = false;

                    addNewElementsToList();
                }

            }

        });

    }

    private void addNewElementsToList()
    {
        getEvents();
    }

    private List<Event> getEvents()
    {
        System.out.println("iNITIAL ITEM COUNT = " + eventListAdapter.getItemCount());
        final List<Event> eventsList=new ArrayList<>();;
        Call<JsonElement> eventPage = restClient.getConsumerService().getEvents(token, "", currentPage, 10);
        eventPage.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Response<JsonElement> response) {
                JsonObject responseBody = response.body().getAsJsonObject();
                if (responseBody.has("events")) {
                    JsonArray jsonArray = responseBody.getAsJsonArray("events");
                    for (int i = 0; i < jsonArray.size(); i++) {
                        JsonObject storedObject = jsonArray.get(i).getAsJsonObject();
                        Event current = new Event();
                        current.setEventId(storedObject.get("id").getAsInt());
                        current.setName(storedObject.get("title").getAsString());
                        Calendar startCal = new GregorianCalendar();
                        startCal.setTimeInMillis((storedObject.get("starts_at").getAsLong()) * 1000);
                        current.setStartDateTime(startCal);
                        Calendar endCal = new GregorianCalendar();
                        endCal.setTimeInMillis((storedObject.get("ends_at").getAsLong()) * 1000);
                        current.setFinishDateTime(endCal);
                        current.setImgUrl(storedObject.get("image").getAsString());
                        eventsList.add(current);
                    }
                } else {
                    if (responseBody.has("error")) {
                        System.out.println("ERROR");
                        wan.wanmarcos.models.Error error = builder.buildError(responseBody.get("error").getAsJsonObject());
                        Toast.makeText(getActivity(), "Error : " + error.toString(), Toast.LENGTH_SHORT).show();
                    } else {

                    }
                }
                System.out.println("EventList Size = " + eventsList.size());
                eventListAdapter.addAll(eventsList);
                System.out.println("FINAL ITEM COUNT = " + eventListAdapter.getItemCount());
            }

            @Override
            public void onFailure(Throwable t) {
            }
        });

        currentPage++;
        return eventsList;
    }
}
