package wan.wanmarcos.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.SearchView;
import android.widget.Toast;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import wan.wanmarcos.R;
import wan.wanmarcos.managers.FragmentsMethods;
import wan.wanmarcos.managers.ItemAdapterListener;
import wan.wanmarcos.models.Teacher;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.utils.Redirection.Redirect;
import wan.wanmarcos.utils.RestClient;
import wan.wanmarcos.utils.Storage;
import wan.wanmarcos.views.adapters.TeacherListAdapter;


public class TeacherListFragment extends Fragment implements FragmentsMethods{
    private SearchView searchView;
    private TeacherListAdapter teacherListAdapter;
    private RecyclerView recyclerViewTeachers;
    private LinearLayoutManager layoutManagerRecyclerView;
    private RestClient restClient;
    private int currentPage;
    private String actualSerach;
    private static final String JSON_TEACHER="professors";
    String token="Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI1IiwiaXNzIjoiaHR0cDpcL1wvNTIuODkuMTI0LjBcL2FwaVwvdjFcL2F1dGhlbnRpY2F0ZSIsImlhdCI6IjE0NDcxMDQ5MzQiLCJleHAiOiIxNDU1NzQ0OTM0IiwibmJmIjoiMTQ0NzEwNDkzNCIsImp0aSI6IjcxZjM2NjgwN2EwZTIyZTY1ODM0OWYzZDMyOTcxNDQ1In0.gQK_MjKSRx6BhVCsy0CyhvJTEZB-wK2EWvKKJrDpUm4";

    public TeacherListFragment() {
        // Required empty public constructor
        actualSerach=Constants.EMPTY_STRING;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        restClient = new RestClient(getActivity());
        currentPage=1;
        teacherListAdapter=new TeacherListAdapter(this);
        getTeacherData(actualSerach);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(Constants.FRAGMENT_LIST_TEACHER_LAYOUT, container, false);
        setUpElements(view);
        addListeners();
        return view;
    }

    public void addListeners() {
        recyclerViewTeachers.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int state = 0;
            private int i = 0;
            private boolean changeState = false;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState != state) {
                    changeState = true;
                    if (changeState ) {
                        if(state==0){
                            if(layoutManagerRecyclerView.findLastCompletelyVisibleItemPosition() == teacherListAdapter.getItemCount() - 1 && layoutManagerRecyclerView.findLastCompletelyVisibleItemPosition() > 0) {
                                getTeacherData(actualSerach);
                            }
                        }
                    }
                    state = newState;
                }
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            String previousQuery=Constants.EMPTY_STRING;
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(!previousQuery.equals(query)){
                    resetAdapter(query);
                    getTeacherData(query);
                    previousQuery=query;

                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //TODO
                return false;
            }
        });
    }
    private void resetAdapter(String query){
        actualSerach=query;
        teacherListAdapter.removeAll();
        currentPage=1;
    }
    public void setUpElements(View view){
        layoutManagerRecyclerView=new LinearLayoutManager(getActivity());
        searchView=(SearchView)view.findViewById(R.id.searchViewTeachers);
        searchView.setFocusable(false);
        recyclerViewTeachers=(RecyclerView)view.findViewById(R.id.teacher_list);
        recyclerViewTeachers.setAdapter(teacherListAdapter);
        recyclerViewTeachers.setLayoutManager(layoutManagerRecyclerView);
    }

    public void getTeacherData(String search_text){
        Call<JsonElement> teacherPage= restClient.getConsumerService().getTeachers(token,search_text,currentPage,Constants.CANTIDAD);
        teacherPage.enqueue(new Callback<JsonElement>() {
            @Override
            public synchronized void onResponse(Response<JsonElement> response, Retrofit retrofit) {
                JsonObject responseBody = response.body().getAsJsonObject();
                if (responseBody.has(JSON_TEACHER)) {
                    JsonArray jsonArray = responseBody.getAsJsonArray(JSON_TEACHER);
                    for (int i = 0; i < jsonArray.size(); i++) {
                        JsonObject storedObject = jsonArray.get(i).getAsJsonObject();
                        Teacher current = new Teacher(storedObject);
                        teacherListAdapter.add(current);
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }

        });
        currentPage++;
    }
}
