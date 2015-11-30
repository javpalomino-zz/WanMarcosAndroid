package wan.wanmarcos.views.adapters;

import android.support.v4.app.Fragment;
import android.view.View;

import wan.wanmarcos.models.Home;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.views.adapters.ViewHolders.CustomViewHolder;
import wan.wanmarcos.views.adapters.ViewHolders.HomeHolder;

/**
 * Created by carlos-pc on 29/11/15.
 */
public class HomeListAdapter extends CustomSimpleListAdapter<Home> {
    public HomeListAdapter(Fragment fragment) {
        super(fragment, Constants.HOME_NEW_ITEM);
    }

    @Override
    public CustomViewHolder getObject(View view) {
        return new HomeHolder(view);
    }

    @Override
    public int getContainerID() {
        return Constants.HOME_CONTAINER;
    }

    @Override
    public String getFragmentName() {
        return "";
    }
}
