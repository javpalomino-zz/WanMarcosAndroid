package wan.wanmarcos.views.adapters.ViewHolders;

import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import wan.wanmarcos.R;
import wan.wanmarcos.views.adapters.PopUpFragment;
import wan.wanmarcos.views.widgets.CircleTransform;

/**
 * Created by carlos-pc on 29/11/15.
 */
public class ValuationPlaceHeaderHolder extends CustomHeaderViewHolder{
    private TextView placeName;
    private TextView descripctionPlace;
    private TextView referencePlace;
    private TextView ratingPlace;
    private ImageView placeImage;
    private ImageView placeCardBackground;
    private FloatingActionButton floatingActionButton;
    private PopUpFragment addClicked;
    public ValuationPlaceHeaderHolder(View itemView) {
        super(itemView);
        placeCardBackground= (ImageView) itemView.findViewById(R.id.places_card_background);
        placeImage=(ImageView)itemView.findViewById(R.id.profile_place_image);
        placeName=(TextView)itemView.findViewById(R.id.profile_place_name);
        referencePlace=(TextView)itemView.findViewById(R.id.profile_place_description);
        descripctionPlace=(TextView)itemView.findViewById(R.id.profile_reference_place_name);
        ratingPlace=(TextView)itemView.findViewById(R.id.profile_place_rating);
        floatingActionButton=(FloatingActionButton)itemView.findViewById(R.id.addComment);
    }

    @Override
    public void setElements() {
        Picasso.with(itemView.getContext()).load(R.mipmap.backgroundcardplace).fit().centerCrop().into(placeCardBackground);
        Picasso.with(itemView.getContext()).load("http://lorempixel.com/350/230/").transform(new CircleTransform()).into(placeImage);
        placeName.setText("Chio");
        addEvents();
    }
    public void addEvents(){
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addClicked.popUp();
            }
        });
    }
    public void setListener(PopUpFragment valuationListAdapter) {
        addClicked=valuationListAdapter;

    }
}
