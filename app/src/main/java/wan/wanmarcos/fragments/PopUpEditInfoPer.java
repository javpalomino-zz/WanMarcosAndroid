package wan.wanmarcos.fragments;

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

import java.util.ArrayList;
import java.util.HashMap;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import wan.wanmarcos.R;
import wan.wanmarcos.managers.FragmentsMethods;
import wan.wanmarcos.models.Rating;
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
    private HashMap<String,String> marCarreers;

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
        faculty= (AutoCompleteTextView) view.findViewById(R.id.profile_edit_info_faculty);
        carreer= (AutoCompleteTextView) view.findViewById(R.id.profile_edit_info_carreer);
        sendButton = (Button)view.findViewById(R.id.profile_edit_info_submit);
        getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        restClient=new RestClient();
    }

    @Override
    public void addListeners() {
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();

            }
        });
    }
    public void setListener(PopUpFragment listener){
        onClickListener=listener;
    }

    private void getCategories(){
        Call<JsonElement> sugCategories = restClient.getConsumerService().autocompleteFaculties(null);
        sugCategories.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Response<JsonElement> response, Retrofit retrofit) {
                if(response.isSuccess()){
                    JsonArray categories = response.body().getAsJsonObject().getAsJsonArray("categories");
                    ArrayList<String> body = new ArrayList<String>();
                    for (int i = 0; i < categories.size(); i++) {
                        body.add(categories.get(i).getAsJsonObject().get("name").getAsString());
                        mapFaculties.put(categories.get(i).getAsJsonObject().get("name").getAsString(), categories.get(i).getAsJsonObject().get("id").getAsString());
                    }
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
}
