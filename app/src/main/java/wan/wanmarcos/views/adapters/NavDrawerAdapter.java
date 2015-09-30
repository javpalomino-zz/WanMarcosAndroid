package wan.wanmarcos.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;
import wan.wanmarcos.R;
import wan.wanmarcos.models.NavDrawerLink;

/**
 * Created by Francisco on 26/09/2015.
 */
public class NavDrawerAdapter extends RecyclerView.Adapter<NavDrawerAdapter.NavDrawerViewHolder>{

    private LayoutInflater inflater;
    private List<NavDrawerLink> data = Collections.emptyList();
    private Context context;
    private ClickListener clickListener;

    public NavDrawerAdapter(Context context,List<NavDrawerLink> data){

         inflater = LayoutInflater.from(context);
        this.data=data;
        this.context=context;
    }

    @Override
    public NavDrawerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view =  inflater.inflate(R.layout.navdrawer_row, parent, false);
        NavDrawerViewHolder holder = new NavDrawerViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(NavDrawerViewHolder holder, int position) {
        NavDrawerLink current = data.get(position);
        holder.title.setText(current.getTitle());
        holder.img.setImageResource(current.getIconId());
    }

    public void setClickListener(ClickListener clickListener){
        this.clickListener=clickListener;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class NavDrawerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title;
        ImageView img;

        public NavDrawerViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            title = (TextView) itemView.findViewById(R.id.navDrawerRowText);
            img = (ImageView) itemView.findViewById(R.id.navDrawerRowIcon);
        }


        @Override
        public void onClick(View v) {

            if(clickListener!=null)
            {
                clickListener.itemClicked(v,getPosition());
            }
        }

    }
    public interface ClickListener{
        public void itemClicked(View view,int position);
    }

}
