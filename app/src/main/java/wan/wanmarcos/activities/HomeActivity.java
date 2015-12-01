package wan.wanmarcos.activities;


import android.app.FragmentTransaction;


import android.support.v4.widget.DrawerLayout;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;

import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.R;

import wan.wanmarcos.fragments.TeacherCourseProfileFragment;
import wan.wanmarcos.fragments.TeacherListFragment;
import wan.wanmarcos.fragments.TeacherProfileFragment;
import wan.wanmarcos.utils.Redirection.Redirect;
import wan.wanmarcos.utils.Redirection.Redirection;
import wan.wanmarcos.models.Course;
import wan.wanmarcos.fragments.NavigationDrawerFragment;
import wan.wanmarcos.models.Teacher;
import wan.wanmarcos.utils.Storage;

public class HomeActivity extends AppCompatActivity {
    private Toolbar toolbar;
    NavigationDrawerFragment drawerFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        MainActivity.setContext(getApplicationContext());
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setBackgroundColor(toolbar, R.attr.colorPrimary);
        drawerFragment = (NavigationDrawerFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.SetUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);
        Redirect.getSingelton().showFragment(this, Constants.HOME_CONTAINER, Constants.FRAGMENT_LIST_NEWS);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        switch (id){
            case R.id.logout : Redirect.getSingelton().logOut(this); break;
        }

        return super.onOptionsItemSelected(item);
    }
    private void setBackgroundColor(Toolbar toolbar,int resID)
    {
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(resID, typedValue, true);
        int color = typedValue.data;
        toolbar.setBackgroundColor(color);
    }
}
