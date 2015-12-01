package wan.wanmarcos.views.adapters.ViewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import wan.wanmarcos.R;
import wan.wanmarcos.views.widgets.CircleTransform;

/**
 * Created by soporte on 28/11/15.
 */
public class CourseHeaderHolder extends CustomHeaderViewHolder {
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
    public void setElements() {
        Picasso.with(view.getContext()).load(R.mipmap.backgroundcardteacher).fit().centerCrop().into(teacherCardBackground);
        teacherName.setText("Carlos");
        ratingTeacher.setText("4.0");
        Picasso.with(itemView.getContext()).load("http://lorempixel.com/g/400/200/").transform(new CircleTransform()).into(teacherImage);
    }
}