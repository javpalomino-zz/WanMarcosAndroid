package wan.wanmarcos.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import wan.wanmarcos.R;
import wan.wanmarcos.managers.FragmentsMethods;
import wan.wanmarcos.models.Home;
import wan.wanmarcos.models.Session;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.utils.RestClient;
import wan.wanmarcos.views.adapters.HomeListAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeListNewsFragment extends Fragment implements FragmentsMethods {
    private static final String JSON_TEACHER = "professor";
    private static final String JSON_EVENT = "event";
    private static final String JSON_PLACE = "place";
    private HomeListAdapter homeListAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManagerRecyclerView;
    private RestClient restClient;
    private int current_page;
    public HomeListNewsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        current_page=1;
        restClient=new RestClient(getActivity());
        homeListAdapter=new HomeListAdapter(this);
        getData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(Constants.FRAGMENT_LIST_NEWS_LAYOUT, container, false);
        getActivity().setTitle("Noticias");
        setUpElements(view);
        addListeners();
        return view;
    }


    @Override
    public void setUpElements(View view) {
        layoutManagerRecyclerView=new LinearLayoutManager(getActivity());
        recyclerView = (RecyclerView) view.findViewById(R.id.home_list);
        recyclerView.setAdapter(homeListAdapter);
        recyclerView.setLayoutManager(layoutManagerRecyclerView);

    }

    @Override
    public void addListeners() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int state = 0;
            private int i = 0;
            private boolean changeState = false;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState != state) {
                    changeState = true;
                    if (changeState ) {
                        if(state==0){
                            if(layoutManagerRecyclerView.findLastCompletelyVisibleItemPosition() == homeListAdapter.getItemCount() - 1 && layoutManagerRecyclerView.findLastCompletelyVisibleItemPosition() > 0) {
                                getData();
                            }
                        }
                    }
                    state = newState;
                }
            }
        });
    }

    public void getData() {
        Call<JsonElement> logInUser = restClient.getConsumerService().getNews(Session.getSession(this.getActivity().getSharedPreferences(Constants.PREFERENCES, Context.MODE_PRIVATE)).getToken(),current_page);
        logInUser.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Response<JsonElement> response, Retrofit retrofit) {
                JsonObject responseBody = response.body().getAsJsonObject();
                if (responseBody.has(JSON_TEACHER)&&responseBody.has(JSON_EVENT)&&responseBody.has(JSON_PLACE)) {
                    JsonObject storedObject;
                    Home current;
                    if(!responseBody.get(JSON_EVENT).isJsonNull()){
                        storedObject = responseBody.getAsJsonObject(JSON_EVENT);
                        current= new Home(storedObject,JSON_EVENT);
                        homeListAdapter.add(current);
                    }
                    if (!responseBody.get(JSON_PLACE).isJsonNull()){
                        storedObject = responseBody.getAsJsonObject(JSON_PLACE);
                        current = new Home(storedObject,JSON_PLACE);
                        homeListAdapter.add(current);
                    }
                    if(!responseBody.get(JSON_TEACHER).isJsonNull()){
                        storedObject = responseBody.getAsJsonObject(JSON_TEACHER);
                        current = new Home(storedObject,JSON_TEACHER);
                        homeListAdapter.add(current);
                    }
                    current_page++;
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}
