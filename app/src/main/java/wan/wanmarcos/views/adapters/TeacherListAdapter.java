package wan.wanmarcos.views.adapters;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;
import wan.wanmarcos.R;
import wan.wanmarcos.fragments.TeacherListFragment;
import wan.wanmarcos.models.Teacher;


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
        TextView teacherRaiting=(TextView)view.findViewById(R.id.teacher_raiting);
        TextView teacherFaculty=(TextView)view.findViewById(R.id.teacher_faculty);
        TextView teacherDescription=(TextView)view.findViewById(R.id.teacher_description);
        Teacher teacher=getItem(pos);
        teacherName.setText(teacher.getName());
        teacherFaculty.setText(teacher.getFaculties());
        teacherRaiting.setText(teacher.getRaiting()+"");
        teacherDescription.setText(teacher.getDescription());
        Picasso.with(getContext()).load("http://lorempixel.com/350/230/").into(teacherImage);
    }

}