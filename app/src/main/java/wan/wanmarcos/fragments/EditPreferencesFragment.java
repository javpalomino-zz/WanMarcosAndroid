package wan.wanmarcos.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Calendar;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import wan.wanmarcos.R;
import wan.wanmarcos.models.Event;
import wan.wanmarcos.models.Preference;
import wan.wanmarcos.models.Session;
import wan.wanmarcos.utils.Builder;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.utils.DateAndTimeDealer;
import wan.wanmarcos.utils.Redirection.Redirect;
import wan.wanmarcos.utils.RestClient;
import wan.wanmarcos.views.adapters.EditPreferencesAdapter;
import wan.wanmarcos.views.adapters.EventListAdapter;
import wan.wanmarcos.views.adapters.PreferenceListAdapter;

/**
 * Created by Francisco on 1/12/2015.
 */
public class EditPreferencesFragment extends Fragment {
    RestClient restClient;
    SharedPreferences preferences;
    Session session;
    private Builder builder;
    private EditPreferencesAdapter editPreferencesAdapter;
    private ImageView addPreference;
    private View mLayout;
    private Boolean received = true;
    private boolean userScrolled = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    private LinearLayoutManager mLayoutManager;
    private RecyclerView recyclerView;
    private int currentPage;

    public  EditPreferencesFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        restClient = new RestClient(getActivity());
        preferences = getActivity().getSharedPreferences(Constants.PREFERENCES, Context.MODE_PRIVATE);
        session = Session.getSession(preferences);
        editPreferencesAdapter = new EditPreferencesAdapter(this);
        getInitialData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout =inflater.inflate(R.layout.fragment_profile_edit_preferences, container, false);
        setUpElements(layout);
        addListeners();
        mLayout=layout;
        return layout;
    }

    private void setUpElements(View layout)
    {
        recyclerView = (RecyclerView) layout.findViewById(R.id.faculties_list);
        recyclerView.setAdapter(editPreferencesAdapter);
        mLayoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        addPreference=(ImageView)layout.findViewById(R.id.add_faculty_button);
        builder = new Builder();
        addPreference.setVisibility(View.GONE);
    }

    private void addListeners()
    {
        addNewPreferenceListener();
    }
    private void addNewPreferenceListener()
    {
        addPreference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //show popup
            }
        });
    }

    public void getInitialData()
    {
        getData();
    }
    public void getData(){
        editPreferencesAdapter.add(new Preference("ESTA SECCION SE ENCUENTRA BAJO CONSTRUCCION",0));
    }
}
