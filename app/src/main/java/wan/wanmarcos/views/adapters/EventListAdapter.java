package wan.wanmarcos.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import wan.wanmarcos.R;
import wan.wanmarcos.models.Event;

/**
 * Created by Francisco on 26/10/2015.
 */
public class EventListAdapter extends  RecyclerView.Adapter<EventListAdapter.EventListViewHolder>{
    private LayoutInflater inflater;
    private List<Event> data = Collections.emptyList();
    private Context context;
    private ClickListener clickListener;

    public EventListAdapter(Context context,List<Event> data){

        inflater = LayoutInflater.from(context);
        this.data=data;
        this.context=context;
    }

    @Override
    public EventListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =  inflater.inflate(R.layout.event_list_item, parent, false);
        EventListViewHolder holder = new EventListViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(EventListViewHolder holder, int position) {
        Event current = data.get(position);
        holder.title.setText(current.getName());
        holder.img.setImageResource(current.getIconId());
        String startDateAndTime = current.CalendarToString(current.getStartDateTime());
        holder.dateAndTime.setText(startDateAndTime);
    }

    public void setClickListener(ClickListener clickListener){

        this.clickListener=clickListener;
    }

    @Override
    public int getItemCount() {

        return data.size();
    }

    class EventListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title;
        TextView dateAndTime;
        ImageView img;

        public EventListViewHolder(View itemView) {

            super(itemView);
            itemView.setOnClickListener(this);
            title = (TextView) itemView.findViewById(R.id.eventListTitle);
            img = (ImageView) itemView.findViewById(R.id.eventListImage);
            dateAndTime =(TextView) itemView.findViewById(R.id.eventListDateAndTime);
        }


        @Override
        public void onClick(View v) {

            if(clickListener!=null)
            {
                clickListener.itemClicked(v,getPosition());
            }
        }

    }

    public Event getItemAtPos(int pos)
    {
        return data.get(pos);
    }


    public interface ClickListener{
        public void itemClicked(View view,int position);
    }
}
