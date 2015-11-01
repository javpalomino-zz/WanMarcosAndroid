package wan.wanmarcos.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.TimePicker;

import org.w3c.dom.Text;

import wan.wanmarcos.R;
import wan.wanmarcos.activities.EventsActivity;
import wan.wanmarcos.views.adapters.EventListAdapter;

/**
 * Created by Francisco on 1/11/2015.
 */
public class SuggestedEventFragment extends Fragment {

    private EditText txtName;
    private AutoCompleteTextView txtPlace;
    private AutoCompleteTextView txtCategory;
    private DatePicker dateStart;
    private TimePicker timeStart;
    private DatePicker dateEnd;
    private TimePicker timeEnd;
    private EditText txtLink;
    private Button btnImage;
    private Button btnSchedule;
    private EditText txtDescription;
    private Button btnSubmit;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout =inflater.inflate(R.layout.fragment_events_form, container, false);
        setUpElements(layout);
        addListeners();
        return layout;
    }

    private void setUpElements(View layout)
    {
        txtName= (EditText) layout.findViewById(R.id.eventFormName);
        txtPlace= (AutoCompleteTextView) layout.findViewById(R.id.eventFormPlace);
        txtCategory= (AutoCompleteTextView) layout.findViewById(R.id.eventFormCategory);
        dateStart= (DatePicker) layout.findViewById(R.id.eventFormStartDate);
        timeStart= (TimePicker) layout.findViewById(R.id.eventFormStartTime);
        dateEnd= (DatePicker) layout.findViewById(R.id.eventFormEndDate);
        timeEnd= (TimePicker) layout.findViewById(R.id.eventFormEndTime);
        txtLink= (EditText) layout.findViewById(R.id.eventFormLink);
        btnImage= (Button) layout.findViewById(R.id.eventFormImage);
        btnSchedule= (Button) layout.findViewById(R.id.eventFormSchedule);
        txtDescription= (EditText) layout.findViewById(R.id.eventFormDescription);
        btnSubmit= (Button) layout.findViewById(R.id.eventFormSubmit);

    }

    private void addListeners()
    {
        addImageLoadListener();
        addScheduleLoadListener();
        addSubmitListener();
    }

    private void addImageLoadListener()
    {
     //
    }
    private void addScheduleLoadListener()
    {
        //
    }
    private void addSubmitListener()
    {
        //
    }



}
