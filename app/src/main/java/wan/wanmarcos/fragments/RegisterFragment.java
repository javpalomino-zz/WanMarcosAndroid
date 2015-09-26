package wan.wanmarcos.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import wan.wanmarcos.R;
import wan.wanmarcos.activities.HomeActivity;
import wan.wanmarcos.models.*;
import wan.wanmarcos.models.Error;
import wan.wanmarcos.utils.Builder;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.utils.RestClient;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RegisterFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        btnRegister = (Button)view.findViewById(R.id.signUp);
        txtEmail = (EditText)view.findViewById(R.id.email);
        txtFirstName = (EditText)view.findViewById(R.id.first_name);
        txtLastName = (EditText)view.findViewById(R.id.last_name);
        txtPassword = (EditText)view.findViewById(R.id.password);

        txtError = (TextView)view.findViewById(R.id.error);

        btnRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                email = txtEmail.getText().toString();
                first_name = txtFirstName.getText().toString();
                last_name = txtLastName.getText().toString();
                password = txtPassword.getText().toString();

                Call<JsonElement> signUpUser = restClient.getConsumerService()
                            .signUp(
                                    email, password,
                                    first_name, last_name,
                                    device_token, Constants.PLATFORM);

                signUpUser.enqueue(new Callback<JsonElement>() {
                    @Override
                    public void onResponse(Response<JsonElement> response) {
                        JsonObject responseBody = response.body().getAsJsonObject();

                        if(responseBody.has("token")){
                            Session session = builder.buildSession(responseBody);

                            if(responseBody.has("user")){
                                User user = builder.buildUser(responseBody.get("user").getAsJsonObject());
                                txtError.setText(user.toString());
                            }
                            Intent intent = new Intent();
                            intent.setClass(getActivity(), HomeActivity.class);
                            startActivity(intent);
                        }
                        else{
                            if(responseBody.has("error")){
                                Error error = builder.buildError(responseBody.get("error").getAsJsonObject());
                                txtError.setText(error.toString());
                            }
                            else{
                                txtError.setText("unknow error");
                            }
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {

                    }
                });
            }
        });

        return view;
    }


}
