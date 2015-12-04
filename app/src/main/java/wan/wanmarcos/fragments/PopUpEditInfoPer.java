package wan.wanmarcos.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import wan.wanmarcos.R;
import wan.wanmarcos.activities.ProfileActivity;
import wan.wanmarcos.managers.FragmentsMethods;
import wan.wanmarcos.models.Rating;
import wan.wanmarcos.models.Session;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.utils.RestClient;
import wan.wanmarcos.utils.Storage;
import wan.wanmarcos.views.adapters.PopUpFragment;
import wan.wanmarcos.views.adapters.RatingListAdapter;
import wan.wanmarcos.views.widgets.CircleTransform;

/**
 * Created by Francisco on 1/12/2015.
 */
public class PopUpEditInfoPer extends DialogFragment implements FragmentsMethods {
    AutoCompleteTextView faculty;
    AutoCompleteTextView carreer;
    Button sendButton;
    RestClient restClient;
    private PopUpFragment onClickListener;
    private HashMap<String,String> mapFaculties;
    private HashMap<String,String> mapCarreers;
    private int facultyId;
    private int carreerId;
    private SharedPreferences preferences;
    private Session session;
    private String token;
    private boolean recieved;
    public PopUpEditInfoPer()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(Constants.PERSONAL_INFO_POPUP, container);
        setUpElements(view);
        addListeners();
        return view;
    }
    @Override
    public void setUpElements(View view) {
        recieved=true;
        preferences = view.getContext().getSharedPreferences(Constants.PREFERENCES, Context.MODE_PRIVATE);
        session = Session.getSession(preferences);
        token = Constants.HEADER+session.getToken();
        restClient=new RestClient();
        mapFaculties = new HashMap<String,String>();
        mapCarreers = new HashMap<String,String>();
        faculty= (AutoCompleteTextView) view.findViewById(R.id.profile_edit_info_faculty);
        faculty.setThreshold(1);
        getFaculties();
        carreer= (AutoCompleteTextView) view.findViewById(R.id.profile_edit_info_carreer);
        carreer.setThreshold(1);
        getCarrers();
        sendButton = (Button)view.findViewById(R.id.profile_edit_info_submit);
        getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
    }

    @Override
    public void addListeners() {
        setListenerSendButton();
        addOnItemSelectedFaculties();
        addOnItemSelectedCarreer();
    }
    public void setListenerSendButton(){
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recieved) {
                    sendPersonalInformation();
                    dismiss();
                } else {
                    Toast.makeText(getActivity(), "La informacion esta siendo enviada", Toast.LENGTH_SHORT);
                }

            }
        });
    }
    public void sendPersonalInformation(){
        recieved =false;
        Call<JsonElement> sugEvent = restClient.getConsumerService().changeProfileInformation(token, facultyId, carreerId);
        sugEvent.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Response<JsonElement> response, Retrofit retrofit) {
                if(response.isSuccess()){
                    System.out.println("Success");
                }else{

                    Toast.makeText(getActivity(),"Error al comunicarse con el servidor",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println(t);
            }
        });

    }
    public void setListener(PopUpFragment listener){
        onClickListener=listener;
    }
    private void getFaculties(){
        Call<JsonElement> sugCategories = restClient.getConsumerService().autocompleteFaculties(null);
        sugCategories.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Response<JsonElement> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    JsonArray faculties = response.body().getAsJsonObject().getAsJsonArray("faculties");
                    ArrayList<String> body = new ArrayList<String>();
                    String prueba = "";
                    for (int i = 0; i < faculties.size(); i++) {
                        prueba += faculties.get(i).getAsJsonObject().get("name").getAsString();
                        body.add(faculties.get(i).getAsJsonObject().get("name").getAsString());
                        mapFaculties.put(faculties.get(i).getAsJsonObject().get("name").getAsString(), faculties.get(i).getAsJsonObject().get("id").getAsString());
                    }
                    Toast.makeText(getActivity(), prueba, Toast.LENGTH_SHORT).show();
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.select_dialog_item, body);
                    faculty.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
    private void addOnItemSelectedFaculties(){
        faculty.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String facultynname = faculty.getText().toString();
                facultyId = Integer.parseInt(mapFaculties.get(facultynname));
            }
        });
    }
    private void getCarrers(){
        Call<JsonElement> sugCategories = restClient.getConsumerService().autocompleteCarrers(null);
        sugCategories.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Response<JsonElement> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    JsonArray carrers = response.body().getAsJsonObject().getAsJsonArray("degrees");
                    ArrayList<String> body = new ArrayList<String>();
                    String prueba = "";
                    for (int i = 0; i < carrers.size(); i++) {
                        prueba += carrers.get(i).getAsJsonObject().get("name").getAsString();
                        body.add(carrers.get(i).getAsJsonObject().get("name").getAsString());
                        mapCarreers.put(carrers.get(i).getAsJsonObject().get("name").getAsString(), carrers.get(i).getAsJsonObject().get("id").getAsString());
                    }
                    Toast.makeText(getActivity(), prueba, Toast.LENGTH_SHORT).show();
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.select_dialog_item, body);
                    carreer.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
    private void addOnItemSelectedCarreer() {
        carreer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String carrername = carreer.getText().toString();
                carreerId = Integer.parseInt(mapCarreers.get(carrername));
            }
        });
    }
}
