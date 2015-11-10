package wan.wanmarcos.utils;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Francisco on 10/11/2015.
 */
public class DateAndTimeDealer {
    private static DateAndTimeDealer instance;

    public DateAndTimeDealer()
    {
        instance=this;
    }

    public long turnCalendarIntoMilis(Calendar cal)
    {
        long milis;
        milis=cal.getTimeInMillis()/1000;
        return milis;
    }

    public Calendar turnMilisIntoCalendar(long milis)
    {
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(milis*1000);
        return cal;
    }

    public static DateAndTimeDealer getInstance() {
        return instance;
    }

    public static void setInstance(DateAndTimeDealer instance) {
        DateAndTimeDealer.instance = instance;
    }
}
