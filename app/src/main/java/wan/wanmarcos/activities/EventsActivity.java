package wan.wanmarcos.activities;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import wan.wanmarcos.R;
import wan.wanmarcos.fragments.EventPageFragment;
import wan.wanmarcos.fragments.EventViewListFragment;
import wan.wanmarcos.fragments.NavigationDrawerFragment;
import wan.wanmarcos.fragments.SuggestedEventFragment;
import wan.wanmarcos.models.Event;

public class EventsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    NavigationDrawerFragment drawerFragment;
    Menu createdMenu;

    private static EventsActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        instance=this;
        setUpNavDrawer();
        addListFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        createdMenu=menu;
        //getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    public void setUpNavDrawer(){
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        drawerFragment = (NavigationDrawerFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.SetUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);


    }

    private void addListFragment()
    {
        EventViewListFragment eventViewListFragment = new EventViewListFragment();
        try {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.add(R.id.eventsContainer, eventViewListFragment);
            transaction.commit();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void toNewEventForm()
    {
        SuggestedEventFragment suggestedEventFragment = new SuggestedEventFragment();
        try {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.eventsContainer, suggestedEventFragment);
            transaction.addToBackStack("eventViewListFragment");
            transaction.commit();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void toEventPage(Event selectedEvent)
    {
        EventPageFragment eventPageFragment = new EventPageFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("selectedEvent", selectedEvent);
        eventPageFragment.setArguments(bundle);

        try {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.eventsContainer, eventPageFragment);
            transaction.addToBackStack("eventViewListFragment");
            transaction.commit();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static EventsActivity getInstance() {
            return instance;
    }
}
