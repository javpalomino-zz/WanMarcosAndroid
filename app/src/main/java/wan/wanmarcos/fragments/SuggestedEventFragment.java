package wan.wanmarcos.fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import okio.BufferedSink;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import wan.wanmarcos.R;
import wan.wanmarcos.activities.EventsActivity;
import wan.wanmarcos.models.*;
import wan.wanmarcos.utils.Builder;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.utils.DateAndTimeDealer;
import wan.wanmarcos.utils.Redirection.Redirect;
import wan.wanmarcos.utils.RestClient;

/**
 * Created by Francisco on 1/11/2015.
 */
public class SuggestedEventFragment extends Fragment {

    private RestClient restClient;
    private Builder builder;

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
    private static final int PICKFILE_RESULT_CODE=100;
    private static final int PICKIMAGE_RESULT_CODE=200;
    private Uri imageUri;
    private Uri scheduleUri;
    FloatingActionButton sendFAB;

    private Event eventToPost;

    private View v_Layout;

    private DateAndTimeDealer dateAndTimeDealer;

    public SuggestedEventFragment() {
        restClient = new RestClient();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dateAndTimeDealer=new DateAndTimeDealer();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_events_form, container, false);
        setUpElements(layout);
        addListeners();
        v_Layout = layout;
        return layout;
    }

    private void setUpElements(View layout) {
        txtName = (EditText) layout.findViewById(R.id.eventFormName);
        txtPlace = (AutoCompleteTextView) layout.findViewById(R.id.eventFormPlace);
        txtCategory = (AutoCompleteTextView) layout.findViewById(R.id.eventFormCategory);
        btnDateStart = (Button) layout.findViewById(R.id.eventFormStartDateButton);
        btnDateEnd = (Button) layout.findViewById(R.id.eventFormEndDateButton);
        btnTimeStart = (Button) layout.findViewById(R.id.eventFormStartTimeButton);
        btnTimeEnd = (Button) layout.findViewById(R.id.eventFormEndTimeButton);
        txtLink = (EditText) layout.findViewById(R.id.eventFormLink);
        btnImage = (Button) layout.findViewById(R.id.eventFormImage);
        btnSchedule = (Button) layout.findViewById(R.id.eventFormSchedule);
        txtDescription = (EditText) layout.findViewById(R.id.eventFormDescription);
        sendFAB = (FloatingActionButton) layout.findViewById(R.id.sendFab);
        builder = new Builder();

    }

    private void addListeners() {
        addStartDateButtonListener();
        addEndDateButoonListener();
        addStartTimeButtonListener();
        addEndTimeButtonListener();
        addImageLoadListener();
        addScheduleLoadListener();
        addSubmitListener();
    }

    private void addStartDateButtonListener() {
        btnDateStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showStartDatePickerDialog(v_Layout);
            }
        });
    }

    private void addEndDateButoonListener() {
        btnDateEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showEndDatePickerDialog(v_Layout);
            }
        });
    }

    private void addStartTimeButtonListener() {
        btnTimeStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showStartTimePickerDialog(v_Layout);
            }
        });
    }

    private void addEndTimeButtonListener() {
        btnTimeEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showEndTimePickerDialog(v_Layout);
            }
        });
    }


    public void showStartTimePickerDialog(View v) {
        DialogFragment startTimeFragment = new TimePickerFragment(mStartTimeSetListener);
        startTimeFragment.show(getActivity().getSupportFragmentManager(), "startTimePicker");
    }

    public void showStartDatePickerDialog(View v) {
        DialogFragment startDateFragment = new DatePickerFragment(mStartDateSetListener);
        startDateFragment.show(getActivity().getSupportFragmentManager(), "startDatePicker");
    }

    public void showEndTimePickerDialog(View v) {
        DialogFragment endTimeFragment = new TimePickerFragment(mEndTimeSetListener);
        endTimeFragment.show(getActivity().getSupportFragmentManager(), "endTimePicker");
    }

    public void showEndDatePickerDialog(View v) {
        DialogFragment endDateFragment = new DatePickerFragment(mEndDateSetListener);
        endDateFragment.show(getActivity().getSupportFragmentManager(), "endDatePicker");
    }

    TimePickerDialog.OnTimeSetListener mStartTimeSetListener =
            new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
                    btnTimeStart.setText("Hora de Inicio : " + hourOfDay + ":" + minute);
                    timeStart = new GregorianCalendar();
                    timeStart.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    timeStart.set(Calendar.MINUTE, minute);
                    Toast.makeText(getActivity(), timeStart.get(Calendar.HOUR_OF_DAY) + ":" + timeStart.get(Calendar.MINUTE), Toast.LENGTH_SHORT).show();
                }
            };


    TimePickerDialog.OnTimeSetListener mEndTimeSetListener =
            new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
                    btnTimeEnd.setText("Hora de Fin : " + hourOfDay + ":" + minute);
                    timeEnd = new GregorianCalendar();
                    timeEnd.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    timeEnd.set(Calendar.MINUTE, minute);
                    Toast.makeText(getActivity(), timeEnd.get(Calendar.HOUR_OF_DAY) + ":" + timeEnd.get(Calendar.MINUTE), Toast.LENGTH_SHORT).show();
                }
            };

    DatePickerDialog.OnDateSetListener mStartDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(android.widget.DatePicker view, int year, int month, int day) {
                    btnDateStart.setText("Fecha de Inicio : " + day + "/" + month + "/" + year);
                    dateStart = new GregorianCalendar();
                    dateStart.set(Calendar.YEAR, year);
                    dateStart.set(Calendar.MONTH, month);
                    dateStart.set(Calendar.DAY_OF_MONTH, day);
                    Toast.makeText(getActivity(), dateStart.get(Calendar.YEAR) + "-" + dateStart.get(Calendar.MONTH) + "-" + dateStart.get(Calendar.DAY_OF_MONTH), Toast.LENGTH_SHORT).show();
                }
            };

    DatePickerDialog.OnDateSetListener mEndDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(android.widget.DatePicker view, int year, int month, int day) {
                    btnDateEnd.setText("Fecha de Fin : " + day + "/" + month + "/" + year);
                    dateEnd = new GregorianCalendar();
                    dateEnd.set(Calendar.YEAR, year);
                    dateEnd.set(Calendar.MONTH, month);
                    dateEnd.set(Calendar.DAY_OF_MONTH, day);
                    Toast.makeText(getActivity(), dateEnd.get(Calendar.YEAR) + "-" + dateEnd.get(Calendar.MONTH) + "-" + dateEnd.get(Calendar.DAY_OF_MONTH), Toast.LENGTH_SHORT).show();
                }
            };


    private void addImageLoadListener() {
        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent , PICKIMAGE_RESULT_CODE);
            }
        });
    }

    private void addScheduleLoadListener() {
        btnSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                startActivityForResult(intent,PICKFILE_RESULT_CODE);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch(requestCode)
        {
            case PICKFILE_RESULT_CODE:
                scheduleUri = data.getData();
                setNameOfFileInButton(data.getData(), btnSchedule);
                break;
            case PICKIMAGE_RESULT_CODE:
                imageUri = data.getData();
                setNameOfFileInButton(data.getData(),btnImage);
                break;
        }
    }

    private void setNameOfFileInButton(Uri uri,Button button){

        Cursor returnCursor = getActivity().getContentResolver().query(uri,null,null,null,null);
        int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
        returnCursor.moveToFirst();
        button.setText(returnCursor.getString(nameIndex));
        Toast.makeText(getActivity(),getPath(uri), Toast.LENGTH_SHORT).show();
    }
    private void addSubmitListener() {
        sendFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Enviando Sugerencia de Evento", Toast.LENGTH_SHORT).show();
                getFields();
                postEvent();
            }
        });
    }

    private void getFields() {
        eventToPost = new Event();
        eventToPost.setName(txtName.getText().toString());
        eventToPost.setDescription(txtDescription.getText().toString());
        Calendar calStart = new GregorianCalendar();
        calStart.set(dateStart.get(Calendar.YEAR), dateStart.get(Calendar.MONTH), dateStart.get(Calendar.DAY_OF_MONTH), timeStart.get(Calendar.HOUR), timeStart.get(Calendar.MINUTE), timeStart.get(Calendar.SECOND));
        eventToPost.setStartDateTime(calStart);
        Calendar calEnd = new GregorianCalendar();
        calEnd.set(dateEnd.get(Calendar.YEAR), dateEnd.get(Calendar.MONTH), dateEnd.get(Calendar.DAY_OF_MONTH), timeEnd.get(Calendar.HOUR), timeEnd.get(Calendar.MINUTE), timeEnd.get(Calendar.SECOND));
        eventToPost.setFinishDateTime(calEnd);
        eventToPost.setEventLink(txtLink.getText().toString());
    }

    private void postEvent() {
        try {
            File file = new File(getPath(imageUri));
            RequestBody image = RequestBody.create(MediaType.parse("image/*"), file);
            Call<JsonElement> sugEvent = restClient.getConsumerService().suggestEvent(
                    eventToPost.getName() ,
                    eventToPost.getDescription(),
                    dateAndTimeDealer.getInstance().turnCalendarIntoMilis(eventToPost.getStartDateTime()),
                    dateAndTimeDealer.getInstance().turnCalendarIntoMilis(eventToPost.getFinishDateTime()),
                    eventToPost.getEventLink().toString(),
                    image);
            sugEvent.enqueue(new Callback<JsonElement>() {
                @Override
                public void onResponse(Response<JsonElement> response, Retrofit retrofit) {
                    if (response.isSuccess()) {
                        JsonObject responseBody = response.body().getAsJsonObject();
                        if (responseBody.has("name")) {
                            String name = responseBody.get("name").getAsString();
                            Toast.makeText(getActivity(), "Tu Evento : " + name + " ha sido sugerido correctamente.", Toast.LENGTH_SHORT).show();
                        } else {
                            if (responseBody.has("error")) {
                                wan.wanmarcos.models.Error error = builder.buildError(responseBody.get("error").getAsJsonObject());
                                Toast.makeText(getActivity(), "Error : " + error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else {
                        try {
                            Toast.makeText(getActivity(), response.errorBody().string(), Toast.LENGTH_LONG).show();
                        }catch (Throwable e){
                            Toast.makeText(getActivity(),"el string de mierda", Toast.LENGTH_LONG).show();
                        }
                    }

                }

                @Override
                public void onFailure(Throwable t) {
                    Toast.makeText(getActivity(),t.toString(),Toast.LENGTH_LONG).show();
                }
            });
        }catch (Throwable e){
            Toast.makeText(getActivity(),e.toString(),Toast.LENGTH_LONG).show();
        }
    }

    public String getPath(Uri uri) {

        String[] projection = { MediaStore.Images.Media.DATA };

        Cursor cursor = getActivity().getContentResolver().query(uri, projection, null, null, null);

        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        cursor.moveToFirst();

        return cursor.getString(column_index);

    }
}