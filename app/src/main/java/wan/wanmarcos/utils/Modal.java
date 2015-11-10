package wan.wanmarcos.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;

import wan.wanmarcos.activities.ContactanosActivity;

/**
 * Created by postgrado on 31/10/15.
 */
public class Modal  {
    AlertDialog.Builder builder;
    public Modal(Activity activity){
        builder = new AlertDialog.Builder(activity);
    }

    public void buildModal(String title,String  messague,String textButton,Boolean cancelable){
        builder.setMessage(messague).
                setTitle(title).
                setCancelable(cancelable).
                setNeutralButton(textButton, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
    }
    public void showModal(){
        AlertDialog alert=builder.create();
        alert.show();
    }

}
