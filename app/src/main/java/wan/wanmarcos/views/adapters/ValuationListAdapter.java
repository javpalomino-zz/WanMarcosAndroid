package wan.wanmarcos.views.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import wan.wanmarcos.fragments.PopupCommentFragment;
import wan.wanmarcos.models.Valuation;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.utils.RestClient;
import wan.wanmarcos.utils.Storage;
import wan.wanmarcos.views.adapters.ViewHolders.CustomHeaderViewHolder;
import wan.wanmarcos.views.adapters.ViewHolders.CustomViewHolder;
import wan.wanmarcos.views.adapters.ViewHolders.ValuationCourseHeaderHolder;
import wan.wanmarcos.views.adapters.ViewHolders.ValuationHolder;

/**
 * Created by postgrado on 17/10/15.
 */

public class ValuationListAdapter extends CustomDoubleAdapter<Valuation> implements PopUpFragment{
    private RestClient restClient;
    String token="Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI1IiwiaXNzIjoiaHR0cDpcL1wvNTIuODkuMTI0LjBcL2FwaVwvdjFcL2F1dGhlbnRpY2F0ZSIsImlhdCI6IjE0NDcxMDQ5MzQiLCJleHAiOiIxNDU1NzQ0OTM0IiwibmJmIjoiMTQ0NzEwNDkzNCIsImp0aSI6IjcxZjM2NjgwN2EwZTIyZTY1ODM0OWYzZDMyOTcxNDQ1In0.gQK_MjKSRx6BhVCsy0CyhvJTEZB-wK2EWvKKJrDpUm4";
    public ValuationListAdapter(Fragment fragment) {
        super(fragment, Constants.VALUATION_NEW_ITEM, Constants.DETAIL_COURSE_TEACHER);
        restClient=new RestClient(fragment.getActivity());
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
    public void close(final String message, final float score) {
        Call<JsonElement> jsonElementCall=restClient.getConsumerService().commentCourse(token,Integer.parseInt(Storage.getSingelton().getInfo(Storage.KEY_COURSE_ID)),Integer.parseInt(Storage.getSingelton().getInfo(Storage.KEY_TEACHER_ID)), score, message);
        jsonElementCall.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Response<JsonElement> response, Retrofit retrofit) {
                JsonObject jsonObject=response.body().getAsJsonObject();
                add(new Valuation(Constants.name,null, message, score));
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}