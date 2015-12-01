package wan.wanmarcos.activities;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;

import wan.wanmarcos.R;
import wan.wanmarcos.fragments.LoginFragment;
import wan.wanmarcos.fragments.RegisterFragment;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.utils.Redirection.Redirect;

public class MainActivity extends AppCompatActivity {
    public static Context context;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Constants.DEVICE_DENSITY=getResources().getDisplayMetrics().density;
        Constants.DEVICE_WIDTH=getResources().getDisplayMetrics().widthPixels;
        Constants.DEVICE_HEIGHT=getResources().getDisplayMetrics().heightPixels;
        DisplayMetrics metrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        Constants.DEVICE_HEIGHT2=metrics.heightPixels/Constants.DEVICE_DENSITY;
        Constants.DEVICE_WIDTH2=metrics.widthPixels/Constants.DEVICE_DENSITY;
        context = getApplicationContext();
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(Constants.PREFERENCES, Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token",null);
        if(token != null){
            Redirect.getSingelton().showActivity(this,HomeActivity.class);
        }else{
            if(findViewById(R.id.fragment_container) != null){
                if(savedInstanceState != null){
                    return;
                }
                LoginFragment loginFragment = new LoginFragment();
                loginFragment.setArguments(getIntent().getExtras());
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,loginFragment).commit();
        }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static Context getContext(){
        return context;
    }
    public static void setContext(Context newContext){
        context = newContext;
    }
}
