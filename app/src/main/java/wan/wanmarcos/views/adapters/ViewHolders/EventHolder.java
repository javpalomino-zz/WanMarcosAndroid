package wan.wanmarcos.views.adapters.ViewHolders;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.graphics.Palette;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import wan.wanmarcos.R;
import wan.wanmarcos.models.Event;
import wan.wanmarcos.utils.Storage;

/**
 * Created by soporte on 28/11/15.
 */
public class EventHolder extends CustomViewHolder<Event>{
    private TextView title;
    private TextView dateAndTime;
    private ImageView img;
    private int current;

    public EventHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        title = (TextView) itemView.findViewById(R.id.eventListTitle);
        img = (ImageView) itemView.findViewById(R.id.eventListImage);
        dateAndTime =(TextView) itemView.findViewById(R.id.eventListDateAndTime);
    }

    @Override
    public void setElements(Event object) {
        current=object.getEventId();
        title.setText(object.getName());
        Picasso.with(itemView.getContext())
                .load(object.getImgUrl())
                .into(img);
        if(img.getDrawable()!=null)
        {
            Bitmap bitmap = ((BitmapDrawable)img.getDrawable()).getBitmap();
            Palette p = Palette.from(bitmap).generate();
            img.setBackgroundColor(p.getVibrantColor(0x0000000));
        }
        String startDateAndTime = object.CalendarToString(object.getStartDateTime())+" - "+object.CalendarToString(object.getFinishDateTime());
        dateAndTime.setText(startDateAndTime);

    }

    @Override
    public void onClick(View v) {
        Storage.getSingelton().storageData(current,Storage.KEY_EVENT_ID);
        recyclerViewClickListener.recyclerViewListClicked(v,current);
    }
}
