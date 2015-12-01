package wan.wanmarcos.views.adapters.ViewHolders;

import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import wan.wanmarcos.R;
import wan.wanmarcos.views.adapters.PopUpFragment;
import wan.wanmarcos.views.widgets.CircleTransform;

/**
 * Created by soporte on 28/11/15.
 */
public class ValuationCourseHeaderHolder extends CustomHeaderViewHolder {
    private TextView teacherName;
    private TextView courseName;
    private TextView facultyName;
    private ImageView teacherImage;
    private ImageView teacherCardBackground;
    private FloatingActionButton floatingActionButton;
    private View view;
    private PopUpFragment addClicked;
    public ValuationCourseHeaderHolder(View itemView) {
        super(itemView);
        view=itemView;
        teacherCardBackground= (ImageView) itemView.findViewById(R.id.teacher_course_card_background);
        teacherName=(TextView)itemView.findViewById(R.id.profile_course_teacher_name);
        courseName=(TextView)itemView.findViewById(R.id.profile_course_course_name);
        facultyName=(TextView)itemView.findViewById(R.id.profile_course_faculty_name);
        teacherImage=(ImageView)itemView.findViewById(R.id.profile_course_teacher_image);
        floatingActionButton=(FloatingActionButton)itemView.findViewById(R.id.addComment);
    }

    @Override
    public void setElements() {
        Picasso.with(view.getContext()).load(R.mipmap.backgroundcardteacher).fit().centerCrop().into(teacherCardBackground);
        Picasso.with(itemView.getContext()).load("http://lorempixel.com/g/400/200/").transform(new CircleTransform()).into(teacherImage);
        facultyName.setText("d");
        teacherName.setText("carlos");
        courseName.setText("Cato");
        addListeners();
    }
    public void addListeners(){
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addClicked.popUp();
            }
        });
    }

    public void setListener(PopUpFragment valuationListAdapter) {
        addClicked=valuationListAdapter;

    }
}
