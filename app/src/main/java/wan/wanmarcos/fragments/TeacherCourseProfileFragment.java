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
import wan.wanmarcos.models.Teacher;
import wan.wanmarcos.models.Valuation;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.utils.Redirection.Redirect;
import wan.wanmarcos.utils.Storage;
import wan.wanmarcos.views.adapters.RatingListAdapter;
import wan.wanmarcos.views.adapters.ValuationListAdapter;
import wan.wanmarcos.views.widgets.CircleTransform;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeacherCourseProfileFragment extends Fragment implements FragmentsMethods,ItemAdapterListener<Valuation> {
    private TextView teacherName;
    private TextView courseName;
    private TextView facultyName;
    private ImageView teacherImage;
    private RatingListAdapter ratingListAdapter;
    private ValuationListAdapter valuationListAdapter;
    private RecyclerView recyclerViewRating;
    private RecyclerView recyclerViewComments;

    public TeacherCourseProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view=inflater.inflate(Constants.FRAGMENT_TEACHER_COURSE_LAYOUT, container, false);
        setUpElements(view);
        addListeners();
        return view;
    }

    @Override
    public void setUpElements(View view) {
        teacherName=(TextView)view.findViewById(R.id.profile_course_teacher_name);
        teacherName.setText(Storage.getSingelton().getInfo(this,Storage.KEY_TEACHER_NAME));
        courseName=(TextView)view.findViewById(R.id.profile_course_course_name);
        courseName.setText(Storage.getSingelton().getInfo(this,Storage.KEY_COURSE_NAME));
        facultyName=(TextView)view.findViewById(R.id.profile_course_faculty_name);
        facultyName.setText(Storage.getSingelton().getInfo(this,Storage.KEY_FACULTY_NAME));
        teacherImage=(ImageView)view.findViewById(R.id.profile_course_teacher_image);
        Picasso.with(view.getContext()).load(Storage.getSingelton().getInfo(this,Storage.KEY_TEACHER_IMAGE)).transform(new CircleTransform()).into(teacherImage);
        recyclerViewComments=(RecyclerView)view.findViewById(R.id.comments_list);
        recyclerViewRating=(RecyclerView)view.findViewById(R.id.rating_list);
        ratingListAdapter=new RatingListAdapter(getActivity(),getStaticData());
        valuationListAdapter=new ValuationListAdapter(getActivity(),getData(""));
        valuationListAdapter.setListener(this);
        recyclerViewComments.setAdapter(valuationListAdapter);
        recyclerViewRating.setAdapter(ratingListAdapter);
        recyclerViewComments.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewRating.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    public void addListeners(){

    }
    @Override
    public void itemClicked(View view, Valuation object) {
        //
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
    public List<Valuation> getData(String data) {
        List<Valuation> valuations=new ArrayList<>();
        valuations.add(new Valuation("Carlos","d","Curso Boni",14));
        valuations.add(new Valuation("Carlos","d","Curso Boni",14));
        valuations.add(new Valuation("Carlos","d","Curso Boni",14));
        valuations.add(new Valuation("Carlos","d","Curso Boni",14));
        valuations.add(new Valuation("Carlos","d","Curso Boni",14));
        valuations.add(new Valuation("Carlos","d","Curso Boni",14));
        return  valuations;

    }
}
