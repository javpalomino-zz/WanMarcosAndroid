package wan.wanmarcos.views.adapters;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import wan.wanmarcos.R;
import wan.wanmarcos.managers.ItemAdapterListener;
import wan.wanmarcos.managers.ViewHolderSetters;
import wan.wanmarcos.models.Rating;
import wan.wanmarcos.models.Valuation;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.utils.Redirection.Redirect;
import wan.wanmarcos.utils.Storage;
import wan.wanmarcos.views.adapters.ViewHolders.CustomViewHolder;
import wan.wanmarcos.views.adapters.ViewHolders.ValuationCourseHeaderHolder;
import wan.wanmarcos.views.adapters.ViewHolders.ValuationHolder;
import wan.wanmarcos.views.widgets.CircleTransform;

/**
 * Created by postgrado on 17/10/15.
 */

public class ValuationListAdapter extends CustomDoubleAdapter<Valuation>{

    public ValuationListAdapter(Fragment fragment) {
        super(fragment, Constants.VALUATION_NEW_ITEM, Constants.DETAIL_COURSE_TEACHER);
    }

    @Override
    public CustomViewHolder getObject(View view) {
        return new ValuationHolder(view);
    }

    @Override
    public CustomViewHolder getObjectHeader(View view) {
        return new ValuationCourseHeaderHolder(view);
    }

    @Override
    public int getContainerID() {
        return Constants.TEACHER_CONTAINER;
    }

    @Override
    public String getFragmentName() {
        return "";
    }
}