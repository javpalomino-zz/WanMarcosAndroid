package wan.wanmarcos.fragments;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;

import wan.wanmarcos.R;

/**
 * Created by Francisco on 1/11/2015.
 */
public class SuggestedEventFragment extends Fragment {

    private EditText txtName;
    private AutoCompleteTextView txtPlace;
    private AutoCompleteTextView txtCategory;
    private Button btnDateStart;
    private Calendar dateStart;
    private Button btnDateEnd;
    private Calendar dateEnd;
    private Button btnTimeStart;
    private Calendar timeStart;
    private Button btnTimeEnd;
    private Calendar timeEnd;
    private EditText txtLink;
    private Button btnImage;
    private Button btnSchedule;
    private EditText txtDescription;
    private Button btnSubmit;

    private View v_Layout;

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
        v_Layout=layout;
        return layout;
    }

    private void setUpElements(View layout)
    {
        txtName= (EditText) layout.findViewById(R.id.eventFormName);
        txtPlace= (AutoCompleteTextView) layout.findViewById(R.id.eventFormPlace);
        txtCategory= (AutoCompleteTextView) layout.findViewById(R.id.eventFormCategory);
        btnDateStart = (Button) layout.findViewById(R.id.eventFormStartDateButton);
        btnDateEnd= (Button) layout.findViewById(R.id.eventFormEndDateButton);
        btnTimeStart= (Button) layout.findViewById(R.id.eventFormStartTimeButton);
        btnTimeEnd= (Button) layout.findViewById(R.id.eventFormEndTimeButton);
        txtLink= (EditText) layout.findViewById(R.id.eventFormLink);
        btnImage= (Button) layout.findViewById(R.id.eventFormImage);
        btnSchedule= (Button) layout.findViewById(R.id.eventFormSchedule);
        txtDescription= (EditText) layout.findViewById(R.id.eventFormDescription);
        btnSubmit= (Button) layout.findViewById(R.id.eventFormSubmit);

    }

    private void addListeners()
    {
        addStartDateButtonListener();
        addEndDateButoonListener();
        addStartTimeButtonListener();
        addEndTimeButtonListener();
        addImageLoadListener();
        addScheduleLoadListener();
        addSubmitListener();
    }

    private void addStartDateButtonListener()
    {
        btnDateStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(v_Layout);
            }
        });
    }
    private void addEndDateButoonListener()
    {
        btnDateEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(v_Layout);
            }
        });
    }

    private void addStartTimeButtonListener()
    {
        btnTimeStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerDialog(v_Layout);
            }
        });
    }
    private void addEndTimeButtonListener()
    {
        btnTimeEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerDialog(v_Layout);
            }
        });
    }


    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
    }
    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
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
