package wan.wanmarcos.views.adapters;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import wan.wanmarcos.utils.Redirection.Redirect;
import wan.wanmarcos.views.adapters.ViewHolders.CustomHeaderViewHolder;
import wan.wanmarcos.views.adapters.ViewHolders.CustomViewHolder;

/**
 * Created by soporte on 28/11/15.
 */
public abstract class CustomDoubleAdapter <T> extends RecyclerView.Adapter<RecyclerView.ViewHolder>implements RecyclerViewClickListener {
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_HEADER = 0;
    private Fragment mFragment;
    private int mResource,mResourceHeader;
    private List<T> objetos=new ArrayList<>();
    private LayoutInflater inflater;
    public CustomDoubleAdapter(Fragment fragment, int resource,int resourceHeader){
        mResource=resource;
        mResourceHeader=resourceHeader;
        mFragment=fragment;
        inflater= LayoutInflater.from(mFragment.getActivity());
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType==TYPE_HEADER){
            View view=inflater.inflate(mResourceHeader,parent,false);
            CustomHeaderViewHolder customViewHolder=getObjectHeader(view);
            return customViewHolder;
        }
        else if(viewType==TYPE_ITEM){
            View view=inflater.inflate(mResource,parent,false);
            CustomViewHolder customViewHolder=getObject(view);
            customViewHolder.setListener(this);
            return customViewHolder;
        }

        return null;
    }


    @Override
    public int getItemViewType(int position) {
        if (!isPositionHeader(position)){
            return TYPE_ITEM;
        }
        return TYPE_HEADER;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof CustomViewHolder){
            T t=objetos.get(position-1);
            ((CustomViewHolder)holder).setElements(t);
        }
        else if(holder instanceof CustomHeaderViewHolder){
            ((CustomHeaderViewHolder)holder).setElements();
        }

    }

    @Override
    public int getItemCount() {
        return objetos.size()+1;
    }
    public void add(T object){
        objetos.add(getItemCount()-1,object);
        notifyItemInserted(getItemCount()-1);
    }
    public abstract CustomViewHolder getObject(View view);
    public abstract CustomHeaderViewHolder getObjectHeader(View view);
    public abstract int getContainerID();
    public abstract String getFragmentName();

    @Override
    public void recyclerViewListClicked(View v, int id) {
        Redirect.getSingelton().showFragment(mFragment, getContainerID(), getFragmentName());
    }

    public Fragment getFragment(){
        return mFragment;
    }
}
