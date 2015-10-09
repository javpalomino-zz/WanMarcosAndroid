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
import wan.wanmarcos.models.Course;

/**
 * Created by carlos-pc on 09/10/15.
 */
public class CourseListAdapter extends ArrayAdapter<Course> {
    private Context context;
    private List<Course> ratings;

    public CourseListAdapter(Context context, List<Course> objects) {
        super(context, R.layout.rating_new_item,objects);
        this.context=context;
        this.ratings=objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView=layoutInflater.inflate(R.layout.course_new_item,parent,false);
        setElements(rowView, position);
        return rowView;
    }
    public void setElements(View view,int pos){
        RatingBar bar = (RatingBar) view.findViewById(R.id.course_rating);
        bar.setNumStars(5);
        bar.setRating(ratings.get(pos).getRating());
        TextView faculty = (TextView) view.findViewById(R.id.course_faculty);
        faculty.setText(ratings.get(pos).getFaculty());
        TextView name= (TextView) view.findViewById(R.id.course_name);
        name.setText(ratings.get(pos).getName());
        TextView mark=(TextView)view.findViewById(R.id.course_mark);
    }
}
