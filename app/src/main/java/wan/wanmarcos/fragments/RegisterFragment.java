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

import org.json.JSONObject;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import wan.wanmarcos.R;
import wan.wanmarcos.activities.HomeActivity;
import wan.wanmarcos.models.*;
import wan.wanmarcos.models.Error;
import wan.wanmarcos.utils.Builder;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.utils.Redirection.Redirect;
import wan.wanmarcos.utils.ConvertResponse;
import wan.wanmarcos.utils.RestClient;


public class RegisterFragment extends Fragment {

    View view;
    private RestClient restClient;
    private Button btnRegister;
    private TextView txtError;
    private EditText txtEmail;
    private EditText txtPassword;
    private EditText txtFirstName;
    private EditText txtLastName;
    private String email;
    private String password;
    private String first_name;
    private String last_name;
    private String device_token = "xxxxx";
    private Builder builder = new Builder();
    private boolean received = true;

    public static RegisterFragment newInstance() {
        RegisterFragment fragment = new RegisterFragment();
        return fragment;
    }

    public RegisterFragment() {
        // Required empty public constructor
        restClient = new RestClient();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_register, container, false);
        setUpElements();
        addListeners();
        return view;
    }

    private void setUpElements(){
        btnRegister = (Button)view.findViewById(R.id.signUp);
        txtEmail = (EditText)view.findViewById(R.id.email);
        txtFirstName = (EditText)view.findViewById(R.id.first_name);
        txtLastName = (EditText)view.findViewById(R.id.last_name);
        txtPassword = (EditText)view.findViewById(R.id.password);
        txtError = (TextView)view.findViewById(R.id.error);
    }
    private void addListeners(){
        addSignUpListener();
    }


    private void addSignUpListener(){
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (received == false) {
                    return;
                } else {
                    getFields();
                    postSignUp();
                }
            }
        });
    }
    public void getFields(){
        email = txtEmail.getText().toString();
        first_name = txtFirstName.getText().toString();
        last_name = txtLastName.getText().toString();
        password = txtPassword.getText().toString();
    }
    public void postSignUp(){
        received=false;
        Call<JsonElement> signUpUser = restClient.getConsumerService()
                .signUp(
                        email, password,
                        first_name, last_name,
                        device_token, Constants.PLATFORM);

        signUpUser.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Response<JsonElement> response, Retrofit retrofit) {
                try {
                    if(response.isSuccess()){
                        JsonObject responseBody = response.body().getAsJsonObject();
                        Session session = builder.buildSession(responseBody);
                        User user = builder.buildUser(responseBody.get("user").getAsJsonObject());
                        received=true;
                        Intent intent = new Intent();
                        intent.setClass(getActivity(), HomeActivity.class);
                        startActivity(intent);

                    }else{
                        ConvertResponse error = new ConvertResponse(response);
                        String message=error.getMessage();
                        if(error.getKeys()!=null){
                            setErrorFields(error.getKeys(), message);
                        }else{
                            Toast.makeText(getActivity(),message, Toast.LENGTH_SHORT).show();
                        }

                        received = true;
                    }
                }catch (Throwable e){
                    Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
                    received=true;
                }
            }
            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getActivity(),"Conexión No Disponible.", Toast.LENGTH_SHORT).show();
                received=true;
            }
        });
    }
    private void setErrorFields(ArrayList<String> keys,String message){
        String[] messageField = message.split("/");
        String prueba="";
        for(int i=0;i<keys.size();i++){
            if(keys.get(i).equals("first_name")){
                txtFirstName.setError(messageField[i]);
            }else{
                if(keys.get(i).equals("last_name")){
                    txtLastName.setError(messageField[i]);
                }else{
                    if(keys.get(i).equals("email")){
                        txtEmail.setError(messageField[i]);
                    }else{
                        if(keys.get(i).equals("password")){
                            txtPassword.setError(messageField[i]);
                        }
                    }
                }
            }
        }
    }
}
