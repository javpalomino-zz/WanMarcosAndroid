package wan.wanmarcos.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import wan.wanmarcos.R;
import wan.wanmarcos.models.Teacher;
import wan.wanmarcos.utils.Constants;

public class SectionTeacherTitle extends Fragment {
    private String teacherName;
    public SectionTeacherTitle(String name){
        teacherName=name;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(Constants.TEACHER_TITLE,container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        TextView editText=(TextView)view.findViewById(R.id.teacher_name);
        editText.setText(teacherName);
    }

}
