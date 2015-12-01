package wan.wanmarcos.activities;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.inputmethod.InputMethodManager;

import wan.wanmarcos.R;
import wan.wanmarcos.fragments.EventPageFragment;
import wan.wanmarcos.fragments.EventViewListFragment;
import wan.wanmarcos.fragments.NavigationDrawerFragment;
import wan.wanmarcos.fragments.SuggestedEventFragment;
import wan.wanmarcos.models.Event;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.utils.Redirection.Redirect;

public class EventsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    NavigationDrawerFragment drawerFragment;
    Menu createdMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        Redirect.getSingelton().showFragment(this, Constants.EVENT_CONTAINER,Constants.FRAGMENT_LIST_EVENT);
        setUpNavDrawer();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        createdMenu=menu;
        return true;
    }

    public void setUpNavDrawer(){
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setBackgroundColor(toolbar, R.attr.colorPrimary);
        drawerFragment = (NavigationDrawerFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.SetUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);
    }
    private void setBackgroundColor(Toolbar toolbar,int resID)
    {
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(resID, typedValue, true);
        int color = typedValue.data;
        toolbar.setBackgroundColor(color);
    }
}
