package wan.wanmarcos.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import wan.wanmarcos.R;
import wan.wanmarcos.models.Course;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.views.adapters.CourseListAdapter;

public class SectionTeacherCourses extends Fragment {
    private CourseListAdapter courseListAdapter;
    private ListView listView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        courseListAdapter=new CourseListAdapter(getActivity(), Constants.COURSE_NEW_ITEM);
        courseListAdapter.add(new Course("Matematica",(float)4.0,"FISI","carñlos"));
        courseListAdapter.add(new Course("Letras",(float)3.4,"FIEE","carñlos"));
        courseListAdapter.add(new Course("Humanidades",(float)3.5,"FLCHs","carñlos"));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(Constants.SECTION_LIST,container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        listView=(ListView)view.findViewById(R.id.generic_listView);
        listView.setAdapter(courseListAdapter);
    }
}
