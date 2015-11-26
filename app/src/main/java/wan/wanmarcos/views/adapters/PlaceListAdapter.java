package wan.wanmarcos.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import wan.wanmarcos.R;
import wan.wanmarcos.fragments.Place_SectionListFragment;
import wan.wanmarcos.managers.ItemAdapterListener;
import wan.wanmarcos.managers.ViewHolderSetters;
import wan.wanmarcos.models.Place;
import wan.wanmarcos.models.Teacher;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.views.widgets.CircleTransform;

/**
 * Created by soporte on 25/11/15.
 */
public class PlaceListAdapter extends RecyclerView.Adapter<PlaceListAdapter.PlaceHolder> {
    private LayoutInflater inflater;
    private List<Place> places= new ArrayList<>();
    private ItemAdapterListener itemAdapterListener;

    public PlaceListAdapter(Context context){
        inflater= LayoutInflater.from(context);
    }
    @Override
    public PlaceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(Constants.PLACE_NEW_ITEM, parent, false);
        PlaceHolder teacherCustomer = new PlaceHolder (view);
        return teacherCustomer;
    }

    @Override
    public void onBindViewHolder(PlaceHolder holder, int position) {
        Place teacher=places.get(position);
        holder.setElements(teacher);
    }

    @Override
    public int getItemCount() {
        return places.size();
    }
    public synchronized void add(Place place){
        places.add(getItemCount(), place);
        notifyItemInserted(getItemCount());
    }

    public void setListener(ItemAdapterListener listener) {
        this.itemAdapterListener = listener;
    }

    public class PlaceHolder extends RecyclerView.ViewHolder implements ViewHolderSetters<Place>,View.OnClickListener {
        private ImageView imageView;
        private TextView placeName;
        private TextView placeDistance;
        private TextView placeDescription;
        private TextView placeReference;
        private TextView placeRating;
        private View vista;
        private Place current;

        public PlaceHolder(View itemView) {
            super(itemView);
            vista=itemView;
            itemView.setOnClickListener(this);
            imageView = (ImageView) itemView.findViewById(R.id.place_item_image);
            placeName = (TextView) itemView.findViewById(R.id.place_item_name);
            placeReference = (TextView) itemView.findViewById(R.id.place_reference_place);
            placeDescription = (TextView) itemView.findViewById(R.id.places_item_description);
            placeDistance=(TextView)itemView.findViewById(R.id.places_item_distance);
            placeRating=(TextView)itemView.findViewById(R.id.rating_number_label);

        }
        public void setElements(Place place){
            current=place;
            placeDistance.setText("a "+place.getDistance()+" mts");
            placeName.setText(place.getPlaceName());
            placeReference.setText(place.getReferencePlace());
            placeDescription.setText(place.getReferences());
            placeRating.setText(String.valueOf(place.getRatingPlace()));
            Picasso.with(vista.getContext()).load("http://lorempixel.com/300/300/").transform(new CircleTransform()).into(imageView);
        }

        @Override
        public void onClick(View v) {
            Log.d("D", "DASD");
            itemAdapterListener.itemClicked(v,current);
        }
    }
}
