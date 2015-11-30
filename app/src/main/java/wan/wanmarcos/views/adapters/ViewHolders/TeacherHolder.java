package wan.wanmarcos.views.adapters.ViewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import wan.wanmarcos.R;
import wan.wanmarcos.models.Teacher;
import wan.wanmarcos.views.adapters.RecyclerViewClickListener;
import wan.wanmarcos.views.widgets.CircleTransform;

/**
 * Created by soporte on 28/11/15.
 */
public class  TeacherHolder extends CustomViewHolder<Teacher> {
    private TextView teacherName;
    private ImageView teacherImage;
    private TextView teacherCourses;
    private View vista;
    private int current;

    public TeacherHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        teacherName= (TextView) itemView.findViewById(R.id.teacher_item_name);
        teacherImage=(ImageView)itemView.findViewById(R.id.teacher_item_picture);
        teacherCourses=(TextView)itemView.findViewById(R.id.teacher_item_courses);
        vista=itemView;
    }

    @Override
    public void setElements(Teacher elements) {
        current=elements.getId();
        teacherName.setText(elements.getName());
        Picasso.with(vista.getContext()).load(elements.getImageUrl()).transform(new CircleTransform()).into(teacherImage);
    }


    @Override
    public void onClick(View v) {
        recyclerViewClickListener.recyclerViewListClicked(v,current);
    }
}