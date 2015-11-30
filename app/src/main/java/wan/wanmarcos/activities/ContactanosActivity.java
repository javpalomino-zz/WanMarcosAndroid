package wan.wanmarcos.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.squareup.okhttp.ResponseBody;

import org.json.JSONObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
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
    private FloatingActionButton btnEnviarFeed;
    private EditText txtComment;
    private Modal modal;
    private String post_response_messague;
    private RestClient restClient;
    private String token;
    private String message;
    private Boolean received=true;
    NavigationDrawerFragment drawerFragment;
    SharedPreferences preferences;
    Session session;

   public ContactanosActivity(){
        post_response_messague = "\n";
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpElements();
        setListeners();
    }
    void setListeners(){
        addListenerSendFeedback();
    }
    void addListenerSendFeedback(){
        btnEnviarFeed.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(received == false){
                    return;
                }else{
                    getFields();
                    postFeedback(v.getContext());
                }

            }
        });
    }
    private void setUpElements(){
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
        btnEnviarFeed = (FloatingActionButton)this.findViewById(R.id.btnEnviarFeed);
        txtComment = (EditText)this.findViewById(R.id.txtComment);
    }
    private void getFields(){
        token = Constants.HEADER+session.getToken();
        message = txtComment.getText().toString();
    }
    private void postFeedback(final Context context){
        received = false;
        Call < JsonElement > suggestion = restClient.getConsumerService().suggestions(token,message);
        suggestion.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Response<JsonElement> response, Retrofit retrofit) {
                try {
                    if (response.isSuccess()) {
                        JsonObject responseBody = response.body().getAsJsonObject();
                        PopUp();
                    } else {
                        JSONObject responseBody = new JSONObject(response.errorBody().string());
                        Toast.makeText(context, responseBody.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                    received = true;
                } catch (Throwable e) {
                    post_response_messague = "Error: " + e.getMessage();
                    Toast.makeText(context,post_response_messague, Toast.LENGTH_SHORT).show();
                    received = true;
                }
            }

            @Override
            public void onFailure(Throwable t) {
                received = true;
                post_response_messague = "Error de Conexión" + t.toString();
                Toast.makeText(context,post_response_messague, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void PopUp(){
        modal.buildModal(Constants.MODAL_TITLE_CONTACTANOS,
                        Constants.MODAL_MESSAGE_CONTACTANOS+post_response_messague,
                        Constants.MODAL_BUTTON_DENADA,true);
        modal.showModal();
    }
}
