package wan.wanmarcos.views.adapters.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by carlos-pc on 29/11/15.
 */
public abstract class CustomHeaderViewHolder extends RecyclerView.ViewHolder{
    public CustomHeaderViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void setElements() ;
}
