package wan.wanmarcos.views.adapters.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import wan.wanmarcos.R;
import wan.wanmarcos.models.Course;
import wan.wanmarcos.utils.Storage;
import wan.wanmarcos.views.widgets.CircleTransform;

/**
 * Created by soporte on 28/11/15.
 */
public class CourseHeaderHolder extends CustomViewHolder<Course>{
    private TextView teacherName;
    private TextView ratingTeacher;
    private ImageView teacherCardBackground;
    private ImageView teacherImage;
    private View view;
    public CourseHeaderHolder(View itemView) {
        super(itemView);
        view=itemView;
        teacherCardBackground=(ImageView)itemView.findViewById(R.id.teacher_card_background);
        teacherImage=(ImageView)itemView.findViewById(R.id.profile_teacher_image);
        teacherName=(TextView)itemView.findViewById(R.id.profile_teacher_mame);
        ratingTeacher=(TextView)itemView.findViewById(R.id.profile_teacher_rating);
    }

    @Override
    public void setElements(Course object) {
        Picasso.with(view.getContext()).load("https://newevolutiondesigns.com/images/freebies/google-material-design-wallpaper-17.jpg").fit().centerCrop().into(teacherCardBackground);
        teacherName.setText("Carlos");
        ratingTeacher.setText("4.0");
        Picasso.with(itemView.getContext()).load("http://lorempixel.com/g/400/200/").transform(new CircleTransform()).into(teacherImage);
    }

    @Override
    public void onClick(View v) {
        //TODO
    }
}