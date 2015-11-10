package wan.wanmarcos.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import wan.wanmarcos.R;
import wan.wanmarcos.utils.Redirection.Redirect;
import wan.wanmarcos.utils.Redirection.Redirection;
import wan.wanmarcos.models.Teacher;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.managers.ItemAdapterListener;
import wan.wanmarcos.utils.RestClient;
import wan.wanmarcos.views.adapters.TeacherListAdapter;
import wan.wanmarcos.views.widgets.RecyclerViewDivider;

public class SectionListTeachers extends Fragment implements ItemAdapterListener<Teacher> {
    private RestClient restClient;
    private RecyclerView recyclerView;
    private TeacherListAdapter teacherListAdapter;
    private SearchView searchView;
    private int i;

    public static SectionListTeachers newInstance(){
        SectionListTeachers sectionListTeachers=new SectionListTeachers();
        return sectionListTeachers;
    }

    public SectionListTeachers(){
        restClient =  new RestClient();
        i=1;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout=inflater.inflate(Constants.SECTION_GENERIC_LIST_LAYOUT,container,false);
        setUpElements(layout);
        addListeners();
        return layout;
    }

    public void setUpElements(View view){
        recyclerView=(RecyclerView)view.findViewById(R.id.generic_listView);
        teacherListAdapter=new TeacherListAdapter(getActivity(),getData(""));
        teacherListAdapter.setListener(this);
        recyclerView.setAdapter(teacherListAdapter);
        recyclerView.addItemDecoration(new RecyclerViewDivider(getActivity()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
    public void addListeners(){
        //addItemListener(listView);
    }

    public  List <Teacher> getData(String search) {
        List <Teacher>dTeachers=new ArrayList();
        SharedPreferences preferences = this.getActivity().getSharedPreferences(Constants.PREFERENCES, Context.MODE_PRIVATE);
        String token="Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI1IiwiaXNzIjoiaHR0cDpcL1wvNTIuODkuMTI0LjBcL2FwaVwvdjFcL2F1dGhlbnRpY2F0ZSIsImlhdCI6IjE0NDcxMDQ5MzQiLCJleHAiOiIxNDU1NzQ0OTM0IiwibmJmIjoiMTQ0NzEwNDkzNCIsImp0aSI6IjcxZjM2NjgwN2EwZTIyZTY1ODM0OWYzZDMyOTcxNDQ1In0.gQK_MjKSRx6BhVCsy0CyhvJTEZB-wK2EWvKKJrDpUm4";
        Call<JsonElement> getTeachers= restClient.getConsumerService().getTeachers(token,search, i, Constants.CANTIDAD);
        getTeachers.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Response<JsonElement> response) {
                JsonObject responseBody = response.body().getAsJsonObject();
                //gLog.d("DEBUG", responseBody.getAsString());
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("DEBUG", "error");
            }
        });
        dTeachers.add(new Teacher("Carlos", 15,new ArrayList<String>(), "Valeroso Profesor", "http://lorempixel.com/350/230/"));
        dTeachers.add(new Teacher("Jose",18,new ArrayList<String>(),"Educado Maestro", "http://lorempixel.com/350/230/"));
        dTeachers.add(new Teacher("Juan", 13, new ArrayList<String>(), "Profesor Empeñoso", "http://lorempixel.com/350/230/"));
        dTeachers.add(new Teacher("Miguel", 19, new ArrayList<String>(), "SHESHO SHESHO  ", "http://lorempixel.com/350/230/"));
        i++;
        return dTeachers;
    }

    @Override
    public void itemClicked(View view, Teacher object) {
        Redirect.getSingletonInstance().changeFragment(object);
    }
}
