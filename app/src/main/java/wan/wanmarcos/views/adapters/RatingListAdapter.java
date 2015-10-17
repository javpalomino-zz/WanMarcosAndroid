package wan.wanmarcos.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import wan.wanmarcos.R;
import wan.wanmarcos.models.Rating;

/**
 * Created by carlos-pc on 09/10/15.
 */
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
}
