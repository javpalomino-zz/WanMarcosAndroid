package wan.wanmarcos.activities;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import wan.wanmarcos.R;
import wan.wanmarcos.fragments.NavigationDrawerFragment;

/**
 * Created by Francisco on 8/11/2015.
 */
public class PlacesActivity extends AppCompatActivity {

    private Toolbar toolbar;
    NavigationDrawerFragment drawerFragment;
    Menu createdMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);
        setUpNavDrawer();
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


}
