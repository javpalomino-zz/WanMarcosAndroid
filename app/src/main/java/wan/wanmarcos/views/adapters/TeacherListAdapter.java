package wan.wanmarcos.views.adapters;

import android.support.v4.app.Fragment;
import android.view.View;

import wan.wanmarcos.models.Teacher;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.views.adapters.ViewHolders.CustomViewHolder;
import wan.wanmarcos.views.adapters.ViewHolders.TeacherHolder;

public class TeacherListAdapter extends CustomSimpleListAdapter<Teacher> {


    public TeacherListAdapter(Fragment fragment) {
        super(fragment, Constants.TEACHER_NEW_ITEM);
    }

    @Override
    public CustomViewHolder getObject(View view) {
        return new TeacherHolder(view);
    }

    @Override
    public int getContainerID() {
        return Constants.TEACHER_CONTAINER;
    }

    @Override
    public String getFragmentName() {
        return Constants.FRAGMENT_PROFILE_TEACHER;
    }
}