package wan.wanmarcos.views.adapters.ViewHolders;

import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import wan.wanmarcos.R;
import wan.wanmarcos.models.Preference;

/**
 * Created by Francisco on 1/12/2015.
 */
public class PreferenceHolder extends CustomViewHolder<Preference> {

    private TextView preferenceName;
    private View view;
    private ImageView img;
    private Preference preference;

    public PreferenceHolder(View itemView) {
        super(itemView);
        preferenceName=(TextView)itemView.findViewById(R.id.preference_name);
        img=(ImageView)itemView.findViewById(R.id.underConstructionList);
        view=itemView;
    }
    @Override
    public void setElements(Preference object) {
        preference=object;
        preferenceName.setText(preference.getPreferenceName());
        Picasso.with(view.getContext()).load(R.mipmap.enconstruccionbob).fit().into(img);

    }

    @Override
    public void onClick(View v) {

    }
}
