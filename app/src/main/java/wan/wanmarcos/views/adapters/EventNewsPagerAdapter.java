package wan.wanmarcos.views.adapters;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import wan.wanmarcos.R;
import wan.wanmarcos.fragments.EventNewsFragment;
import wan.wanmarcos.models.Event;

/**
 * Created by javier on 27/09/15.
 */
public class EventNewsPagerAdapter extends PagerAdapter{
    FragmentActivity activity;
    List<Event> events;
    EventNewsFragment fragment;

    public EventNewsPagerAdapter(FragmentActivity activity, List<Event> events, EventNewsFragment fragment){
        this.activity = activity;
        this.events = events;
        this.fragment = fragment;
    }

    @Override
    public int getCount() {
        return events.size();
    }

    @Override
    public View instantiateItem(ViewGroup container, final int position){
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        final View view = inflater.inflate(R.layout.event_new_item1, container, false);

        ImageView eventImage = (ImageView)view.findViewById(R.id.event_new_image);
        final TextView eventTitle = (TextView)view.findViewById(R.id.event_new_title);
        System.out.println("CARGANDO");
        System.out.println(position);
        System.out.println(events.get(position).getImgUrl());
        System.out.println(events.get(position).getName());
        Picasso.with(fragment.getActivity()).load(events.get(position).getImgUrl()).fit().centerCrop().into(eventImage, new Callback() {
            @Override
            public void onSuccess() {
                System.out.println("CARGO");
                eventTitle.setText(events.get(position).getName());
            }

            @Override
            public void onError() {
                System.out.println("FALLO");
                view.setVisibility(View.GONE);
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
