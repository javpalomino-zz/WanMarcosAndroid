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


public class TeacherListAdapter extends ArrayAdapter<Teacher> {
    private Context context;
    private List<Teacher> teachers;



    public TeacherListAdapter(Context cont, List<Teacher> objects) {
        super(cont, R.layout.teacher_new_item,objects);
        this.context=cont;
        this.teachers=objects;
    }
    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView=layoutInflater.inflate(R.layout.teacher_new_item,parent,false);
        setElements(rowView, position);
        return rowView;
    }
    public void setElements(View view,int pos){
        TextView teacherName=(TextView)view.findViewById(R.id.teacher_name);
        ImageView teacherImage=(ImageView)view.findViewById(R.id.teacher_image);
        TextView teacherRaiting=(TextView)view.findViewById(R.id.teacher_raiting);
        TextView teacherFaculty=(TextView)view.findViewById(R.id.teacher_faculty);
        TextView teacherDescription=(TextView)view.findViewById(R.id.teacher_description);
        teacherName.setText(teachers.get(pos).getName());
        teacherFaculty.setText(teachers.get(pos).getFaculties());
        teacherRaiting.setText(teachers.get(pos).getRaiting()+"");
        teacherDescription.setText(teachers.get(pos).getDescription());
        Picasso.with(context).load("http://lorempixel.com/350/230/").into(teacherImage);
    }

}