package wan.wanmarcos.models;

import android.os.Parcelable;
import android.os.Parcel;

import java.security.PublicKey;
import java.util.Calendar;

/**
 * Created by javier on 27/09/15.
 */
public class Event implements Parcelable{

    private String name;
    private String imgUrl;
    private String referencePlace;
    private Calendar startDateTime;
    private Calendar finishDateTime;
    private String description;
    private int iconId;

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

        // We just need to write each field into the
        // parcel. When we read from parcel, they
        // will come back in the same order
        System.out.println("WRITING NAME");
        dest.writeString(name);
        System.out.println("WRITING IMG URL");
        dest.writeString(imgUrl);
        System.out.println("WRITING REFERENCE PLACE");
        dest.writeString(referencePlace);
        System.out.println("WRITING START");
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
            dest.writeInt(0);
            dest.writeInt(0);
            dest.writeInt(1);
            dest.writeInt(0);
            dest.writeInt(0);
            dest.writeInt(0);
        }

        System.out.println("WRITING End");
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
            dest.writeInt(0);
            dest.writeInt(0);
            dest.writeInt(1);
            dest.writeInt(0);
            dest.writeInt(0);
            dest.writeInt(0);
        }

        System.out.println("WRITING description");
        dest.writeString(description);
        System.out.println("writing icon id");
        dest.writeInt(iconId);
    }

    private void readFromParcel(Parcel in) {

        // We just need to read back each
        // field in the order that it was
        // written to the parcel
        System.out.println("Reading Name");
        name = in.readString();
        System.out.println("Reading img url");
        imgUrl=in.readString();
        System.out.println("Reading place");
        referencePlace=in.readString();
        System.out.println("Reading start");
        startDateTime.set(in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt());
        System.out.println("Reading end");
        finishDateTime.set(in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt());
        System.out.println("reading description");
        description=in.readString();
        System.out.println("Reading icon id");
        iconId=in.readInt();
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

    public String CalendarToString(Calendar cal)
    {
        String str="";
        if(cal!=null)
        {
            switch (cal.get(Calendar.MONTH))
            {
                case 0 : str=str+"January";break;
                case 1 : str=str+"February";break;
                case 2 : str=str+"March";break;
                case 3 : str=str+"April";break;
                case 4 : str=str+"May";break;
                case 5 : str=str+"June";break;
                case 6 : str=str+"July";break;
                case 7 : str=str+"August";break;
                case 8 : str=str+"September";break;
                case 9 : str=str+"October";break;
                case 10 : str=str+"November";break;
                case 11 : str=str+"December";break;
            }
            str = str + " "+cal.get(Calendar.DAY_OF_MONTH)+" , "+(cal.get(Calendar.YEAR)+ 1900) +" "+cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE)+" hs";
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
}
