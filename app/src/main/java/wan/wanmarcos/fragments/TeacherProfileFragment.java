package wan.wanmarcos.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import wan.wanmarcos.R;
import wan.wanmarcos.managers.FragmentsMethods;
import wan.wanmarcos.managers.ItemAdapterListener;
import wan.wanmarcos.models.Course;
import wan.wanmarcos.models.Rating;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.utils.Redirection.Redirect;
import wan.wanmarcos.views.adapters.CourseListAdapter;
import wan.wanmarcos.views.adapters.RatingListAdapter;
import wan.wanmarcos.views.widgets.CircleTransform;


public class TeacherProfileFragment extends Fragment implements FragmentsMethods,ItemAdapterListener<Course>{

    private TextView teacherName;
    private ImageView teacherImage;
    private RecyclerView recyclerviewTeacherRatings;
    private RecyclerView recyclerViewTeacherCourses;
    private RatingListAdapter ratingListAdapter;
    private CourseListAdapter courseListAdapter;

    public TeacherProfileFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(Constants.FRAGMENT_PROFILE_TEACHER_LAYOUT, container, false);
        setUpElements(view);
        addListeners();
        return view;
    }
    @Override
    public void onResume() {
        //Redirect.getSingletonInstance().setActivity((AppCompatActivity) getActivity(), R.id.home_fragment);
        super.onResume();
    }

    @Override
    public void setUpElements(View view) {
        teacherName=(TextView)view.findViewById(R.id.profile_teacher_mame);
        teacherImage=(ImageView)view.findViewById(R.id.profile_teacher_image);
        Picasso.with(getActivity()).load("http://lorempixel.com/300/300/").transform(new CircleTransform()).into(teacherImage);
        teacherName.setText("CARLOS");
        recyclerViewTeacherCourses=(RecyclerView)view.findViewById(R.id.course_list);
        recyclerviewTeacherRatings=(RecyclerView)view.findViewById(R.id.rating_list);
        courseListAdapter=new CourseListAdapter(getActivity(),getData(""));
        courseListAdapter.setListener(this);
        ratingListAdapter=new RatingListAdapter(getActivity(),getStaticData());
        recyclerviewTeacherRatings.setAdapter(ratingListAdapter);
        recyclerviewTeacherRatings.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewTeacherCourses.setAdapter(courseListAdapter);
        recyclerViewTeacherCourses.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void addListeners() {

    }

    @Override
    public void itemClicked(View view, Course object) {
        //TODO
        // Redirect.getSingletonInstance().changeFragment(object);
    }

    public List<Rating> getStaticData(){
        List<Rating> ratings=new ArrayList<>();
        ratings.add(new Rating((float) 4.0,"tecnica"));
        ratings.add(new Rating((float) 2.5,"salud"));
        ratings.add(new Rating((float) 1.3,"conmosion"));
        ratings.add(new Rating((float) 3.0,"desarrollo"));
        return ratings;
    }

    @Override
    public List<Course> getData(String data) {
        List<Course> courses=new ArrayList<>();
        courses.add(new Course("Matematica", (float) 4.0,"FISI"));
        courses.add(new Course("Fisica", (float) 4.0,"FISI"));
        courses.add(new Course("Matematica", (float) 4.0,"FISI"));
        courses.add(new Course("Matematica", (float) 4.0,"FISI"));
        return courses;
    }
}
