package wan.wanmarcos.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.sql.SQLOutput;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import wan.wanmarcos.R;
import wan.wanmarcos.fragments.NavigationDrawerFragment;
import wan.wanmarcos.fragments.PopUpEditInfoPer;
import wan.wanmarcos.fragments.PopupCommentFragment;
import wan.wanmarcos.fragments.ProfileUserFragment;
import wan.wanmarcos.models.Session;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.utils.Modal;
import wan.wanmarcos.utils.Redirection.Redirect;
import wan.wanmarcos.utils.RestClient;
import wan.wanmarcos.utils.UriManager;
import wan.wanmarcos.views.adapters.PopUpFragment;

public class ProfileActivity extends AppCompatActivity {
    private int PICKIMAGE_RESULT_CODE = 100;
    private Uri imageUri;


    private Toolbar toolbar;
    NavigationDrawerFragment drawerFragment;
    private PopUpFragment perInfo;
    RestClient restClient;
    private SharedPreferences preferences;
    private Session session;
    private String token;
    private boolean recieved;
    private ProgressDialog progressDialog;
    private Modal modal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        MainActivity.setContext(getApplicationContext());
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setBackgroundColor(toolbar, R.attr.colorPrimary);
        drawerFragment = (NavigationDrawerFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.SetUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);
        recieved=true;
        preferences = getSharedPreferences(Constants.PREFERENCES, Context.MODE_PRIVATE);
        session = Session.getSession(preferences);
        token = Constants.HEADER+session.getToken();
        restClient=new RestClient();
        Redirect.getSingelton().showFragment(this, Constants.PROFILE_CONTAINER, Constants.FRAGMENT_PROFILE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_logout:Redirect.getSingelton().logOut(this); break;
            case R.id.action_edit_photo:OpenGalery();break;
            case R.id.action_edit_prefs:;GotoEditPrf();break;
            case R.id.action_edit_info:ShowInfoPopup();break;
        }

        return super.onOptionsItemSelected(item);
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
           if(PICKIMAGE_RESULT_CODE==requestCode)
           {
               imageUri = data.getData();
               File image = new File(UriManager.getPathImage(imageUri,this));
               if(recieved){
                   uploadPhoto(image);
                   Redirect.getSingelton().showFragment(this, Constants.PROFILE_CONTAINER, Constants.FRAGMENT_PROFILE);
                   progressDialog = new ProgressDialog(this);
                   progressDialog.setMessage("Actualizando Foto De Perfil");
                   progressDialog.setCancelable(false);
                   progressDialog.show();
               }
            }
        }
    }
    public void uploadPhoto(final File image){
        recieved=false;
        RequestBody imageFile = RequestBody.create(MediaType.parse("image/*"),image);
        Call<JsonElement> uploadPhoto = restClient.getConsumerService().changeProfilePhoto(token,imageFile);
        uploadPhoto.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Response<JsonElement> response, Retrofit retrofit) {
                if(!response.isSuccess()){
                    try {
                        System.out.println(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    progressDialog.dismiss();
                    UpdatePhotoToProfileHeader(image);
                    UpdatePhotoToNavDrawer(image);
                    Toast.makeText(getBaseContext(),"Foto de perfil actulizada",Toast.LENGTH_SHORT).show();
                }
                recieved = true;
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println(t.toString());
                progressDialog.dismiss();
                modal.buildModal(Constants.MODAL_TITLE_ERROR_PHOTO,Constants.MODAL_MESSAGE_ERROR_PHOTO,Constants.MODAL_ERROR_PHOTO_BUTTON_OK,true);
                modal.showModal();
            }
        });

    }
    public  void ShowInfoPopup()
    {
        FragmentManager fm = getSupportFragmentManager();
        PopUpEditInfoPer editInfoPer = new PopUpEditInfoPer();
        editInfoPer.show(fm, "fragment_edit_name");
    }

    private void setBackgroundColor(Toolbar toolbar,int resID)
    {
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(resID, typedValue, true);
        int color = typedValue.data;
        toolbar.setBackgroundColor(color);
    }


    public void UpdatePhotoToProfileHeader(File image)
    {
        System.out.println("DENTRO DE ACTIVITY");
        ProfileUserFragment fragment = (ProfileUserFragment) getSupportFragmentManager().findFragmentById(R.id.profile_fragment);
        fragment.UpdatePhoto(image);
    }
    public void UpdatePhotoToNavDrawer(File image)
    {
        System.out.println("DENTRO DE ACTIVITY");
        NavigationDrawerFragment fragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        fragment.UpdatePhoto(image);
    }
    public void GotoEditPrf()
    {
        Redirect.getSingelton().showFragment(this, Constants.PROFILE_CONTAINER, Constants.FRAGMENT_EDIT_PREFERENCE);
    }

}
