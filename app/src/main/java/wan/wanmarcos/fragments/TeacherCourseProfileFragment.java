package wan.wanmarcos.fragments;


import android.app.DialogFragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import wan.wanmarcos.R;
import wan.wanmarcos.managers.Click;
import wan.wanmarcos.managers.FragmentsMethods;
import wan.wanmarcos.managers.ItemAdapterListener;
import wan.wanmarcos.models.Teacher;
import wan.wanmarcos.models.Valuation;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.utils.RestClient;
import wan.wanmarcos.utils.Storage;
import wan.wanmarcos.views.adapters.ValuationListAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeacherCourseProfileFragment extends Fragment implements FragmentsMethods{

    private ValuationListAdapter valuationListAdapter;
    private RecyclerView recyclerViewComments;
    private RestClient restClient;
    private int current_page;
    private String JSON_COMMENT="comments";
    String token="Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI1IiwiaXNzIjoiaHR0cDpcL1wvNTIuODkuMTI0LjBcL2FwaVwvdjFcL2F1dGhlbnRpY2F0ZSIsImlhdCI6IjE0NDcxMDQ5MzQiLCJleHAiOiIxNDU1NzQ0OTM0IiwibmJmIjoiMTQ0NzEwNDkzNCIsImp0aSI6IjcxZjM2NjgwN2EwZTIyZTY1ODM0OWYzZDMyOTcxNDQ1In0.gQK_MjKSRx6BhVCsy0CyhvJTEZB-wK2EWvKKJrDpUm4";
    public TeacherCourseProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        current_page=1;
        restClient=new RestClient(getActivity());
        valuationListAdapter=new ValuationListAdapter(this);
        getData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view=inflater.inflate(Constants.FRAGMENT_TEACHER_COURSE_LAYOUT, container, false);
        setUpElements(view);
        addListeners();
        return view;
    }

    @Override
    public void setUpElements(View view) {
        recyclerViewComments=(RecyclerView)view.findViewById(R.id.comments_list);
        valuationListAdapter=new ValuationListAdapter(this);
        recyclerViewComments.setAdapter(valuationListAdapter);
        recyclerViewComments.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    public void addListeners(){

    }
    public void getData(){
        Call<JsonElement> jsonElementCall=restClient.getConsumerService().getCourseComments(token,Integer.parseInt(Storage.getSingelton().getInfo(Storage.KEY_TEACHER_ID)),Integer.parseInt(Storage.getSingelton().getInfo(Storage.KEY_COURSE_ID)),current_page,Constants.CANTIDAD);
        jsonElementCall.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Response<JsonElement> response) {
                JsonObject responseBody = response.body().getAsJsonObject();
                if (responseBody.has(JSON_COMMENT)) {
                    JsonArray jsonArray = responseBody.getAsJsonArray(JSON_COMMENT);
                    for (int i = 0; i < jsonArray.size(); i++) {
                        JsonObject storedObject = jsonArray.get(i).getAsJsonObject();
                        Valuation current = new Valuation(storedObject);
                        valuationListAdapter.add(current);
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
        valuationListAdapter.add(new Valuation("Carlos","d","Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha sido el texto de relleno estándar de las industrias desde el año 1500, cuando un impresor (N. del T. persona que se dedica a la imprenta) desconocido usó una galería de textos y los mezcló de tal manera que logró hacer un libro de textos especimen. No sólo sobrevivió 500 años, sino que tambien ingresó como texto de relleno en documentos electrónicos, quedando esencialmente igual al original. Fue popularizado en los 60s con la creación de las hojas las cuales contenian pasajes de Lorem Ipsum, y más recientemente con software de autoedición, como por ejemplo Aldus PageMaker, el cual incluye versiones de Lorem Ipsum.",14));
        valuationListAdapter.add(new Valuation("Carlos","d","Curso Boni",14));
        valuationListAdapter.add(new Valuation("Carlos","d","Curso Boni",14));
        valuationListAdapter.add(new Valuation("Carlos","d","Curso Boni",14));
        valuationListAdapter.add(new Valuation("Carlos", "d", "Curso Boni", 14));
        valuationListAdapter.add(new Valuation("Carlos", "d", "Curso Boni", 14));
    }
}
