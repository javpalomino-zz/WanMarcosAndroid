package wan.wanmarcos.views.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;

import wan.wanmarcos.fragments.PopupCommentFragment;
import wan.wanmarcos.models.Valuation;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.views.adapters.ViewHolders.CustomHeaderViewHolder;
import wan.wanmarcos.views.adapters.ViewHolders.CustomViewHolder;
import wan.wanmarcos.views.adapters.ViewHolders.ValuationCourseHeaderHolder;
import wan.wanmarcos.views.adapters.ViewHolders.ValuationHolder;

/**
 * Created by postgrado on 17/10/15.
 */

public class ValuationListAdapter extends CustomDoubleAdapter<Valuation> implements PopUpFragment{

    public ValuationListAdapter(Fragment fragment) {
        super(fragment, Constants.VALUATION_NEW_ITEM, Constants.DETAIL_COURSE_TEACHER);
    }

    @Override
    public CustomViewHolder getObject(View view) {
        return new ValuationHolder(view);
    }

    @Override
    public CustomHeaderViewHolder getObjectHeader(View view) {
        ValuationCourseHeaderHolder valuationCourseHeaderHolder=new ValuationCourseHeaderHolder(view);
        valuationCourseHeaderHolder.setListener(this);
        return valuationCourseHeaderHolder;
    }

    @Override
    public int getContainerID() {
        return Constants.TEACHER_CONTAINER;
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