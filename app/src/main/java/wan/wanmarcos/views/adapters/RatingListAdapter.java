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
public class RatingListAdapter  extends ArrayAdapter<Rating> {
    private Context context;
    private List<Rating> ratings;

    public RatingListAdapter(Context context, List<Rating> objects) {
        super(context, R.layout.rating_new_item,objects);
        this.context=context;
        this.ratings=objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView=layoutInflater.inflate(R.layout.rating_new_item,parent,false);
        setElements(rowView, position);
        return rowView;
    }
    public void setElements(View view,int pos){
        RatingBar bar = (RatingBar) view.findViewById(R.id.rating_progress);
        bar.setNumStars(5);
        bar.setRating(ratings.get(pos).getRating());
        TextView type = (TextView) view.findViewById(R.id.rating_type);
        type.setText(ratings.get(pos).getType());
        TextView quantity = (TextView) view.findViewById(R.id.rating_mark);
        quantity.setText(ratings.get(pos).getRating()+"");
    }
}
