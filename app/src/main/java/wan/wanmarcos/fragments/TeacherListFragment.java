package wan.wanmarcos.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.SearchView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import wan.wanmarcos.R;
import wan.wanmarcos.managers.FragmentsMethods;
import wan.wanmarcos.managers.ItemAdapterListener;
import wan.wanmarcos.models.Teacher;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.utils.Redirection.Redirect;
import wan.wanmarcos.utils.Storage;
import wan.wanmarcos.views.adapters.TeacherListAdapter;


public class TeacherListFragment extends Fragment implements FragmentsMethods,ItemAdapterListener<Teacher>{
    private SearchView searchView;
    private TeacherListAdapter teacherListAdapter;
    private RecyclerView recyclerViewTeachers;
    public TeacherListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(Constants.FRAGMENT_LIST_TEACHER_LAYOUT, container, false);
        setUpElements(view);
        return view;
    }

    public void addListeners() {

    }

    public void setUpElements(View view){
        searchView=(SearchView)view.findViewById(R.id.searchViewTeachers);
        searchView.setFocusable(false);
        teacherListAdapter=new TeacherListAdapter(getActivity(),getData(""));
        teacherListAdapter.setListener(this);
        recyclerViewTeachers=(RecyclerView)view.findViewById(R.id.teacher_list);
        recyclerViewTeachers.setAdapter(teacherListAdapter);
        recyclerViewTeachers.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    @Override
    public void itemClicked(View view, Teacher object) {
        Storage.getSingelton().storage(object,this);
        Redirect.getSingelton().showFragment(this,Constants.TEACHER_CONTAINER,Constants.FRAGMENT_PROFILE_TEACHER);
    }

    public List<Teacher> getData(String search_text){
        List<Teacher> teachers=new ArrayList<>();
        teachers.add(new Teacher("Carlos",14,new ArrayList<String>(),"Carpe Diem","http://lorempixel.com/300/300/"));
        teachers.add(new Teacher("Juan",4,new ArrayList<String>(),"Carpe ","http://lorempixel.com/300/300/"));
        teachers.add(new Teacher("Pablo",5,new ArrayList<String>(),"Carpe dasd","http://lorempixel.com/300/300/"));
        teachers.add(new Teacher("Miguel",11,new ArrayList<String>(),"Diem","http://lorempixel.com/300/300/"));
        teachers.add(new Teacher("Zoraida",48,new ArrayList<String>(),"Gosta de Fazer","http://lorempixel.com/300/300/"));
        teachers.add(new Teacher("Zen",20,new ArrayList<String>(),"Desejo","http://lorempixel.com/300/300/"));
        return teachers;
    }
}
