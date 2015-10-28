package wan.wanmarcos.activities;

<<<<<<< HEAD
import android.app.FragmentTransaction;
=======

import android.support.v4.widget.DrawerLayout;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
>>>>>>> dev
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.R;
<<<<<<< HEAD
import wan.wanmarcos.fragments.EventNewsFragment;
import wan.wanmarcos.fragments.TeacherCourseProfileFragment;
import wan.wanmarcos.fragments.TeacherListFragment;
import wan.wanmarcos.fragments.TeacherProfileFragment;
import wan.wanmarcos.managers.Communicator;
import wan.wanmarcos.models.Course;
import wan.wanmarcos.models.Teacher;

public class HomeActivity extends AppCompatActivity implements Communicator{
=======
import wan.wanmarcos.fragments.NavigationDrawerFragment;

public class HomeActivity extends AppCompatActivity {

    private Toolbar toolbar;
    NavigationDrawerFragment drawerFragment;
>>>>>>> dev
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

<<<<<<< HEAD
        if(findViewById(R.id.home_fragment) != null){
            if(savedInstanceState != null){
                return;
            }
            EventNewsFragment eventNewsFragment= new EventNewsFragment();
            eventNewsFragment.setArguments(getIntent().getExtras());
            getFragmentManager().beginTransaction().add(R.id.home_fragment,eventNewsFragment).commit();
        }
=======
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        drawerFragment = (NavigationDrawerFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.SetUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);
>>>>>>> dev
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

<<<<<<< HEAD
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            toListTeachers();
=======
        switch (id){
            case R.id.action_settings : return true;
            case R.id.logout : logout();
>>>>>>> dev
        }

        return super.onOptionsItemSelected(item);
    }
<<<<<<< HEAD

    @Override
    public void toListTeachers() {
        TeacherListFragment teacherListFragment= new TeacherListFragment();
        try {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.home_fragment, teacherListFragment);
            transaction.addToBackStack("teacherFragment");

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void toProfileTeacher(Teacher teacher) {
        TeacherProfileFragment profileFragment=new TeacherProfileFragment();
        try {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.home_fragment, profileFragment);
            transaction.addToBackStack("profilefragment");

            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
        addTeacherInformation(teacher);

    }

    @Override
    public void toTeacherCourseInformation(Course course) {
        TeacherCourseProfileFragment teacherCourseProfileFragment=new TeacherCourseProfileFragment();
        try{
            FragmentTransaction transaction= getFragmentManager().beginTransaction();
            transaction.replace(R.id.home_fragment, teacherCourseProfileFragment);
            transaction.addToBackStack("profileteachercoursefragment");

            transaction.commit();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        addCourseInformation(course);
    }

    @Override
    public void addTeacherInformation(Teacher teacher) {
        dataTeacher.putString("teachername",teacher.getName());
        dataTeacher.putString("facultyname",teacher.getFaculties());
        dataTeacher.putString("imageurl",teacher.getImageUrl());
    }

    @Override
    public void addCourseInformation(Course course) {
        dataTeacher.putString("coursename",course.getName());
        dataTeacher.putFloat("courserating",course.getRating());
    }

    @Override
    public float getFloatInformation(String key) {
        return 0;
    }

    @Override
    public int getIntInformation(String key) {
        return 0;
    }

    @Override
    public String getStringInformation(String key) {
        if(dataTeacher.containsKey(key)){
            return dataTeacher.getString(key);
        }
        else{
            return null;
        }
=======
    private void logout(){
        SharedPreferences preferences = getSharedPreferences(Constants.PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =  preferences.edit();
        editor.clear();
        editor.commit();
        Intent login_activity = new Intent(this,MainActivity.class);
        finish();
        startActivity(login_activity);
>>>>>>> dev
    }
}
