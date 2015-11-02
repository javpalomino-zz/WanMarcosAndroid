package wan.wanmarcos.fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import wan.wanmarcos.R;
import wan.wanmarcos.utils.Redirection.Redirect;
import wan.wanmarcos.utils.Redirection.Redirection;
import wan.wanmarcos.utils.Constants;

public class SectionTeacherTitle extends Fragment {

    public SectionTeacherTitle() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(Constants.TEACHER_TITLE,container,false);
        setUpElements(view);
        return view;
    }
    public void setUpElements(View view){
        TextView textView=(TextView) view.findViewById(R.id.teacher_name);
        textView.setText(Redirect.getSingletonInstance().getInformation("teachername"));
    }

}
