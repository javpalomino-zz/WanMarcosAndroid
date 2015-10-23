package wan.wanmarcos.fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import wan.wanmarcos.R;
import wan.wanmarcos.managers.Communicator;
import wan.wanmarcos.models.Teacher;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.views.adapters.TeacherListAdapter;

public class SectionListTeachers extends Fragment {

    private TeacherListAdapter teacherListAdapter;
    private ListView listView;
    private Communicator communicator;

    public static SectionListTeachers newInstance(){
        SectionListTeachers sectionListTeachers=new SectionListTeachers();
        return sectionListTeachers;
    }
    public SectionListTeachers(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        communicator=(Communicator)getActivity();
        teacherListAdapter=new TeacherListAdapter(getActivity(), Constants.TEACHER_NEW_ITEM);
        List<String> lista=new ArrayList<>();
        lista.add("FISI");
        lista.add("FIEE");
        lista.add("FLCH");
        teacherListAdapter.add(new Teacher("Carlos", 15, lista, "Valeroso Profesor"));
        teacherListAdapter.add(new Teacher("Jose",18,lista,"Educado Maestro"));
        teacherListAdapter.add(new Teacher("Juan", 13, lista, "Profesor Empeñoso"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(Constants.SECTION_LIST,container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        listView=(ListView)view.findViewById(R.id.generic_listView);
        listView.setAdapter(teacherListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                communicator.response(teacherListAdapter.getItem(position));

            }
        });
        //SideSelector sideSelector=(SideSelector)view.findViewById(R.id.generic_scroll_view);
        //sideSelector.setListView(listView);

    }
}
