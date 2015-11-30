package wan.wanmarcos.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import wan.wanmarcos.R;
import wan.wanmarcos.managers.ItemAdapterListener;
import wan.wanmarcos.managers.ViewHolderSetters;
import wan.wanmarcos.models.Rating;
import wan.wanmarcos.utils.Constants;

/**
 * Created by carlos-pc on 09/10/15.
 */
//TODO
public class RatingListAdapter extends RecyclerView.Adapter<RatingListAdapter.RatingHolder>{
    private List<Rating>ratings= new ArrayList<>();
    private LayoutInflater layoutInflater;

    public RatingListAdapter(Context context){
        layoutInflater= LayoutInflater.from(context);
    }

    @Override
    public RatingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(Constants.RATING_NEW_ITEM, parent, false);
        RatingHolder ratingHolder=new RatingHolder(view);
        return ratingHolder;
    }

    @Override
    public void onBindViewHolder(RatingHolder holder, int position) {
        Rating rating=ratings.get(position);
        holder.setElements(rating);
    }

    @Override
    public int getItemCount() {
        return ratings.size();
    }
    public synchronized void add(Rating rating){
        ratings.add(getItemCount(),rating);
        notifyItemInserted(getItemCount());
    }


    public class RatingHolder extends RecyclerView.ViewHolder implements ViewHolderSetters<Rating>{
        private TextView ratingType;
        private RatingBar ratingBar;
        private TextView ratingMark;
        private Rating current;
        public RatingHolder(View itemView) {
            super(itemView);
            ratingType=(TextView) itemView.findViewById(R.id.rating_type);
            ratingBar=(RatingBar) itemView.findViewById(R.id.rating_progress);
            ratingMark=(TextView) itemView.findViewById(R.id.rating_mark);
        }
        @Override
        public void setElements(Rating elements) {
            current=elements;
            ratingMark.setText(elements.getRating() + "");
            ratingBar.setRating(elements.getRating());
            ratingType.setText(elements.getType());
            addListeners();
        }
        private void addListeners(){
            ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    if(fromUser) {
                        ratingMark.setText(String.valueOf(rating));
                        current.setRating(rating    );
                    }
                }
            });
        }

    }
    public boolean isChanged(int pos){
        Log.d("D",ratings.get(pos).getRating()+"-");
        if(ratings.get(pos).getRating()!=0){
            return true;
        }
        return false;
    }
}
