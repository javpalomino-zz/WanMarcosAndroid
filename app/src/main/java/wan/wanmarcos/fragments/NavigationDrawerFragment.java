package wan.wanmarcos.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import wan.wanmarcos.R;
import wan.wanmarcos.activities.HomeActivity;
import wan.wanmarcos.models.Session;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.utils.Redirection.Redirect;
import wan.wanmarcos.activities.EventsActivity;
import wan.wanmarcos.models.NavDrawerLink;
import wan.wanmarcos.utils.RestClient;
import wan.wanmarcos.utils.Storage;
import wan.wanmarcos.views.adapters.NavDrawerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment implements NavDrawerAdapter.ClickListener {

    private RecyclerView recyclerView;

    public static final String PREF_FILE_NAME="testpref";
    public static final String KEY_USER_LEARNED_DRAWER="user_learned_drawer";

    private  boolean mUserLearnedDrawer;
    private boolean mFromSavedInstanceState;

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;

    private NavDrawerAdapter adapter;
    private View containerView;
    private RestClient restClient;

    private ImageView headerBackground;
    private TextView profileName;
    private TextView profileEmail;
    private ImageView profileImage;
    private String token="Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI1IiwiaXNzIjoiaHR0cDpcL1wvNTIuODkuMTI0LjBcL2FwaVwvdjFcL2F1dGhlbnRpY2F0ZSIsImlhdCI6IjE0NDcxMDQ5MzQiLCJleHAiOiIxNDU1NzQ0OTM0IiwibmJmIjoiMTQ0NzEwNDkzNCIsImp0aSI6IjcxZjM2NjgwN2EwZTIyZTY1ODM0OWYzZDMyOTcxNDQ1In0.gQK_MjKSRx6BhVCsy0CyhvJTEZB-wK2EWvKKJrDpUm4";

    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mUserLearnedDrawer=Boolean.valueOf(ReadFromPreferences(getActivity(),KEY_USER_LEARNED_DRAWER,"false"));
        if(savedInstanceState!=null){
            mFromSavedInstanceState=true;
        }
        restClient=new RestClient(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout =inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        setUpElements(layout);
        return layout;
    }


    private void setUpElements(View layout)
    {
        recyclerView = (RecyclerView) layout.findViewById(R.id.drawerList);
        adapter = new NavDrawerAdapter(getActivity(),getData());
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        headerBackground = (ImageView)layout.findViewById(R.id.nav_drawer_profile_background);
        Picasso.with(getActivity()).load(R.mipmap.backgroundprofile).fit().centerCrop().into(headerBackground);
        profileImage = (ImageView)layout.findViewById(R.id.nav_drawer_profile_image);
        profileName = (TextView)layout.findViewById(R.id.nav_drawer_profile_name);
        profileEmail = (TextView)layout.findViewById(R.id.nav_drawer_profile_email);
        Call<JsonElement> jsonElementCall=restClient.getConsumerService().me(Session.getSession(getActivity().getSharedPreferences(Constants.PREFERENCES, Context.MODE_PRIVATE)).getToken());
        jsonElementCall.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Response<JsonElement> response, Retrofit retrofit) {
                JsonObject jsonObject=response.body().getAsJsonObject();
                profileEmail.setText(jsonObject.get("email").getAsString());
                profileName.setText(jsonObject.get("first_name").getAsString()+" "+jsonObject.get("last_name").getAsString());
                ColorGenerator generator = ColorGenerator.MATERIAL;
                int color = generator.getColor(profileName.getText().charAt(0));
                TextDrawable.IBuilder builder = TextDrawable.builder().round();
                TextDrawable textDrawable = builder.build(profileName.getText().toString().charAt(0)+"", color);
                profileImage.setImageDrawable(textDrawable);
            }

            @Override
            public void onFailure(Throwable t) {
            }
        });
    }

    public static List<NavDrawerLink> getData()
    {
        List<NavDrawerLink> data=new ArrayList<>();
        int[] icons = {R.mipmap.ic_view_list_black_48dp,R.mipmap.ic_school_black_48dp,R.mipmap.ic_map_black_48dp,R.mipmap.ic_event_note_black_48dp,R.mipmap.ic_account_circle_black_48dp,R.mipmap.ic_comment_black_48dp};
        String[] titles={"Noticias","Docentes","Lugares","Eventos","Mi Perfil","Contáctanos"};
        for (int i=0;i<icons.length && i<titles.length;i++)
        {
            NavDrawerLink current = new NavDrawerLink();
            current.setIconId(icons[i]);
            current.setTitle(titles[i]);
            data.add(current);
        }
        return data;
    }

    public void SetUp(int fragmentId,DrawerLayout drawerLayout , Toolbar toolbar) {
        containerView = getActivity().findViewById(fragmentId);
        mDrawerLayout=drawerLayout;
        addListeners(toolbar);
        if(!mUserLearnedDrawer && !mFromSavedInstanceState)
        {
            mDrawerLayout.openDrawer(containerView);
        }
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }

    private void addListeners(Toolbar toolbar)
    {
        addToolbarToggleListener(toolbar);

    }

    private void addToolbarToggleListener(Toolbar toolbar)
    {
        mDrawerToggle= new ActionBarDrawerToggle(getActivity(),mDrawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if(!mUserLearnedDrawer)
                {
                    mUserLearnedDrawer=true;
                    SaveToPreferences(getActivity(),KEY_USER_LEARNED_DRAWER,mUserLearnedDrawer+"");
                }
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }
        };
    }
    public static void SaveToPreferences(Context context , String preferenceName , String preferenceValue){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(preferenceName,preferenceValue);
        editor.commit();
    }

    public static String ReadFromPreferences(Context context, String preferenceName, String defaultValue){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME , context.MODE_PRIVATE);
        return sharedPreferences.getString(preferenceName,defaultValue);
    }

    @Override
    public void itemClicked(View view, int position) {

        String activityExecute="";

        switch (position){
            case 0 :
                activityExecute=Constants.HOME_ACTIVITY; break;
            case 1:
                activityExecute=Constants.TEACHER_ACTIVITY;break;

            case 2:
                activityExecute=Constants.PLACE_ACTIVITY;break;
            case 3:
                activityExecute=Constants.EVENT_ACTIVITY; break;
            case 4:
                activityExecute=Constants.PROFILE_ACTIVITY;
                break;
            case 5:
                activityExecute=Constants.CONTACT_ACTIVITY;break;
        }
        mDrawerLayout.closeDrawers();
        Storage.getSingelton().clearActivityData(this);
        Redirect.getSingelton().showActivity(this, activityExecute);
    }
}
