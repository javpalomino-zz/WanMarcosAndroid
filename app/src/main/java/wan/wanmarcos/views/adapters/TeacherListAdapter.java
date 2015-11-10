package wan.wanmarcos.views.adapters;

import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import wan.wanmarcos.R;
import wan.wanmarcos.managers.ItemAdapterListener;
import wan.wanmarcos.managers.ViewHolderSetters;
import wan.wanmarcos.models.Teacher;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.views.widgets.CircleTransform;
public class TeacherListAdapter extends RecyclerView.Adapter<TeacherListAdapter.TeacherHolder> {
    private LayoutInflater inflater;
    private List<Teacher> teachers= Collections.emptyList();
    private ItemAdapterListener itemAdapterListener;
    public TeacherListAdapter(Context context,List<Teacher> teachers){
        inflater= LayoutInflater.from(context);
        this.teachers=teachers;
    }
    @Override
    public TeacherHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(Constants.TEACHER_NEW_ITEM, parent, false);
        TeacherHolder teacherCustomer = new TeacherHolder(view);
        return teacherCustomer;
    }

    @Override
    public void onBindViewHolder(TeacherHolder holder, int position) {
        Teacher teacher=teachers.get(position);
        holder.setElements(teacher);
    }

    public void setListener(ItemAdapterListener listener){
        this.itemAdapterListener =listener;
    }

    @Override
    public int getItemCount() {
        return teachers.size();
    }

    public class  TeacherHolder extends RecyclerView.ViewHolder implements ViewHolderSetters<Teacher>,View.OnClickListener{
        private TextView teacherName;
        private RatingBar teacherRating;
        private ImageView teacherImage;
        private TextView teacherCourses;
        private TextView teacherAssumptions;
        private View vista;
        private Teacher current;
        public TeacherHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            teacherName= (TextView) itemView.findViewById(R.id.teacher_name);
            teacherRating=(RatingBar)itemView.findViewById(R.id.teacher_rating);
            teacherImage=(ImageView)itemView.findViewById(R.id.teacher_image);
            teacherCourses=(TextView)itemView.findViewById(R.id.teacher_description);
            teacherAssumptions=(TextView)itemView.findViewById(R.id.teacher_faculty);
            vista=itemView;
        }

        @Override
        public void setElements(Teacher elements) {
            current=elements;
            teacherName.setText(elements.getName());
            teacherRating.setRating(elements.getRaiting());

            teacherCourses.setText(Constants.tripleTab()+ elements.getFaculties());
            teacherAssumptions.setText(Constants.doubleTab()+elements.getDescription());
            Picasso.with(vista.getContext()).load(elements.getImageUrl()).transform(new CircleTransform()).into(teacherImage);
        }

        @Override
        public void onClick(View v) {
            itemAdapterListener.itemClicked(v,current);
        }
    }
}