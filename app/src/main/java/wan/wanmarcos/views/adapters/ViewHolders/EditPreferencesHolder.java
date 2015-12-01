package wan.wanmarcos.views.adapters.ViewHolders;

import android.content.DialogInterface;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import wan.wanmarcos.R;
import wan.wanmarcos.fragments.PopUpEditInfoPer;
import wan.wanmarcos.models.Preference;

/**
 * Created by Francisco on 1/12/2015.
 */
public class EditPreferencesHolder extends CustomViewHolder<Preference> {

    private TextView preferenceName;
    private ImageView deleteButton;
    private int prefernceId;

    public EditPreferencesHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        preferenceName = (TextView) itemView.findViewById(R.id.editpref_faculty_list_faculty_name);
        deleteButton = (ImageView) itemView.findViewById(R.id.remove_faculty_button);
        setListeners();
    }

    public void setListeners()
    {
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAndShowAlertDialog();
            }
        });
    }
        private void createAndShowAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
        builder.setTitle("¿Estás Seguro de Querer Eliminar \n"+preferenceName.getText());
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //TODO
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //TODO
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    @Override
    public void setElements(Preference object) {
        preferenceName.setText(object.getPreferenceName());
    }

    @Override
    public void onClick(View v) {

    }
}
