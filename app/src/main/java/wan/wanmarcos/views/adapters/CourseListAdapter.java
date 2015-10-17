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
public class CourseListAdapter extends CustomListAdapter<Course> {

    public CourseListAdapter(Context context, int resourceID) {
        super(context,resourceID);
    }
    public CourseListAdapter(Context context, int resourceID, List<Course> objectList) {
        super(context, resourceID, objectList);
    }
    public void setElements(View view,int pos){
        RatingBar bar = (RatingBar) view.findViewById(R.id.course_rating);
        bar.setNumStars(5);
        Course course=getItem(pos);
        bar.setRating(course.getRating());
        TextView faculty = (TextView) view.findViewById(R.id.course_faculty);
        faculty.setText(course.getFaculty());
        TextView name= (TextView) view.findViewById(R.id.course_name);
        name.setText(course.getName());
        TextView mark=(TextView)view.findViewById(R.id.course_mark);
        mark.setText(course.getRating()+"");
    }
}
