package wan.wanmarcos.activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import wan.wanmarcos.R;
import wan.wanmarcos.fragments.EventNewsFragment;
import wan.wanmarcos.fragments.TeacherListFragment;
import wan.wanmarcos.fragments.TeacherProfileFragment;
import wan.wanmarcos.managers.Communicator;
import wan.wanmarcos.models.Teacher;

public class HomeActivity extends AppCompatActivity implements Communicator{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        if(findViewById(R.id.home_fragment) != null){
            if(savedInstanceState != null){
                return;
            }
            EventNewsFragment eventNewsFragment= new EventNewsFragment();
            eventNewsFragment.setArguments(getIntent().getExtras());
            getFragmentManager().beginTransaction().add(R.id.home_fragment,eventNewsFragment).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
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
        if (id == R.id.action_settings) {
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

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void response(Teacher teacher) {
        TeacherProfileFragment profileFragment=new TeacherProfileFragment();
        try {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.home_fragment, profileFragment);
            transaction.addToBackStack("profilefragment");

            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
        profileFragment.changeData(teacher);

    }
}
