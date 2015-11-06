package wan.wanmarcos.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import wan.wanmarcos.R;
import wan.wanmarcos.utils.Redirection.Redirect;
import wan.wanmarcos.utils.Redirection.Redirection;
import wan.wanmarcos.models.Teacher;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.managers.ItemAdapterListener;
import wan.wanmarcos.views.adapters.TeacherListAdapter;
import wan.wanmarcos.views.widgets.RecyclerViewDivider;

public class SectionListTeachers extends Fragment implements ItemAdapterListener<Teacher> {

    private RecyclerView recyclerView;
    private TeacherListAdapter teacherListAdapter;

    public static SectionListTeachers newInstance(){
        SectionListTeachers sectionListTeachers=new SectionListTeachers();
        return sectionListTeachers;
    }

    public SectionListTeachers(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout=inflater.inflate(Constants.SECTION_LIST,container,false);
        setUpElements(layout);
        addListeners();
        return layout;
    }

    public void setUpElements(View view){
        recyclerView=(RecyclerView)view.findViewById(R.id.generic_listView);
        teacherListAdapter=new TeacherListAdapter(getActivity(),getData());
        teacherListAdapter.setListener(this);
        recyclerView.setAdapter(teacherListAdapter);
        recyclerView.addItemDecoration(new RecyclerViewDivider(getActivity()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
    public void addListeners(){
        //addItemListener(listView);
    }

    public  List <Teacher> getData() {
        List<String> lista=new ArrayList<>();
        lista.add("FISI");
        lista.add("FIEE");
        lista.add("FLCH");
        List <Teacher>dTeachers=new ArrayList();

        dTeachers.add(new Teacher("Carlos", 15, lista, "Valeroso Profesor", "http://lorempixel.com/350/230/"));
        dTeachers.add(new Teacher("Jose",18,lista,"Educado Maestro", "http://lorempixel.com/350/230/"));
        dTeachers.add(new Teacher("Juan", 13, lista, "Profesor Empeñoso", "http://lorempixel.com/350/230/"));
        dTeachers.add(new Teacher("Miguel", 19, lista, "SHESHO SHESHO  ", "http://lorempixel.com/350/230/"));
        return dTeachers;
    }

    @Override
    public void itemClicked(View view, Teacher object) {
        Redirect.getSingletonInstance().changeFragment(object);
    }
}
