package wan.wanmarcos.fragments;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Parcel;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.sql.SQLOutput;
import java.util.Calendar;

import wan.wanmarcos.R;
import wan.wanmarcos.models.Event;
import wan.wanmarcos.utils.Redirection.Redirect;

/**
 * Created by Francisco on 2/11/2015.
 */
public class EventPageFragment extends Fragment{



    private ImageView imageView;
    private TextView txtReference;
    private TextView txtStart;
    private TextView txtEnd;
    private TextView txtDescription;
    private TextView txtLink;
    private Button btnSchedule;
    FloatingActionButton downloadFAB;


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
        txtLink=(TextView)layout.findViewById(R.id.evnetPageLinkText);
        downloadFAB = (FloatingActionButton)  layout.findViewById(R.id.downloadProgram);
    }

    private void fillData()
    {
        Picasso.with(getContext())
                .load(Redirect.getSingletonInstance().getInformation("eventimage"))
                .into(imageView);
        txtReference.setText(Redirect.getSingletonInstance().getInformation("eventreference"));
        txtStart.setText(Redirect.getSingletonInstance().getInformation("eventdatestart"));
        txtEnd.setText(Redirect.getSingletonInstance().getInformation("eventdateend"));
        txtDescription.setText(Redirect.getSingletonInstance().getInformation("eventdescription"));
        txtLink.setText(Redirect.getSingletonInstance().getInformation("eventlink"));
        Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
        if(bitmap!=null){
            Palette p = Palette.from(bitmap).generate();
            imageView.setBackgroundColor( p.getVibrantColor(0x0000000));
        }
        getActivity().setTitle(Redirect.getSingletonInstance().getInformation("eventname"));
    }


    private void addListeners()
    {
        addScheduleDownloadListener();
    }

    private void addScheduleDownloadListener()
    {
        downloadFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Descargando Programa del Evento", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onResume() {
        Redirect.getSingletonInstance().setActivity((AppCompatActivity) getActivity(), R.id.home_fragment);
        super.onResume();
    }
}
