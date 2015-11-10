package wan.wanmarcos.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Debug;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
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
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import wan.wanmarcos.R;
import wan.wanmarcos.activities.EventsActivity;
import wan.wanmarcos.activities.MainActivity;
import wan.wanmarcos.models.Event;
import wan.wanmarcos.models.Session;
import wan.wanmarcos.utils.Builder;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.utils.DateAndTimeDealer;
import wan.wanmarcos.utils.Redirection.Redirect;
import wan.wanmarcos.utils.RestClient;
import wan.wanmarcos.views.adapters.EventListAdapter;

/**
 * Created by postgrado on 17/10/15.
 */
public class EventViewListFragment extends Fragment implements EventListAdapter.ClickListener{

    RestClient restClient;
    SharedPreferences preferences;
    Session session;

    private Builder builder;

    private Boolean received = true;

    private boolean userScrolled = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;

    private LinearLayoutManager mLayoutManager;

    private RecyclerView recyclerView;
    private EventListAdapter eventListAdapter;

    private Button btnNewEvent;

    FloatingActionButton suggestFAB;

    DateAndTimeDealer dateAndTimeDealer;

    private int currentPage=1;

    private View mLayout;


    public  EventViewListFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dateAndTimeDealer=new DateAndTimeDealer();
        restClient = new RestClient(getActivity());
        preferences = getActivity().getSharedPreferences(Constants.PREFERENCES, Context.MODE_PRIVATE);
        session = Session.getSession(preferences);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout =inflater.inflate(R.layout.fragment_events_list, container, false);
        setUpElements(layout);
        addListeners();
        mLayout=layout;
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
        suggestFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Redirect.getSingletonInstance().changeFragment(new Object());
                currentPage = 1;
            }
        });
    }

    public void getInitialData()
    {
        if(received == false)return;
        received = false;
        System.out.println("Initial Data");
        getEvents();
    }

    @Override
    public void itemClicked(View view, int position) {
        Redirect.getSingletonInstance().changeFragment(eventListAdapter.getItemAtPos(position));
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
        if(received == false)return;
        received = false;
        System.out.println("Lazy loading: " +currentPage);
        getEvents();
    }

    private void getEvents()
    {
        final List<Event> eventsList=new ArrayList<>();;
        Call<JsonElement> eventPage = restClient.getConsumerService().getEvents(session.getToken(), "", currentPage, 10);
        eventPage.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Response<JsonElement> response) {
                JsonObject responseBody = response.body().getAsJsonObject();
                if (responseBody.has("events")) {
                    mLayout.findViewById(R.id.loadingPanel).setVisibility(View.GONE);
                    JsonArray jsonArray = responseBody.getAsJsonArray("events");
                    for (int i = 0; i < jsonArray.size(); i++) {
                        JsonObject storedObject = jsonArray.get(i).getAsJsonObject();
                        Event current = new Event();
                        current.setEventId(storedObject.get("id").getAsInt());
                        current.setName(storedObject.get("title").getAsString());
                        Calendar startCal = dateAndTimeDealer.getInstance().turnMilisIntoCalendar((storedObject.get("starts_at").getAsLong()));
                        current.setStartDateTime(startCal);
                        Calendar endCal = dateAndTimeDealer.getInstance().turnMilisIntoCalendar((storedObject.get("ends_at").getAsLong()));
                        current.setFinishDateTime(endCal);
                        current.setImgUrl(storedObject.get("image").getAsString());
                        eventsList.add(current);
                    }
                } else {
                    if (responseBody.has("error")) {
                        wan.wanmarcos.models.Error error = builder.buildError(responseBody.get("error").getAsJsonObject());
                        Toast.makeText(getActivity(), "Error : " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
                eventListAdapter.addAll(eventsList);
                received = true;
                System.out.println("RECEIVED!!: "+currentPage);
                currentPage++;
            }

            @Override
            public void onFailure(Throwable t) {
            }
        });
    }

    @Override
    public void onResume() {
        Redirect.getSingletonInstance().setActivity((AppCompatActivity) getActivity(), R.id.home_fragment);
        super.onResume();
    }
}
