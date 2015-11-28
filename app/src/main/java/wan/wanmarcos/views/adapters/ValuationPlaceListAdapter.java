package wan.wanmarcos.views.adapters;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import wan.wanmarcos.R;
import wan.wanmarcos.managers.ItemAdapterListener;
import wan.wanmarcos.managers.ViewHolderSetters;
import wan.wanmarcos.models.Valuation;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.utils.Storage;
import wan.wanmarcos.views.widgets.CircleTransform;

/**
 * Created by soporte on 26/11/15.
 */
public class ValuationPlaceListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final int TYPE_ITEM =1 ;
    private static final int TYPE_HEADER = 0;
    private LayoutInflater inflater;
    private Fragment mFragment;
    private List<Valuation> valuations= new ArrayList<>();
    private ItemAdapterListener itemAdapterListener;

    public ValuationPlaceListAdapter(Fragment fragment,Context context){
        mFragment=fragment;
        inflater= LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==TYPE_ITEM){
            View view=inflater.inflate(Constants.VALUATION_NEW_ITEM,parent,false);
            ValuationPlaceHolder courseHolder=new ValuationPlaceHolder (view);
            return courseHolder;
        }
        else if(viewType==TYPE_HEADER){
            View view=inflater.inflate(Constants.DETAIL_PLACE,parent,false);
            ValuationPlaceHeaderHolder courseHeaderHolder=new ValuationPlaceHeaderHolder (view);
            return courseHeaderHolder;
        }
        return null;
    }
    public synchronized void add(Valuation teacher){
        valuations.add(getItemCount(),teacher);
        notifyItemInserted(getItemCount());
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ValuationPlaceHolder) {
            Valuation valuation=valuations.get(position-1);
            ((ValuationPlaceHolder)holder).setElements(valuation);
        } else if (holder instanceof ValuationPlaceHeaderHolder) {
            ((ValuationPlaceHeaderHolder)holder).setElements();
        }
    }
    @Override
    public int getItemViewType(int position) {
        if (!isPositionHeader(position))
            return TYPE_ITEM;

        return TYPE_HEADER;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }
    @Override
    public int getItemCount() {
        return valuations.size();
    }
    public class ValuationPlaceHeaderHolder extends RecyclerView.ViewHolder {
        private TextView placeName;
        private TextView descripctionPlace;
        private TextView referencePlace;
        private TextView ratingPlace;
        private ImageView placeImage;
        private ImageView placeCardBackground;
        private FloatingActionButton floatingActionButton;
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

        public void setElements() {
            Picasso.with(mFragment.getActivity()).load("https://newevolutiondesigns.com/images/freebies/google-material-design-wallpaper-17.jpg").fit().centerCrop().into(placeCardBackground);
            Picasso.with(itemView.getContext()).load(Storage.getSingelton().getInfo(mFragment, Storage.KEY_TEACHER_IMAGE)).transform(new CircleTransform()).into(placeImage);
            placeName.setText("Chio");

        }
        public void addListeners(){
            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemAdapterListener.addClicked(Constants.FRAGMENT_PROFILE_TEACHER);

                }
            });
        }
    }

    public class ValuationPlaceHolder extends RecyclerView.ViewHolder implements ViewHolderSetters<Valuation>,View.OnClickListener {
        private Valuation valuation;
        private View view;
        private ImageView userImage;
        private RatingBar userRate;
        private TextView userValuation;
        private TextView userName;
        public ValuationPlaceHolder(View itemView) {
            super(itemView);
            userName=(TextView) itemView.findViewById(R.id.valuation_user_name);
            userImage=(ImageView) itemView.findViewById(R.id.userImage);
            userValuation=(TextView) itemView.findViewById(R.id.userComments);
            //userRate=(RatingBar) itemView.findViewById(R.id.userRate);
            view=itemView;
        }

        @Override
        public void onClick(View v) {
            itemAdapterListener.itemClicked(v,getAdapterPosition());
        }

        @Override
        public void setElements(Valuation elements) {
            valuation=elements;

            userName.setText(elements.getUserName());
            userValuation.setText(elements.getUserComment());
            ColorGenerator generator = ColorGenerator.MATERIAL;
            int color = generator.getColor(userName.getText().charAt(0));
            TextDrawable.IBuilder builder = TextDrawable.builder().round();
            TextDrawable textDrawable = builder.build(userName.getText().toString().charAt(0)+"", color);
            userImage.setImageDrawable(textDrawable);
            //userRate.setRating(elements.getUserTotalMark());
            //Picasso.with(view.getContext()).load(elements.getUserImage()).into(userImage);
        }
    }
}
