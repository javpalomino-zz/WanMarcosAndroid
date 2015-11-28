package wan.wanmarcos.fragments;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
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
import wan.wanmarcos.utils.Storage;
import wan.wanmarcos.views.adapters.CourseListAdapter;
import wan.wanmarcos.views.adapters.RatingListAdapter;
import wan.wanmarcos.views.widgets.CircleTransform;


public class TeacherProfileFragment extends Fragment implements FragmentsMethods,ItemAdapterListener<Course>{

    private RecyclerView recyclerViewTeacherCourses;
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
    public void setUpElements(View view) {
        recyclerViewTeacherCourses=(RecyclerView)view.findViewById(R.id.course_list);
        courseListAdapter=new CourseListAdapter(this);
        getData("d");
        recyclerViewTeacherCourses.setAdapter(courseListAdapter);
        recyclerViewTeacherCourses.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void addListeners() {

    }

    @Override
    public void itemClicked(View view, Course object) {
        Storage.getSingelton().storage(object,this);
        Redirect.getSingelton().showFragment(this,Constants.TEACHER_CONTAINER,Constants.FRAGMENT_TEACHER_COURSE);
    }

    @Override
    public void addClicked(String fragmentProfileTeacher) {

    }


    public void getData(String data) {
        courseListAdapter.add(new Course("Fisica", (float) 4.0,"FISI"));
        courseListAdapter.add(new Course("Matematica", (float) 4.0,"FISI"));
        courseListAdapter.add(new Course("holi", (float) 4.0,"FISI"));
        courseListAdapter.add(new Course("holi", (float) 4.0,"FISI"));
    }
}
