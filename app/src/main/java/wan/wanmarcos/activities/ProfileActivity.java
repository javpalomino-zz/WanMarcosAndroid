package wan.wanmarcos.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.net.URI;

import wan.wanmarcos.R;
import wan.wanmarcos.fragments.NavigationDrawerFragment;
import wan.wanmarcos.fragments.PopUpEditInfoPer;
import wan.wanmarcos.fragments.PopupCommentFragment;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.utils.Redirection.Redirect;
import wan.wanmarcos.views.adapters.PopUpFragment;

public class ProfileActivity extends AppCompatActivity {

    private Toolbar toolbar;
    NavigationDrawerFragment drawerFragment;
    private int PICKIMAGE_RESULT_CODE = 100;
    private Uri imageUri;
    private PopUpFragment perInfo;
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
        Redirect.getSingelton().showFragment(this, Constants.PROFILE_CONTAINER, Constants.FRAGMENT_PROFILE);
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
            case R.id.action_logout:Redirect.getSingelton().logOut(this); break;
            case R.id.action_edit_photo:OpenGalery();break;
            case R.id.action_edit_prefs:;break;
            case R.id.action_edit_info:ShowInfoPopup();break;
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
    private void OpenGalery()
    {
        Intent galleryIntent = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, PICKIMAGE_RESULT_CODE);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(data!=null && resultCode != this.RESULT_CANCELED){
           if(PICKIMAGE_RESULT_CODE==resultCode)
           {

                    imageUri = data.getData();
                    ////llamar web service
            }
        }
    }
    public  void ShowInfoPopup()
    {
        FragmentManager fm = getSupportFragmentManager();
        PopUpEditInfoPer editInfoPer = new PopUpEditInfoPer();
        editInfoPer.show(fm, "fragment_edit_name");
    }

}
