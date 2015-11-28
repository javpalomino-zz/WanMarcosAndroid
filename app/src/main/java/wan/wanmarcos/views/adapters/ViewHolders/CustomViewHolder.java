package wan.wanmarcos.views.adapters.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;

import wan.wanmarcos.views.adapters.RecyclerViewClickListener;

/**
 * Created by soporte on 28/11/15.
 */
public abstract class CustomViewHolder<T> extends RecyclerView.ViewHolder implements View.OnClickListener{
    private RecyclerViewClickListener recyclerViewClickListener;

    public CustomViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void setElements(T object);

    public void setListener(RecyclerViewClickListener listener){
        recyclerViewClickListener=listener;
    }
}
