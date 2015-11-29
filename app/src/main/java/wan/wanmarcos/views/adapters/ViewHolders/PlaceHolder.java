package wan.wanmarcos.views.adapters.ViewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import wan.wanmarcos.R;
import wan.wanmarcos.models.Place;
import wan.wanmarcos.views.widgets.CircleTransform;

/**
 * Created by soporte on 28/11/15.
 */
public class PlaceHolder extends CustomViewHolder<Place>{
    private ImageView imageView;
    private TextView placeName;
    private TextView placeDistance;
    private TextView placeDescription;
    private TextView placeReference;
    private TextView placeRating;
    private View vista;
    private int current;

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

    public void setElements(Place object) {
        current=object.getIdPlace();
        placeDistance.setText("a "+object.getDistance()+" mts");
        placeName.setText(object.getPlaceName());
        placeReference.setText(object.getReferencePlace());
        placeDescription.setText(object.getReferences());
        placeRating.setText(String.valueOf(object.getRatingPlace()));
        Picasso.with(vista.getContext()).load("http://lorempixel.com/300/300/").transform(new CircleTransform()).into(imageView);
    }

    @Override
    public void onClick(View v) {
        recyclerViewClickListener.recyclerViewListClicked(v,current);
    }
}
