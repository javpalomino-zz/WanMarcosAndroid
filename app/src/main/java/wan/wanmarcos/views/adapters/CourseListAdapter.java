package wan.wanmarcos.views.adapters;

import android.content.Context;
import android.media.Image;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import wan.wanmarcos.R;
import wan.wanmarcos.fragments.TeacherProfileFragment;
import wan.wanmarcos.managers.ItemAdapterListener;
import wan.wanmarcos.managers.ViewHolderSetters;
import wan.wanmarcos.models.Course;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.utils.Storage;
import wan.wanmarcos.views.widgets.CircleTransform;

/**
 * Created by carlos-pc on 09/10/15.
 */
public class CourseListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private List<Course>courses= Collections.emptyList();
    private ItemAdapterListener itemAdapterListener;
    private LayoutInflater layoutInflater;
    private Context context;
    private Fragment mFragment;

    public CourseListAdapter(Fragment myFragment,Context context,List<Course> courses){
        layoutInflater=LayoutInflater.from(context);
        this.courses=courses;
        this.context=context;
        mFragment=myFragment;

    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==TYPE_HEADER){
            View view=layoutInflater.inflate(Constants.COURSE_NEW_ITEM,parent,false);
            CourseHolder courseHolder=new CourseHolder(view);
            return courseHolder;
        }
        else if(viewType==TYPE_ITEM){
            View view=layoutInflater.inflate(Constants.PROFILE_TEACHER,parent,false);
            CourseHeaderHolder courseHeaderHolder=new CourseHeaderHolder(view);
            return courseHeaderHolder;
        }
        return null;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof CourseHolder) {
            Course course=courses.get(position-1);
            ((CourseHolder)holder).setElements(course);
        } else if (holder instanceof CourseHeaderHolder) {
            ((CourseHeaderHolder)holder).setElements();
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_ITEM;

        return TYPE_HEADER;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }


    @Override
    public int getItemCount() {
        return courses.size()+1;
    }
    public void setListener(ItemAdapterListener listener){
        this.itemAdapterListener =listener;
    }

    public class CourseHeaderHolder extends RecyclerView.ViewHolder {
        private ImageView teacherImage;
        private TextView teacherName;
        private TextView ratingTeacher;
        private ImageView teacherCardBackground;
        public CourseHeaderHolder(View itemView) {
            super(itemView);
            teacherCardBackground=(ImageView)itemView.findViewById(R.id.teacher_card_background);
            teacherImage=(ImageView)itemView.findViewById(R.id.profile_teacher_image);
            teacherName=(TextView)itemView.findViewById(R.id.profile_teacher_mame);
            ratingTeacher=(TextView)itemView.findViewById(R.id.profile_teacher_rating);
        }
        public void setElements(){
            Picasso.with(mFragment.getActivity()).load("https://newevolutiondesigns.com/images/freebies/google-material-design-wallpaper-17.jpg").fit().centerCrop().into(teacherCardBackground);
            teacherName.setText(Storage.getSingelton().getInfo(mFragment,Storage.KEY_TEACHER_NAME));
            ratingTeacher.setText(Storage.getSingelton().getInfo(mFragment,Storage.KEY_TEACHER_RATING));
            Picasso.with(itemView.getContext()).load(Storage.getSingelton().getInfo(mFragment,Storage.KEY_TEACHER_IMAGE)).transform(new CircleTransform()).into(teacherImage);
        }
    }

    public class CourseHolder extends RecyclerView.ViewHolder implements ViewHolderSetters<Course>,View.OnClickListener{
        private TextView courseName;
        private TextView courseFaculty;
        private View vista;
        private Course current;
        private ImageView initialLetterImage;
        public CourseHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            courseName= (TextView) itemView.findViewById(R.id.profile_course_course_name);
            courseFaculty=(TextView) itemView.findViewById(R.id.course_faculty);
            initialLetterImage=(ImageView) itemView.findViewById(R.id.letterImageBackground);
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
            ColorGenerator generator = ColorGenerator.MATERIAL;
            int color = generator.getColor(courseName.getText().charAt(0));
            TextDrawable.IBuilder builder = TextDrawable.builder().round();
            TextDrawable textDrawable = builder.build(courseName.getText().toString().charAt(0)+"", color);
            initialLetterImage.setImageDrawable(textDrawable);
            //courseMark.setText(elements.getRating()+"");
            //courseRating.setRating(elements.getRating());
        }
    }
}