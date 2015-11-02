package wan.wanmarcos.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.SQLOutput;
import java.util.Calendar;

import wan.wanmarcos.R;
import wan.wanmarcos.models.Event;

/**
 * Created by Francisco on 2/11/2015.
 */
public class EventPageFragment extends Fragment{


    private Event selectedEvent;

    private ImageView imageView;
    private TextView txtReference;
    private TextView txtStart;
    private TextView txtEnd;
    private TextView txtDescription;
    private Button btnSchedule;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout =inflater.inflate(R.layout.fragment_event_page, container, false);
        setUpElements(layout);
        addListeners();
        loadEvent();
        fillData();
        return layout;
    }

    private void setUpElements(View layout)
    {
        imageView= (ImageView) layout.findViewById(R.id.eventPageImage);
        txtReference= (TextView) layout.findViewById(R.id.eventPageReferencePlace);
        txtStart= (TextView) layout.findViewById(R.id.eventPageStartDateAndTime);
        txtEnd= (TextView) layout.findViewById(R.id.eventPageEndDateAndTime);
        txtDescription= (TextView) layout.findViewById(R.id.eventPageDescription);
        btnSchedule= (Button) layout.findViewById(R.id.eventPageScheduleButton);
    }

    private void loadEvent()
    {
        selectedEvent = getArguments().getParcelable("selectedEvent");
    }

    private void fillData()
    {
        imageView.setImageResource(selectedEvent.getIconId());
        txtReference.setText("Lugar de Referencia : "+selectedEvent.getReferencePlace());
        txtStart.setText(selectedEvent.CalendarToString(selectedEvent.getStartDateTime()));
        txtEnd.setText(selectedEvent.CalendarToString(selectedEvent.getFinishDateTime()));
        txtDescription.setText(selectedEvent.getDescription());
        getActivity().setTitle(selectedEvent.getName());
    }


    private void addListeners()
    {
        addScheduleDownloadListener();
    }

    private void addScheduleDownloadListener()
    {

    }

    public void setSelectedEvent(Event selectedEvent) {
        this.selectedEvent = selectedEvent;
    }
}
