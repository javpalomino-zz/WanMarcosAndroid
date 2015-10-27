package wan.wanmarcos.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import wan.wanmarcos.R;
import wan.wanmarcos.fragments.SectionTeacherRating;
import wan.wanmarcos.managers.ItemAdapterListener;
import wan.wanmarcos.managers.ViewHolderSetters;
import wan.wanmarcos.models.Course;
import wan.wanmarcos.models.Rating;
import wan.wanmarcos.utils.Constants;

/**
 * Created by carlos-pc on 09/10/15.
 */
public class RatingListAdapter extends RecyclerView.Adapter<RatingListAdapter.RatingHolder>{
    private List<Rating>ratings= Collections.emptyList();
    private ItemAdapterListener itemAdapterListener;
    private LayoutInflater layoutInflater;
    public RatingListAdapter(Context context,List<Rating> ratings){
        layoutInflater=LayoutInflater.from(context);
        Log.d("D","Tamaño "+ratings.size());
        this.ratings=ratings;

    }

    @Override
    public RatingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(Constants.RATING_NEW_ITEM,parent,false);
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

    public void setListener(ItemAdapterListener listener) {
        this.itemAdapterListener = listener;
    }

    public class RatingHolder extends RecyclerView.ViewHolder implements ViewHolderSetters<Rating>,View.OnClickListener {
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
        public void onClick(View v) {
            //TODO implement clicklistener
        }

        @Override
        public void setElements(Rating elements) {
            current=elements;
            ratingMark.setText(elements.getRating()+"");
            ratingBar.setRating(elements.getRating());
            ratingType.setText(elements.getType());
        }
    }
}
/*
public class RatingListAdapter  extends CustomListAdapter<Rating> {

    public RatingListAdapter(Context context,int resourceID,List<Rating> listObjects) {
        super(context,resourceID,listObjects);
    }
    public RatingListAdapter(Context context,int resourceID){
        super(context,resourceID);
    }

    public void setElements(View view,int pos){
        RatingBar bar = (RatingBar) view.findViewById(R.id.rating_progress);
        bar.setNumStars(5);
        Rating rating=getItem(pos);
        bar.setRating(rating.getRating());
        TextView type = (TextView) view.findViewById(R.id.rating_type);
        type.setText(rating.getType());
        TextView quantity = (TextView) view.findViewById(R.id.rating_mark);
        quantity.setText(rating.getRating()+"");
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }
}*/
