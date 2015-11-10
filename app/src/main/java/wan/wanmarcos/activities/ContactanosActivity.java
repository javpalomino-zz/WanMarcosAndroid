package wan.wanmarcos.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
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
import wan.wanmarcos.models.Session;
import wan.wanmarcos.models.User;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.utils.Modal;
import wan.wanmarcos.utils.Redirection.Redirect;
import wan.wanmarcos.utils.RestClient;

public class ContactanosActivity extends AppCompatActivity{
    private Toolbar toolbar;
    private Button btnEnviarFeed;
    private EditText txtComment;
    private Modal modal;
    private String post_response_messague;
    NavigationDrawerFragment drawerFragment;

    private RestClient restClient;
    SharedPreferences preferences;
    Session session;

   public ContactanosActivity(){
        post_response_messague = "\n";
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        restClient = new RestClient(this);
        preferences = getSharedPreferences(Constants.PREFERENCES, MODE_PRIVATE);
        session = Session.getSession(preferences);

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

                post_response_messague = session.getToken();

                Call<JsonElement> suggestion = restClient.getConsumerService().suggestions(Constants.HEADER+session.getToken(),"olibolicamaronconcoli");
                
                suggestion.enqueue(new Callback<JsonElement>() {
                    @Override
                    public void onResponse(Response<JsonElement> response) {
                        try{
                            post_response_messague = "Entro al try \n";
                            if(response.isSuccess()){
                                JsonObject responseBody = response.body().getAsJsonObject();
                                post_response_messague +="/nBody: "+responseBody.toString();
                            }else{
                                txtComment.setText(response.errorBody().string());
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
