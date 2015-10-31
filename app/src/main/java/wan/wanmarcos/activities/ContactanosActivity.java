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

import wan.wanmarcos.R;
import wan.wanmarcos.fragments.NavigationDrawerFragment;
import wan.wanmarcos.managers.Communicator;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.utils.Modal;

public class ContactanosActivity extends AppCompatActivity{
    private Toolbar toolbar;
    private Button btnEnviarFeed;
    private EditText txtComment;
    private Modal modal;
    NavigationDrawerFragment drawerFragment;

    @Override
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
                PopUp();
            }
        });
    }

    private void PopUp(){
        modal.buildModal(Constants.MODAL_TITLE_CONTACTANOS,
                        Constants.MODAL_MESSAGE_CONTACTANOS,
                        Constants.MODAL_BUTTON_DENADA,true);
        modal.showModal();
    }
}
