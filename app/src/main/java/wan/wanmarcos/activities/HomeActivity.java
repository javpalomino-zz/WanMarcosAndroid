package wan.wanmarcos.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import wan.wanmarcos.R;
import wan.wanmarcos.utils.Constants;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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

        switch (id){
            case R.id.action_settings : Contactanos();break;
            case R.id.logout : logout(); break;
        }

        return super.onOptionsItemSelected(item);
    }
    private void logout(){
        SharedPreferences preferences = getSharedPreferences(Constants.PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =  preferences.edit();
        editor.clear();
        editor.commit();
        Intent login_activity = new Intent(this,MainActivity.class);
        finish();
        startActivity(login_activity);
    }

    private void Contactanos(){
        Intent contactanos_activity = new Intent(this,ContactanosActivity.class);
        startActivity(contactanos_activity);
    }
}
