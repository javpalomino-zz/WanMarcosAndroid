package wan.wanmarcos.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.JsonElement;
import com.squareup.okhttp.ResponseBody;

import org.json.JSONObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import wan.wanmarcos.R;
import wan.wanmarcos.fragments.NavigationDrawerFragment;
import wan.wanmarcos.managers.Communicator;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.utils.Modal;
import wan.wanmarcos.utils.RestClient;

public class ContactanosActivity extends AppCompatActivity{
    private Toolbar toolbar;
    private Button btnEnviarFeed;
    private EditText txtComment;
    private Modal modal;
    private RestClient restClient;
    private String post_response_messague;
    NavigationDrawerFragment drawerFragment;

   public ContactanosActivity(){
        restClient = new RestClient();
        post_response_messague = "\n";
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactanos);
        modal = new Modal(this);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        drawerFragment = (NavigationDrawerFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.SetUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);
        btnEnviarFeed = (Button)this.findViewById(R.id.btnEnviarFeed);
        txtComment = (EditText)this.findViewById(R.id.txtComment);
        btnEnviarFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Call<JsonElement> suggestion = restClient.getConsumerService().suggestions("Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaXNzIjoiaHR0cDpcL1wvNTIuODkuMTI0LjBcL2FwaVwvdjFcL2F1dGhlbnRpY2F0ZSIsImlhdCI6IjE0NDY5MzA4NjUiLCJleHAiOiIxNDQ2OTM0NDY1IiwibmJmIjoiMTQ0NjkzMDg2NSIsImp0aSI6IjQzMzAyNTJmOWRiMTk4ZTY0NWMzZTQxN2RhYTQ1ODY5In0.7PHMeq-81bTd6_Hz1_lZopKEeZ4tw6uFuFpb7HkvpqY","olibolicamaronconcoli");
                suggestion.enqueue(new Callback<JsonElement>() {
                    @Override
                    public void onResponse(Response<JsonElement> response) {
                        try{
                            post_response_messague = "Entro al try \n";
                            if(response.isSuccess()){
                                JsonObject responseBody = response.body().getAsJsonObject();
                                post_response_messague +="/nBody: "+responseBody.toString();
                            }else{
                                JSONObject responseBody = new JSONObject(response.errorBody().string());
                                post_response_messague += responseBody.toString();
                            }
                        }catch (Throwable e){
                            post_response_messague +="/nException: "+e.toString();
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        post_response_messague += "/nFail: "+t.toString();
                    }
                });
                PopUp();
            }
        });
    }

    private void PopUp(){
        modal.buildModal(Constants.MODAL_TITLE_CONTACTANOS,
                        Constants.MODAL_MESSAGE_CONTACTANOS+"\nresponse: "+post_response_messague,
                        Constants.MODAL_BUTTON_DENADA,true);
        modal.showModal();
    }
}
