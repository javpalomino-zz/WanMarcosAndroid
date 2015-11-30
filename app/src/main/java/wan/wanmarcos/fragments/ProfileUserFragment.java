package wan.wanmarcos.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.security.auth.callback.Callback;

import retrofit.Call;
import retrofit.Response;
import wan.wanmarcos.R;
import wan.wanmarcos.managers.FragmentsMethods;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.utils.RestClient;

/**
 * Created by carlos-pc on 29/11/15.
 */
public class ProfileUserFragment extends Fragment implements FragmentsMethods{
    private TextView name;
    private TextView email;
    private RestClient restClient;

    String token="Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI1IiwiaXNzIjoiaHR0cDpcL1wvNTIuODkuMTI0LjBcL2FwaVwvdjFcL2F1dGhlbnRpY2F0ZSIsImlhdCI6IjE0NDcxMDQ5MzQiLCJleHAiOiIxNDU1NzQ0OTM0IiwibmJmIjoiMTQ0NzEwNDkzNCIsImp0aSI6IjcxZjM2NjgwN2EwZTIyZTY1ODM0OWYzZDMyOTcxNDQ1In0.gQK_MjKSRx6BhVCsy0CyhvJTEZB-wK2EWvKKJrDpUm4";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        restClient=new RestClient();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(Constants.FRAGMENT_PROFILE_LAYOUT,container,false);
        setUpElements(view);
        addListeners();
        return view;
    }

    @Override
    public void setUpElements(View view) {
        name=(TextView)view.findViewById(R.id.profile_name);
        email=(TextView)view.findViewById(R.id.profile_email);
        getData();
    }

    public void getData(){
        Call<JsonElement> userInfo=restClient.getConsumerService().me(token);
        userInfo.enqueue(new retrofit.Callback<JsonElement>() {
            @Override
            public void onResponse(Response<JsonElement> response) {
                JsonObject jsonObject=response.body().getAsJsonObject();

                name.setText(jsonObject.get("first_name").getAsString()+" "+jsonObject.get("last_name").getAsString());
                email.setText(jsonObject.get("email").getAsString());
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
    @Override
    public void addListeners() {

    }
}
