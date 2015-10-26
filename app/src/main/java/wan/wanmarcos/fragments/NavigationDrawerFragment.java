package wan.wanmarcos.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import wan.wanmarcos.R;
import wan.wanmarcos.activities.EventsActivity;
import wan.wanmarcos.activities.HomeActivity;
import wan.wanmarcos.models.NavDrawerLink;
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
    }

    public static List<NavDrawerLink> getData()
    {
        List<NavDrawerLink> data=new ArrayList<>();
        int[] icons = {R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher};
        String[] titles={"Noticias","Docentes","Lugares","Eventos"};
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
        switch (position){
            case 0 :
                Toast.makeText(getActivity(), "A Noticias", Toast.LENGTH_SHORT);
                startActivity(new Intent(getActivity(), HomeActivity.class)) ;break;
            case 1:
                Toast.makeText(getActivity(), "A Docentes", Toast.LENGTH_SHORT);break;
            case 2:
                Toast.makeText(getActivity(),"A Lugares",Toast.LENGTH_SHORT);break;
            case 3:
                Toast.makeText(getActivity(),"A Eventos",Toast.LENGTH_SHORT);
                startActivity(new Intent(getActivity(), EventsActivity.class));break;
        }

    }
}
