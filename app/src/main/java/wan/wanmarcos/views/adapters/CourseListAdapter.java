package wan.wanmarcos.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import wan.wanmarcos.R;
import wan.wanmarcos.managers.ItemAdapterListener;
import wan.wanmarcos.managers.ViewHolderSetters;
import wan.wanmarcos.models.Course;
import wan.wanmarcos.utils.Constants;

/**
 * Created by carlos-pc on 09/10/15.
 */
public class CourseListAdapter extends RecyclerView.Adapter<CourseListAdapter.CourseHolder>{
    private List<Course>courses= Collections.emptyList();
    private ItemAdapterListener itemAdapterListener;
    private LayoutInflater layoutInflater;
    public CourseListAdapter(Context context,List<Course> courses){
        layoutInflater=LayoutInflater.from(context);
        this.courses=courses;

    }
    @Override
    public CourseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(Constants.COURSE_NEW_ITEM,parent,false);
        CourseHolder courseHolder=new CourseHolder(view);
        return courseHolder;
    }

    @Override
    public void onBindViewHolder(CourseHolder holder, int position) {
        Course course=courses.get(position);
        holder.setElements(course);
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }
    public void setListener(ItemAdapterListener listener){
        this.itemAdapterListener =listener;
    }

    public class CourseHolder extends RecyclerView.ViewHolder implements ViewHolderSetters<Course>,View.OnClickListener{
        private TextView courseName;
        private TextView courseFaculty;
        private RatingBar courseRating;
        private TextView courseMark;
        private View vista;
        private Course current;
        public CourseHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            courseName= (TextView) itemView.findViewById(R.id.profile_course_course_name);
            courseFaculty=(TextView) itemView.findViewById(R.id.course_faculty);
            //courseRating=(RatingBar) itemView.findViewById(R.id.course_rating);
            //courseMark=(TextView) itemView.findViewById(R.id.course_mark);
            vista=itemView;
        }

        @Override
        public void onClick(View v) {
            itemAdapterListener.itemClicked(v,current);
        }

        @Override
        public void setElements(Course elements) {
            current=elements;
            courseName.setText(elements.getName());
            courseFaculty.setText(elements.getFaculty());
            //courseMark.setText(elements.getRating()+"");
            //courseRating.setRating(elements.getRating());
        }
    }
}