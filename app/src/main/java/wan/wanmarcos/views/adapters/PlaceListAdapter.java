package wan.wanmarcos.views.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import wan.wanmarcos.R;
import wan.wanmarcos.fragments.Place_SectionListFragment;
import wan.wanmarcos.managers.ItemAdapterListener;
import wan.wanmarcos.managers.ViewHolderSetters;
import wan.wanmarcos.models.Place;
import wan.wanmarcos.models.Teacher;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.views.adapters.ViewHolders.CustomViewHolder;
import wan.wanmarcos.views.adapters.ViewHolders.PlaceHolder;
import wan.wanmarcos.views.widgets.CircleTransform;

/**
 * Created by soporte on 25/11/15.
 */

public class PlaceListAdapter extends CustomSimpleListAdapter<Place>{

    public PlaceListAdapter(Fragment fragment) {
        super(fragment, Constants.PLACE_NEW_ITEM);
    }

    @Override
    public CustomViewHolder getObject(View view) {
        return new PlaceHolder(view);
    }

    @Override
    public int getContainerID() {
        return Constants.PLACE_CONTAINER;
    }

    @Override
    public String getFragmentName() {
        return Constants.FRAGMENT_PROFILE_PLACE;
    }
}