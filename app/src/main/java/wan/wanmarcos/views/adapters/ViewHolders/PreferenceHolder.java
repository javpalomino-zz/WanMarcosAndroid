package wan.wanmarcos.views.adapters.ViewHolders;

import android.view.View;
import android.widget.TextView;

import wan.wanmarcos.R;
import wan.wanmarcos.models.Preference;

/**
 * Created by Francisco on 1/12/2015.
 */
public class PreferenceHolder extends CustomViewHolder<Preference> {

    private TextView preferenceName;
    private View view;
    private Preference preference;

    public PreferenceHolder(View itemView) {
        super(itemView);
        preferenceName=(TextView)itemView.findViewById(R.id.preference_name);
        view=itemView;
    }
    @Override
    public void setElements(Preference object) {
        preference=object;
        preferenceName.setText(preference.getPreferenceName());

    }

    @Override
    public void onClick(View v) {

    }
}
