package wan.wanmarcos.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import wan.wanmarcos.R;
import wan.wanmarcos.activities.HomeActivity;
import wan.wanmarcos.utils.Builder;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.utils.ConvertResponse;
import wan.wanmarcos.utils.RestClient;


public class LoginFragment extends Fragment {
    private RestClient restClient;
    private Button btnLogIn;
    private ImageView logoImg;
    private TextView btnRegister;
    private TextView btnForgotPassword;
    private EditText txtEmail;
    private EditText txtPassword;
    private TextView lblError;
    private String email;
    private String password;
    private Builder builder;
    private String device_token = "xxxxxxxxxxx";
    private Boolean received=true;
    private SharedPreferences preferences;
    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    public LoginFragment() {
        restClient =  new RestClient();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = setUpElements(inflater, container);
        addListeners();
        return view;
    }

    private View setUpElements(LayoutInflater inflater, ViewGroup container){
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        builder = new Builder();
        logoImg = (ImageView)view.findViewById(R.id.appLogo);
        btnLogIn =  (Button)view.findViewById(R.id.logIn);
        btnRegister =(TextView)view.findViewById(R.id.signUp);
        btnForgotPassword = (TextView)view.findViewById(R.id.forgotPassword);
        txtEmail = (EditText)view.findViewById(R.id.email);
        txtPassword = (EditText)view.findViewById(R.id.password);
        lblError = (TextView)view.findViewById(R.id.error);

        return view;
    }

    private void addListeners(){
        addLogInListener();
        addRegisterListener();
        addForgotPasswordListener();
    }

    private void addLogInListener(){

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(received == false){
                    return;
                }else {
                    getFields();
                    postLogIn();
                }
            }
        });
    }

    private void addRegisterListener(){
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterFragment registerFragment = new RegisterFragment();
                try {
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, registerFragment);
                    transaction.addToBackStack("loginFragment");

                    transaction.commit();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void addForgotPasswordListener(){
        btnForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void getFields(){
        email = txtEmail.getText().toString();
        password = txtPassword.getText().toString();
    }

    private void postLogIn(){
        received = false;
        Call<JsonElement> logInUser= restClient.getConsumerService().login(email, password,device_token, Constants.PLATFORM);
        logInUser.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Response<JsonElement> response) {
                try {
                    if (response.isSuccess()) {
                        JsonObject responseBody = response.body().getAsJsonObject();
                        if (responseBody.has("token")) {
                            String token = responseBody.get("token").getAsString();
                            setPreferences(token);
                            changeToHome();
                            received=true;
                        }
                    } else {
                        JSONObject responseBodyError = new JSONObject(response.errorBody().string());
                        String message = "";
                        if(response.code()==500){
                            message="Error del Servidor. Vuelva a intentar más tarde";
                        }else{
                            message=ConvertResponse.getMessageAutenticate(responseBodyError);
                        }
                        Toast.makeText(getActivity(),message, Toast.LENGTH_SHORT).show();
                        received=true;
                    }
                }catch(Throwable e){
                    Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
                    received=true;
                }
            }
            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setPreferences(String token){
        preferences = this.getActivity().getSharedPreferences(Constants.PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("token",token);
        editor.commit();
    }
    private void changeToHome(){
        Intent home_activity = new Intent(getActivity(), HomeActivity.class);
        getActivity().finish();
        startActivity(home_activity);
    }
}
