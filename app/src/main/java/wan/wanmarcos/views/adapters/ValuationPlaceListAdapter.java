package wan.wanmarcos.views.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;

import wan.wanmarcos.fragments.PopupCommentFragment;
import wan.wanmarcos.models.Valuation;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.views.adapters.ViewHolders.CustomHeaderViewHolder;
import wan.wanmarcos.views.adapters.ViewHolders.CustomViewHolder;
import wan.wanmarcos.views.adapters.ViewHolders.ValuationHolder;
import wan.wanmarcos.views.adapters.ViewHolders.ValuationPlaceHeaderHolder;

/**
 * Created by soporte on 26/11/15.
 */
public class ValuationPlaceListAdapter extends CustomDoubleAdapter<Valuation> implements PopUpFragment{
    public ValuationPlaceListAdapter(Fragment fragment) {
        super(fragment, Constants.VALUATION_NEW_ITEM, Constants.DETAIL_PLACE);
    }

    @Override
    public CustomViewHolder getObject(View view) {
        return new ValuationHolder(view);
    }

    @Override
    public CustomHeaderViewHolder getObjectHeader(View view) {
        ValuationPlaceHeaderHolder valuationPlaceHeaderHolder=new ValuationPlaceHeaderHolder(view);
        valuationPlaceHeaderHolder.setListener(this);
        return valuationPlaceHeaderHolder;
    }

    @Override
    public int getContainerID() {
        return Constants.PLACE_CONTAINER;
    }

    @Override
    public String getFragmentName() {
        return "";
    }

    @Override
    public void popUp() {
        FragmentManager fm = getFragment().getFragmentManager();
        PopupCommentFragment editNameDialog = new PopupCommentFragment();
        editNameDialog.setListener(this);
        editNameDialog.show(fm, "fragment_edit_name");
    }

    @Override
    public void close(String message) {
        add(new Valuation("Carlos","http://lorempixel.com/400/200/",message,(float)2.3));
    }
}
