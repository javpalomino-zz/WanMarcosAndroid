package wan.wanmarcos.models;

import android.os.Parcelable;
import android.os.Parcel;

import java.security.PublicKey;
import java.util.Calendar;

/**
 * Created by javier on 27/09/15.
 */
public class Event implements Parcelable{

    private final String[] MONTHS_ARRAY = {"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};
    private final int DEFAULT_YEAR = 0;
    private final int DEFAULT_MONTH = 0;
    private final int DEFAULT_DAY_OF_MONTH = 1;
    private final int DEFAULT_HOUR = 0;
    private final int DEFAULT_MINUTE = 0;
    private final int DEFAULT_SECOND = 0;

    private String name;
    private String imgUrl;
    private String referencePlace;
    private Calendar startDateTime;
    private Calendar finishDateTime;
    private String description;
    private String eventLink;
    private int iconId;
    private int eventId;

    public Event() {

    }

    public Event(String name, String imgUrl) {
        this.name = name;
        this.imgUrl = imgUrl;
    }

    public Event(String name, Calendar startDateTime, int iconId) {
        this.name = name;
        this.startDateTime=startDateTime;
        this.iconId = iconId;
    }

    public Event(String name, String referencePlace, Calendar startDateTime, Calendar finishDateTime,String description, int iconId) {
        this.name = name;
        this.referencePlace = referencePlace;
        this.startDateTime=startDateTime;
        this.finishDateTime=finishDateTime;
        this.description = description;
        this.iconId = iconId;
    }

    public Event(String name, String referencePlace, Calendar startDateTime, Calendar finishDateTime, String description, String imgUrl) {
        this.name = name;
        this.referencePlace = referencePlace;
        this.startDateTime=startDateTime;
        this.finishDateTime=finishDateTime;
        this.description = description;
        this.imgUrl = imgUrl;
    }

    public Event(Parcel in) {
        readFromParcel(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(imgUrl);
        dest.writeString(referencePlace);
        if(startDateTime!=null)
        {
            dest.writeInt(startDateTime.get(Calendar.YEAR));
            dest.writeInt(startDateTime.get(Calendar.MONTH));
            dest.writeInt(startDateTime.get(Calendar.DAY_OF_MONTH));
            dest.writeInt(startDateTime.get(Calendar.HOUR_OF_DAY));
            dest.writeInt(startDateTime.get(Calendar.MINUTE));
            dest.writeInt(startDateTime.get(Calendar.SECOND));
        }
        else
        {
            writeDefaultDateTime(dest);
        }
        if(finishDateTime!=null)
        {
            dest.writeInt(finishDateTime.get(Calendar.YEAR));
            dest.writeInt(finishDateTime.get(Calendar.MONTH));
            dest.writeInt(finishDateTime.get(Calendar.DAY_OF_MONTH));
            dest.writeInt(finishDateTime.get(Calendar.HOUR_OF_DAY));
            dest.writeInt(finishDateTime.get(Calendar.MINUTE));
            dest.writeInt(finishDateTime.get(Calendar.SECOND));
        }
        else
        {
            writeDefaultDateTime(dest);
        }
        dest.writeString(description);
        dest.writeInt(iconId);
        dest.writeString(eventLink);
        dest.writeInt(eventId);
    }

    private void readFromParcel(Parcel in) {
        name = in.readString();
        imgUrl=in.readString();
        referencePlace=in.readString();
        startDateTime.set(in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt());
        finishDateTime.set(in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt());
        description=in.readString();
        iconId=in.readInt();
        eventLink=in.readString();
        eventId=in.readInt();
    }

    public static final Parcelable.Creator CREATOR =
            new Parcelable.Creator() {
                public Event createFromParcel(Parcel in) {
                    return new Event(in);
                }

                public Event[] newArray(int size) {
                    return new Event[size];
                }
            };

    public void writeDefaultDateTime(Parcel dest)
    {
        dest.writeInt(DEFAULT_YEAR);
        dest.writeInt(DEFAULT_MONTH);
        dest.writeInt(DEFAULT_DAY_OF_MONTH);
        dest.writeInt(DEFAULT_HOUR);
        dest.writeInt(DEFAULT_MINUTE);
        dest.writeInt(DEFAULT_SECOND);
    }

    public String CalendarToString(Calendar cal)
    {
        String str="";
        if(cal!=null)
        {
            str=str+MONTHS_ARRAY[cal.get(Calendar.MONTH)];
            str = str + " "+cal.get(Calendar.DAY_OF_MONTH)+" , "+(cal.get(Calendar.YEAR)) +" "+cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE)+" hs";
        }
        else
        {
            str=str+" NOT SET";
        }

        return str;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getReferencePlace() {

        return referencePlace;
    }

    public void setReferencePlace(String referencePlace) {

        this.referencePlace = referencePlace;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }


    public Calendar getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Calendar startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Calendar getFinishDateTime() {
        return finishDateTime;
    }

    public void setFinishDateTime(Calendar finishDateTime) {
        this.finishDateTime = finishDateTime;
    }

    public String getEventLink() {
        return eventLink;
    }

    public void setEventLink(String eventLink) {
        this.eventLink = eventLink;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }
}
