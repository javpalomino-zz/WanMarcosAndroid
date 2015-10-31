package wan.wanmarcos.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import wan.wanmarcos.R;

public class ContactanosActivity extends AppCompatActivity {
    private Button btnEnviarFeed;
    private EditText txtComment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactanos);
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
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setMessage("Gracias por darnos tu opinión.").
                setTitle("Contactanos").
                setCancelable(false).
                setNeutralButton("De Nada", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert=builder.create();
        alert.show();
    }

}
