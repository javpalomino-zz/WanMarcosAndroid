package wan.wanmarcos.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import wan.wanmarcos.R;
import wan.wanmarcos.managers.FragmentsMethods;
import wan.wanmarcos.models.Home;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.utils.RestClient;
import wan.wanmarcos.views.adapters.HomeListAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeListNewsFragment extends Fragment implements FragmentsMethods {
    private HomeListAdapter homeListAdapter;
    private RecyclerView recyclerView;
    private RestClient restClient;
    private int current_page;
    String token="Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI1IiwiaXNzIjoiaHR0cDpcL1wvNTIuODkuMTI0LjBcL2FwaVwvdjFcL2F1dGhlbnRpY2F0ZSIsImlhdCI6IjE0NDcxMDQ5MzQiLCJleHAiOiIxNDU1NzQ0OTM0IiwibmJmIjoiMTQ0NzEwNDkzNCIsImp0aSI6IjcxZjM2NjgwN2EwZTIyZTY1ODM0OWYzZDMyOTcxNDQ1In0.gQK_MjKSRx6BhVCsy0CyhvJTEZB-wK2EWvKKJrDpUm4";
    public HomeListNewsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        restClient=new RestClient(getActivity());
        homeListAdapter=new HomeListAdapter(this);
        current_page=1;
        getData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(Constants.FRAGMENT_LIST_NEWS_LAYOUT, container, false);
        setUpElements(view);
        addListeners();
        return view;
    }


    @Override
    public void setUpElements(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.home_list);
        recyclerView.setAdapter(homeListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    @Override
    public void addListeners() {

    }

    public void getData() {
        Log.d("D","entro");
        Call<JsonElement> teacherPage= restClient.getConsumerService().getNews(token,current_page,Constants.CANTIDAD);
        teacherPage.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Response<JsonElement> response) {
                Log.d("D","cargo");
                JsonObject responseBody = response.body().getAsJsonObject();
                Log.d("D",responseBody.isJsonArray()+"*/n "+responseBody.toString());
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("D","error");
            }
        });
        homeListAdapter.add(new Home(0,"carlos","casa","Teacher","http://lorempixel.com/g/400/200/"));
        homeListAdapter.add(new Home(0,"Toma de Facultad","chio","Event","http://lorempixel.com/g/400/200/"));
        homeListAdapter.add(new Home(0,"Chio","fisi","Place","http://lorempixel.com/g/400/200/"));
        homeListAdapter.add(new Home(0,"SImpe","may","Teacher","http://lorempixel.com/g/400/200/"));
        homeListAdapter.add(new Home(0,"FISITONAZO","lel","Event","http://lorempixel.com/g/400/200/"));
    }
}
