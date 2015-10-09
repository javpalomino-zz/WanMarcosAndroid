package wan.wanmarcos.fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import wan.wanmarcos.R;
import wan.wanmarcos.managers.Communicator;
import wan.wanmarcos.models.Teacher;
import wan.wanmarcos.views.adapters.TeacherListAdapter;


public class TeacherListFragment extends Fragment {
    View view;
    ListView listView;
    ArrayList<Teacher> teachers;
    Communicator comm;

    public TeacherListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_teacher_list, container, false);
        comm=(Communicator)getActivity();
        setUpElements();
        getNewsTeachers();
        addListeners();
        return view;
    }

    public void setUpElements(){
        listView= (ListView)view.findViewById(R.id.list_view_new_teahcers);
        Toast.makeText(getActivity().getApplication(), "STARTED", Toast.LENGTH_LONG).show();
    }
    public void getNewsTeachers(){
        teachers = new ArrayList<Teacher>();
        List<String> lista=new ArrayList<String>();
        lista.add("FISI");
        lista.add("FIEE");
        lista.add("FLCH");
        teachers.add(new Teacher("Carlos", 15, lista, "Valeroso Profesor"));
        teachers.add(new Teacher("Juan", 13, lista, "Profesor Empeñoso"));
        teachers.add(new Teacher("Jorge", 18, lista, "Ejemplar Empeñoso"));
        final TeacherListAdapter adapter=new TeacherListAdapter(getActivity(),teachers);


        listView.setAdapter(adapter);

    }
    public void addListeners(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                Teacher teacher=(Teacher)parent.getItemAtPosition(position);
                comm.response(teacher);
            }
        });

    }

}
