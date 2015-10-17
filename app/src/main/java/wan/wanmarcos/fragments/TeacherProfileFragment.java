package wan.wanmarcos.fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import wan.wanmarcos.R;
import wan.wanmarcos.models.Course;
import wan.wanmarcos.models.Rating;
import wan.wanmarcos.models.Teacher;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.views.adapters.CourseListAdapter;
import wan.wanmarcos.views.adapters.RatingListAdapter;


public class TeacherProfileFragment extends Fragment {
    private Teacher teacher;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getFragmentManager().beginTransaction().add(R.id.sub_fragment_teacher_profile,new SectionTeacherProfile(teacher)).commit();
        getFragmentManager().beginTransaction().add(R.id.sub_fragment_teacher_courses,new SectionTeacherCourses()).commit();
        return inflater.inflate(Constants.FRAGMENT_PROFILE_TEACHER_LAYOUT, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    public void changeData(Teacher object){
        this.teacher=object;
    }
    /*View view;
    ListView listRating,listCourses;
    Teacher teacher;
    TextView name;
    ArrayList<Rating> ratings;
    ArrayList<Course> courses;

    public TeacherProfileFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_teacher_profile, container, false);

        setUpElements();
        return view;
    }
    public void setUpElements(){
        listCourses=(ListView)view.findViewById(R.id.teacher_courses);
        name=(TextView)view.findViewById(R.id.teacher_name);
        name.setText(teacher.getName());
        listRating=(ListView)view.findViewById(R.id.teacher_raitings);

        ImageView profilePicture=(ImageView)view.findViewById(R.id.teacher_image);
        Picasso.with(view.getContext()).load("http://lorempixel.com/350/230/").into(profilePicture);
        getNewRatings();
    }
    public void changeData(Teacher teacher){
        this.teacher=teacher;
    }
    public void getNewRatings(){
        ratings=new ArrayList<Rating>();
        ratings.add(new Rating((float) 4.2, "Pedagogica: "));
        ratings.add(new Rating((float) 1.8, "Dinamismo: "));
        ratings.add(new Rating((float) 2.5, "Concurrencia: "));
        ratings.add(new Rating((float) 3.0, "Ganas: "));

        RatingListAdapter ratingListAdapter=new RatingListAdapter(getActivity(),ratings);

        listRating.setAdapter(ratingListAdapter);

        courses=new ArrayList<Course>();
        courses.add(new Course("Matematica",(float)3.0,"FISI"));
        courses.add(new Course("Calculo",(float)2.0,"FIEE"));
        courses.add(new Course("Nadine",(float)2.5,"FLCH"));

        CourseListAdapter courseListAdapter=new CourseListAdapter(getActivity(),courses);
        listCourses.setAdapter(courseListAdapter);

    }*/
}
