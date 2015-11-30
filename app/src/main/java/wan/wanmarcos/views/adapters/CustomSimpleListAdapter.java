package wan.wanmarcos.views.adapters;


import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import wan.wanmarcos.utils.Redirection.Redirect;
import wan.wanmarcos.views.adapters.ViewHolders.CustomViewHolder;

/**
 * Created by postgrado on 17/10/15.
 */
public abstract class CustomSimpleListAdapter<T> extends RecyclerView.Adapter<CustomViewHolder>implements RecyclerViewClickListener{
    private Fragment mFragment;
    private int mResource;
    private List<T> objetos=new ArrayList<>();
    private LayoutInflater inflater;
    public CustomSimpleListAdapter(Fragment fragment, int resource){
        mResource=resource;
        mFragment=fragment;
        inflater= LayoutInflater.from(mFragment.getActivity());
    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(mResource, parent, false);
        CustomViewHolder customViewHolder = getObject(view);
        customViewHolder.setListener(this);
        return customViewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        T t=objetos.get(position);
        holder.setElements(t);
    }

    @Override
    public int getItemCount() {
        return objetos.size();
    }
    public void add(T object){
        objetos.add(getItemCount(),object);
        notifyItemInserted(getItemCount());
    }
    public void removeAll(){

    }

    public abstract CustomViewHolder getObject(View view);
    public abstract int getContainerID();
    public abstract String getFragmentName();

    @Override
    public  void recyclerViewListClicked(View v, int  id){
        Redirect.getSingelton().showFragment(mFragment, getContainerID(), getFragmentName());
    }
}