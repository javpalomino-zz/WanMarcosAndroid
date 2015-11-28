package wan.wanmarcos.views.adapters;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.utils.Redirection.Redirect;
import wan.wanmarcos.views.adapters.ViewHolders.CustomViewHolder;

/**
 * Created by soporte on 28/11/15.
 */
public abstract class CustomDoubleAdapter <T> extends RecyclerView.Adapter<CustomViewHolder>implements RecyclerViewClickListener {
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_HEADER = 0;
    private Fragment mFragment;
    private boolean flag;
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
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        CustomViewHolder customViewHolder=null;
        if(viewType==TYPE_HEADER){
            view=inflater.inflate(mResourceHeader,parent,false);
            customViewHolder=getObjectHeader(view);
        }
        else if(viewType==TYPE_ITEM){
            view=inflater.inflate(mResource,parent,false);
            customViewHolder=getObject(view);
        }
        customViewHolder.setListener(this);
        return customViewHolder;
    }


    @Override
    public int getItemViewType(int position) {
        if (!isPositionHeader(position)){
            flag=true;
            return TYPE_ITEM;
        }
        flag=false;
        return TYPE_HEADER;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        if(flag){
            T t=objetos.get(position-1);
            holder.setElements(t);
        }
        else{
            holder.setElements(null);
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
    public abstract CustomViewHolder getObjectHeader(View view);
    public abstract int getContainerID();
    public abstract String getFragmentName();

    @Override
    public void recyclerViewListClicked(View v, int id) {
        Redirect.getSingelton().showFragment(mFragment, getContainerID(), getFragmentName());
    }
}
