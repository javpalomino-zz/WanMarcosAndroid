package wan.wanmarcos.views.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
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
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.views.adapters.ViewHolders.CustomViewHolder;
import wan.wanmarcos.views.adapters.ViewHolders.EventHolder;

/**
 * Created by Francisco on 26/10/2015.
 */


public class EventListAdapter extends CustomSimpleListAdapter<Event>{
    public EventListAdapter(Fragment fragment) {
        super(fragment, Constants.EVENT_NEW_ITEM);
    }

    @Override
    public CustomViewHolder getObject(View view) {
        return new EventHolder(view);
    }

    @Override
    public int getContainerID() {
        return Constants.EVENT_CONTAINER;
    }

    @Override
    public String getFragmentName() {
        return Constants.FRAGMENT_DETAIL_EVENT;
    }

    /*


    @Override
    public void onBindViewHolder(EventListViewHolder holder, int position) {
        Event current = getData().get(position);
        holder.title.setText(current.getName());
        Picasso.with(context)
                .load(current.getImgUrl())
                .into(holder.img);
        if(holder.img.getDrawable()!=null)
        {
            Bitmap bitmap = ((BitmapDrawable)holder.img.getDrawable()).getBitmap();
            Palette p = Palette.from(bitmap).generate();
            holder.img.setBackgroundColor(p.getVibrantColor(0x0000000));
        }
        String startDateAndTime = current.CalendarToString(current.getStartDateTime())+" - "+current.CalendarToString(current.getFinishDateTime());
        holder.dateAndTime.setText(startDateAndTime);
    }

       class EventListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title;
        TextView dateAndTime;
        ImageView img;

        public EventListViewHolder(View itemView) {

            super(itemView);

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


    */
}
