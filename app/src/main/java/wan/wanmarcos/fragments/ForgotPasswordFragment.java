package wan.wanmarcos.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import wan.wanmarcos.R;
import wan.wanmarcos.activities.HomeActivity;
import wan.wanmarcos.models.Session;
import wan.wanmarcos.models.User;
import wan.wanmarcos.utils.Builder;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.utils.ConvertResponse;
import wan.wanmarcos.utils.RestClient;

/**
 * Created by soporte on 28/11/15.
 */
public class ForgotPasswordFragment extends Fragment {

    View view;
    private RestClient restClient;
    private Button btnRetrievePassowrd;
    private EditText txtEmail;
    private String email;
    private String device_token = "xxxxx";
    private Builder builder = new Builder();
    private boolean received = true;

    public static ForgotPasswordFragment newInstance() {
        ForgotPasswordFragment fragment = new ForgotPasswordFragment();
        return fragment;
    }

    public ForgotPasswordFragment() {
        // Required empty public constructor
        restClient = new RestClient();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_forgot_password, container, false);
        setUpElements();
        addListeners();
        return view;
    }

    private void setUpElements(){
        btnRetrievePassowrd = (Button)view.findViewById(R.id.retrieveButton);
        txtEmail = (EditText)view.findViewById(R.id.email);
    }
    private void addListeners(){
        addRetrievPasswordListener();
    }


    private void addRetrievPasswordListener(){
        btnRetrievePassowrd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }
    public void getFields(){
        email = txtEmail.getText().toString();
    }
}
