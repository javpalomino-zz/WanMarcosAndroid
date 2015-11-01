package wan.wanmarcos.activities;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import wan.wanmarcos.R;
import wan.wanmarcos.fragments.NavigationDrawerFragment;
import wan.wanmarcos.fragments.RegisterFragment;
import wan.wanmarcos.fragments.SuggestedEventFragment;

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        createdMenu=menu;
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    public void setUpNavDrawer(){
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        drawerFragment = (NavigationDrawerFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.SetUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);


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

    public static EventsActivity getInstance() {
            return instance;
    }
}
