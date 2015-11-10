package wan.wanmarcos.utils;

import android.content.Context;
import android.view.View;

import wan.wanmarcos.R;

/**
 * Created by javier on 25/09/15.
 */
public class Constants {
    public static final String WANMARCOS_BASE_URL = "http://52.89.124.0";
    public static final String WANMARCOS_API_VERSION = "v1";
    public static final String WANMARCOS_API_BASE_URL = WANMARCOS_BASE_URL+"/api/"+WANMARCOS_API_VERSION+"/";

    public static final String SIGN_UP = "users";
    public static final String LOGIN = "authenticate";
    public static final String USER_INFO = "users/me";
    public static final String SUGGESTIONS = "suggestions";
    public static final String REFRESH = "refresh";

    public static final String HEADER = "Bearer ";

    public static final String PLATFORM = "Android";

    //SCREEN DEVICE CONSTANTS
    public static float DEVICE_WIDTH=0;
    public static float DEVICE_WIDTH2=0;
    public static float DEVICE_HEIGHT=0;
    public static float DEVICE_HEIGHT2=0;
    public static float DEVICE_DENSITY=0;
    //LAYOUT CONSTANTS
    public static final int FRAGMENT_LIST_TEACHER_LAYOUT= R.layout.fragment_teacher_list;
    public static final int FRAGMENT_PROFILE_TEACHER_LAYOUT=R.layout.fragment_teacher_profile;
    public static final int FRAGMENT_TEACHER_COURSE_LAYOUT=R.layout.fragment_teacher_course;
    public static final int PROFILE_TEACHER_INFORMATION=R.layout.generic_teacher_profile;
    public static final int PROFILE_COURSE_INFORMATION=R.layout.generic_course_profile;
    public static final int TEACHER_NEW_ITEM=R.layout.teacher_new_item;
    public static final int VALUATION_NEW_ITEM=R.layout.valuation_new_item;
    public static final int SECTION_LIST=R.layout.generic_list;
    public static final int RATING_NEW_ITEM=R.layout.rating_new_item;
    public static final int COURSE_NEW_ITEM=R.layout.course_new_item;
    public static final int TEACHER_TITLE=R.layout.generic_teacher_title;
    public static final int COURSE_TITLE=R.layout.generic_course_title;

    public static String tripleTab(){
        return "\t\t\t\t\t\t\t\t\t";
    }
    public static String doubleTab(){
        return "\t\t\t\t\t\t\t\t\t\t\t\t";
    }

    public static final String PREFERENCES = "WanMarcos_preferences";

    //MODALS
    public static String MODAL_TITLE_CONTACTANOS = "Contáctanos";
    public static String MODAL_MESSAGE_CONTACTANOS = "Gracias por darnos tu opinión";
    public static String MODAL_BUTTON_DENADA = "De Nada";

}
