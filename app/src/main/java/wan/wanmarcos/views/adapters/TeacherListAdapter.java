package wan.wanmarcos.views.adapters;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;
import java.util.StringTokenizer;

import wan.wanmarcos.R;
import wan.wanmarcos.fragments.TeacherListFragment;
import wan.wanmarcos.models.Teacher;
import wan.wanmarcos.views.widgets.CircleTransform;


public class TeacherListAdapter extends CustomListAdapter<Teacher> {
    public TeacherListAdapter(Context context,int resourceID,List<Teacher> listObjects ){
        super(context,resourceID,listObjects);
    }
    public TeacherListAdapter(Context context, int resourceID) {
        super(context, resourceID);
    }

    public void setElements(View view,int pos){
        TextView teacherName=(TextView)view.findViewById(R.id.teacher_name);
        ImageView teacherImage=(ImageView)view.findViewById(R.id.teacher_image);
        RatingBar teacherRaiting=(RatingBar)view.findViewById(R.id.teacher_rating);
        TextView teacherFaculty=(TextView)view.findViewById(R.id.teacher_faculty);
        TextView teacherDescription=(TextView)view.findViewById(R.id.teacher_description);
        Teacher teacher=getItem(pos);
        teacherName.setText(R.string.welcome);
        teacherFaculty.setText(R.string.welcome + teacher.getFaculties());
        teacherRaiting.setRating(teacher.getRaiting());
        String nombre2="\t"+teacher.getDescription();
        teacherDescription.setText(nombre2);
        //teacherDescription.setText((char)(R.string.welcome)+ teacher.getDescription());
        String nombre="\t\t\t\t";

        //teacherDescription.setText("\t\t\t\t"+teacher.getDescription());
        teacherDescription.setText(teacher.getDescription());
        Picasso.with(getContext()).load("http://lorempixel.com/350/230/").transform(new CircleTransform()).into(teacherImage);
    }

}