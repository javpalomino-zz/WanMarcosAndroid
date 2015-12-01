package wan.wanmarcos.fragments;

import android.app.DownloadManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcel;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import wan.wanmarcos.R;
import wan.wanmarcos.models.Event;
import wan.wanmarcos.models.Session;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.utils.DateAndTimeDealer;
import wan.wanmarcos.utils.RestClient;
import wan.wanmarcos.utils.Storage;

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
    FloatingActionButton downloadFAB;

    private String id;
    private String scheduleLink;
    private boolean received;
    private SharedPreferences preferences;
    private Session session;
    private RestClient restClient;
    private Event event;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        restClient = new RestClient(getActivity());
        preferences = getActivity().getSharedPreferences(Constants.PREFERENCES, Context.MODE_PRIVATE);
        session = Session.getSession(preferences);
        received = true;
        event = new Event();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout =inflater.inflate(R.layout.fragment_event_page, container, false);
        id=Storage.getSingelton().getInfo(Storage.KEY_EVENT_ID);
        setUpElements(layout);
        fillData();
        addListeners();
        return layout;
    }

    private void setUpElements(View layout)
    {
        imageView= (ImageView) layout.findViewById(R.id.eventPageImage);
        txtReference= (TextView) layout.findViewById(R.id.eventPageReferencePlace);
        txtReference.setText(event.getReferencePlace());
        txtStart= (TextView) layout.findViewById(R.id.eventPageStartDateAndTime);
        txtEnd= (TextView) layout.findViewById(R.id.eventPageEndDateAndTime);
        txtDescription= (TextView) layout.findViewById(R.id.eventPageDescription);
        txtLink=(TextView)layout.findViewById(R.id.evnetPageLinkText);
        downloadFAB = (FloatingActionButton)  layout.findViewById(R.id.downloadProgram);
    }

    private void fillData()
    {
        Call<JsonElement> eventDetail = restClient.getConsumerService().getEventDetail(session.getToken(), Integer.parseInt(id));
        eventDetail.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Response<JsonElement> response, Retrofit retrofit) {
                JsonObject responseBody = response.body().getAsJsonObject();
                event = new Event(responseBody);
                txtStart.setText(event.getStartDateTimeString());
                txtEnd.setText(event.getFinishDateTimeString());
                txtDescription.setText(event.getDescription());
                txtLink.setText(event.getEventLink());
                Picasso.with(getActivity()).load(event.getImgUrl()).into(imageView);
                Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();

                if(bitmap!=null) {
                    Palette p = Palette.from(bitmap).generate();
                    imageView.setBackgroundColor(p.getVibrantColor(0x0000000));
                }
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println(t);
            }
        });
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
                try {
                    if (received) {
                        received = false;
                        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(scheduleLink));
                        request.setDescription("Cronograma del Evento");
                        request.setTitle("Cronograma");
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                            request.allowScanningByMediaScanner();
                            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                        }
                        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "name-of-the-file.ext");

                        DownloadManager manager = (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
                        manager.enqueue(request);
                        received = true;
                    }
                } catch (Throwable e) {
                    Toast.makeText(getActivity(), "Cronograma no disponible", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private Fragment getFragment(){
        return this;
    }

}
