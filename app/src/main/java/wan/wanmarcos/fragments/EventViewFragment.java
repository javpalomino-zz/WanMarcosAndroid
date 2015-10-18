package wan.wanmarcos.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import wan.wanmarcos.R;

/**
 * Created by postgrado on 17/10/15.
 */
public class EventViewFragment extends Fragment{

    View view;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_event_view, container, false);
        return view;
    }

}
