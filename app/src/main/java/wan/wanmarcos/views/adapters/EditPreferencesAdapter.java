package wan.wanmarcos.views.adapters;

import android.support.v4.app.Fragment;
import android.view.View;

import wan.wanmarcos.models.Preference;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.views.adapters.ViewHolders.CustomViewHolder;
import wan.wanmarcos.views.adapters.ViewHolders.EditPreferencesHolder;

/**
 * Created by Francisco on 1/12/2015.
 */
public class EditPreferencesAdapter extends CustomSimpleListAdapter<Preference> {
    public EditPreferencesAdapter(Fragment fragment) {
        super(fragment, Constants.EDIT_PREFERENCES_NEW_ITEM);
    }

    @Override
    public CustomViewHolder getObject(View view) {
        return new EditPreferencesHolder(view);
    }

    @Override
    public int getContainerID() {
        return Constants.PROFILE_CONTAINER;
    }

    @Override
    public String getFragmentName() {
        return Constants.FRAGMENT_EDIT_PREFERENCE;
    }
}
