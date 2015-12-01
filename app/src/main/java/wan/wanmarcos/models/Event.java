package wan.wanmarcos.models;

import android.os.Parcelable;
import android.os.Parcel;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import wan.wanmarcos.utils.DateAndTimeDealer;

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
    private String startDateTimeString;
    private Calendar finishDateTime;
    private String finishDateTimeString;
    private String description;
    private String eventLink;
    private String scheduleLink;
    private int category;
    private int iconId;
    private int eventId;

    public Event() {

    }
    public Event(JsonObject event){
            if(!event.get("id").isJsonNull()){
                setEventId(event.get("id").getAsInt());
            }else{
                setEventId(-1);
            }

            if(!event.get("title").isJsonNull()){
                setName(event.get("title").getAsString());
            }else{
                setName("No indicado");
            }
            if(!event.get("starts_at").isJsonNull()){
                setStartDateTimeString(unixToDate(event.get("starts_at").getAsLong()));
            }else{
                setStartDateTimeString("No indicado");
            }

            if(!event.get("starts_at").isJsonNull()){
                setFinishDateTimeString(unixToDate(event.get("ends_at").getAsLong()));
            }else{
                setFinishDateTimeString("No indicado");
            }

            if(!event.get("image").isJsonNull()){
                setImgUrl(event.get("image").getAsString());
            }else{
                setImgUrl("");
            }

            if(!event.get("description").isJsonNull()){
                setDescription(event.get("description").getAsString());
            }else{
                setDescription("No indicado");
            }

            if(!event.get("website").isJsonNull()){
                setEventLink(event.get("website").getAsString());
            }else{
                setEventLink("No indicado");
            }

            if(!event.get("place").isJsonNull()){
                setReferencePlace(event.get("place").getAsJsonObject().toString());
            }else{
                setReferencePlace("");
            }

            if(!event.get("information").isJsonNull()){
                setScheduleLink(event.get("information").getAsString());
            }else{
                setScheduleLink("");
            }

    }
    public Event(String name, String imgUrl) {
        this.setName(name);
        this.setImgUrl(imgUrl);
    }

    public Event(String name, Calendar startDateTime, int iconId) {
        this.setName(name);
        this.setStartDateTime(startDateTime);
        this.iconId = iconId;
    }

    public Event(String name, String referencePlace, Calendar startDateTime, Calendar finishDateTime,String description, int iconId) {
        this.setName(name);
        this.setReferencePlace(referencePlace);
        this.setStartDateTime(startDateTime);
        this.setFinishDateTime(finishDateTime);
        this.setDescription(description);
        this.iconId = iconId;
    }

    public Event(String name, String referencePlace, Calendar startDateTime, Calendar finishDateTime, String description, String imgUrl) {
        this.setName(name);
        this.setReferencePlace(referencePlace);
        this.setStartDateTime(startDateTime);
        this.setFinishDateTime(finishDateTime);
        this.setDescription(description);
        this.setImgUrl(imgUrl);
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
        dest.writeString(getName());
        dest.writeString(getImgUrl());
        dest.writeString(getReferencePlace());
        if(getStartDateTime() !=null)
        {
            dest.writeInt(getStartDateTime().get(Calendar.YEAR));
            dest.writeInt(getStartDateTime().get(Calendar.MONTH));
            dest.writeInt(getStartDateTime().get(Calendar.DAY_OF_MONTH));
            dest.writeInt(getStartDateTime().get(Calendar.HOUR_OF_DAY));
            dest.writeInt(getStartDateTime().get(Calendar.MINUTE));
            dest.writeInt(getStartDateTime().get(Calendar.SECOND));
        }
        else
        {
            writeDefaultDateTime(dest);
        }
        if(getFinishDateTime() !=null)
        {
            dest.writeInt(getFinishDateTime().get(Calendar.YEAR));
            dest.writeInt(getFinishDateTime().get(Calendar.MONTH));
            dest.writeInt(getFinishDateTime().get(Calendar.DAY_OF_MONTH));
            dest.writeInt(getFinishDateTime().get(Calendar.HOUR_OF_DAY));
            dest.writeInt(getFinishDateTime().get(Calendar.MINUTE));
            dest.writeInt(getFinishDateTime().get(Calendar.SECOND));
        }
        else
        {
            writeDefaultDateTime(dest);
        }
        dest.writeString(getDescription());
        dest.writeInt(iconId);
        dest.writeString(getEventLink());
        dest.writeInt(getEventId());
    }

    private void readFromParcel(Parcel in) {
        setName(in.readString());
        setImgUrl(in.readString());
        setReferencePlace(in.readString());
        getStartDateTime().set(in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt());
        getFinishDateTime().set(in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt());
        setDescription(in.readString());
        iconId=in.readInt();
        setEventLink(in.readString());
        setEventId(in.readInt());
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

    private String unixToDate(long date){
        DateAndTimeDealer dateAndTimeDealer =  new DateAndTimeDealer();
        String convertedDate = CalendarToString(dateAndTimeDealer.getInstance().turnMilisIntoCalendar(date));
        return convertedDate;
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

    public String getStartDateTimeString() {
        return startDateTimeString;
    }

    public void setStartDateTimeString(String startDateTimeString) {
        this.startDateTimeString = startDateTimeString;
    }

    public String getFinishDateTimeString() {
        return finishDateTimeString;
    }

    public void setFinishDateTimeString(String finishDateTimeString) {
        this.finishDateTimeString = finishDateTimeString;
    }

    public String getScheduleLink() {
        return scheduleLink;
    }

    public void setScheduleLink(String scheduleLink) {
        this.scheduleLink = scheduleLink;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
}
