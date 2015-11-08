package wan.wanmarcos.utils;

import android.content.Context;
import android.view.View;

import wan.wanmarcos.R;
import wan.wanmarcos.activities.HomeActivity;
import wan.wanmarcos.activities.TeacherActivity;

/**
 * Created by javier on 25/09/15.
 */
public class Constants {
    //ACTIVITIES
    public static final String TEACHER_ACTIVITY= TeacherActivity.class.getName();
    public static final String HOME_ACTIVITY= HomeActivity.class.getName();
    public static final String PREFERENCES = "WanMarcos_preferences";


    public static final String WANMARCOS_BASE_URL = "http://52.89.124.0";
    public static final String WANMARCOS_API_VERSION = "v1";
    public static final String WANMARCOS_API_BASE_URL = WANMARCOS_BASE_URL+"/api/"+WANMARCOS_API_VERSION+"/";

    public static final String SIGN_UP = "users";
    public static final String LOGIN = "authenticate";
    public static final String USER_INFO = "users/me";

    public static final String PLATFORM = "Android";

    //SCREEN DEVICE CONSTANTS
    public static float DEVICE_WIDTH=0;
    public static float DEVICE_WIDTH2=0;
    public static float DEVICE_HEIGHT=0;
    public static float DEVICE_HEIGHT2=0;
    public static float DEVICE_DENSITY=0;


    //LAYOUTS

    //Entire fragment layouts
    public static final int FRAGMENT_LIST_TEACHER_LAYOUT= R.layout.fragment_teacher_list;
    public static final int FRAGMENT_PROFILE_TEACHER_LAYOUT=R.layout.fragment_teacher_profile;
    public static final int FRAGMENT_TEACHER_COURSE_LAYOUT=R.layout.fragment_teacher_course;
    //Sections inside fragment
    public static final int PROFILE_TEACHER_INFORMATION=R.layout.generic_teacher_profile;
    public static final int PROFILE_COURSE_INFORMATION=R.layout.generic_course_profile;

    //Items
    public static final int TEACHER_NEW_ITEM=R.layout.teacher_new_item;
    public static final int VALUATION_NEW_ITEM=R.layout.valuation_new_item;
    public static final int RATING_NEW_ITEM=R.layout.rating_new_item;
    public static final int COURSE_NEW_ITEM=R.layout.course_new_item;
    public static final int TEACHER_TITLE=R.layout.generic_teacher_title;
    public static final int COURSE_TITLE=R.layout.generic_course_title;
    //Generic things
    public static final int SECTION_GENERIC_LIST_LAYOUT=R.layout.generic_list;
    public static String tripleTab(){
        return "\t\t\t\t\t\t\t\t\t";
    }
    public static String doubleTab(){
        return "\t\t\t\t\t\t\t\t\t\t\t\t";
    }


    //IDS
    public static final int TEACHER_LIST_ID=R.id.sub_fragment_teacher_list;

    public static final int PROFILE_TEACHER_ID=R.id.sub_fragment_teacher_profile;
    public static final int PROFILE_TEACHER_TITLE=R.id.sub_fragment_teacher_title;
    public static final int PROFILE_TEACHER_RATING=R.id.sub_fragment_teacher_rating;

    public static final int COURSE_LIST_ID=R.id.sub_fragment_course_list;

    public static final int PROFILE_CORUSE_ID=R.id.sub_fragment_course_profile;
    public static final int PROFILE_COURSE_TITLE=R.id.sub_fragment_course_title;
    public static final int PROFILE_COURSE_RATING=R.id.sub_fragment_course_rating;
    public static final int VALUATION_LIST_ID=R.id.sub_fragment_valuations_course;


}
