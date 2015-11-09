package wan.wanmarcos.views.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import wan.wanmarcos.R;
import wan.wanmarcos.models.Event;

/**
 * Created by Francisco on 26/10/2015.
 */
public class EventListAdapter extends  RecyclerView.Adapter<EventListAdapter.EventListViewHolder>{
    private LayoutInflater inflater;
    private List<Event> data = new ArrayList<>();
    private Context context;
    private ClickListener clickListener;

    public EventListAdapter(Context context){
        inflater = LayoutInflater.from(context);
        this.context=context;
    }

    @Override
    public EventListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =  inflater.inflate(R.layout.event_list_item, parent, false);
        EventListViewHolder holder = new EventListViewHolder(view);
        Bitmap bitmap = ((BitmapDrawable)holder.img.getDrawable()).getBitmap();
        Palette p = Palette.from(bitmap).generate();
        holder.img.setBackgroundColor(p.getVibrantColor(0x0000000));
        return holder;
    }

    @Override
    public void onBindViewHolder(EventListViewHolder holder, int position) {
        Event current = getData().get(position);
        holder.title.setText(current.getName());
        Picasso.with(context)
                .load(current.getImgUrl())
                .into(holder.img);
        String startDateAndTime = current.CalendarToString(current.getStartDateTime())+" - "+current.CalendarToString(current.getFinishDateTime());
        holder.dateAndTime.setText(startDateAndTime);
    }

    public void setClickListener(ClickListener clickListener){

        this.clickListener=clickListener;
    }

    @Override
    public int getItemCount() {

        return getData().size();
    }

    public List<Event> getData() {
        return data;
    }

    public void setData(List<Event> data) {
        this.data = data;
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
        return getData().get(pos);
    }


    public interface ClickListener{
        public void itemClicked(View view,int position);
    }
    public void addAll(final List<Event>  events) {
        final int currentCount = data.size();
        synchronized(data) {
            data.addAll(events);
        }
        if (Looper.getMainLooper() == Looper.myLooper()) {
            notifyItemRangeInserted(currentCount, events.size());
        } else {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    notifyItemRangeInserted(currentCount, events.size());
                }
            });
        }
    }



}
