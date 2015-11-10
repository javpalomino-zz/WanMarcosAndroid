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

import java.util.ArrayList;
import java.util.List;

import wan.wanmarcos.R;
import wan.wanmarcos.utils.Redirection.Redirect;
import wan.wanmarcos.utils.Redirection.Redirection;
import wan.wanmarcos.managers.ItemAdapterListener;
import wan.wanmarcos.models.Course;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.views.adapters.CourseListAdapter;
import wan.wanmarcos.views.widgets.RecyclerViewDivider;

public class SectionTeacherCourses extends Fragment implements ItemAdapterListener<Course>{
    private RecyclerView recyclerView;
    private CourseListAdapter courseListAdapter;

    public static SectionTeacherCourses newInstance(){
        SectionTeacherCourses sectionTeacherCourses=new SectionTeacherCourses();
        return sectionTeacherCourses;
    }

    public SectionTeacherCourses(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout=inflater.inflate(Constants.SECTION_GENERIC_LIST_LAYOUT,container,false);
        setUpElements(layout);
        addListeners();
        return layout;
    }

    private void addListeners() {
    }

    @Override
    public void itemClicked(View view, Course object) {
        Redirect.getSingletonInstance().changeFragment(object);
    }

    @Override
    public List<Course> getData(String data) {
        List <Course> courseList=new ArrayList();
        courseList.add(new Course("Matematica",(float)4.0,"FISI"));
        courseList.add(new Course("Letras",(float)3.4,"FIEE"));
        courseList.add(new Course("Humanidades",(float)3.5,"FLCHs"));
        return courseList;
    }

    public void setUpElements(View view) {
        recyclerView=(RecyclerView) view.findViewById(R.id.generic_listView);
        courseListAdapter=new CourseListAdapter(getActivity(),getData(""));
        courseListAdapter.setListener(this);
        recyclerView.setAdapter(courseListAdapter);
        recyclerView.addItemDecoration(new RecyclerViewDivider(getActivity()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
