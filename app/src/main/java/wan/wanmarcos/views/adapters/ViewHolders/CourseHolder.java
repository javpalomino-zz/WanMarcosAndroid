package wan.wanmarcos.views.adapters.ViewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import wan.wanmarcos.R;
import wan.wanmarcos.models.Course;
import wan.wanmarcos.views.adapters.RecyclerViewClickListener;

/**
 * Created by soporte on 28/11/15.
 */
public class CourseHolder extends CustomViewHolder<Course> {
    private TextView courseName;
    private TextView courseFaculty;
    private View vista;
    private int current;
    private ImageView initialLetterImage;
    private RecyclerViewClickListener itemListAdapter;

    public CourseHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        courseName= (TextView) itemView.findViewById(R.id.profile_course_course_name);
        courseFaculty=(TextView) itemView.findViewById(R.id.course_faculty);
        initialLetterImage=(ImageView) itemView.findViewById(R.id.letterImageBackground);
        vista=itemView;
    }

    @Override
    public void setElements(Course object) {
        current=object.getId();
        courseName.setText(object.getName());
        courseFaculty.setText(object.getFaculty());
        ColorGenerator generator = ColorGenerator.MATERIAL;
        int color = generator.getColor(courseName.getText().charAt(0));
        TextDrawable.IBuilder builder = TextDrawable.builder().round();
        TextDrawable textDrawable = builder.build(courseName.getText().toString().charAt(0)+"", color);
        initialLetterImage.setImageDrawable(textDrawable);
    }

    @Override
    public void onClick(View v) {
        itemListAdapter.recyclerViewListClicked(v,current);
    }
}