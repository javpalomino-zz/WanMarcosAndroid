package wan.wanmarcos.utils;

import android.support.v4.app.Fragment;

import wan.wanmarcos.R;
import wan.wanmarcos.activities.ContactanosActivity;
import wan.wanmarcos.activities.EventsActivity;
import wan.wanmarcos.activities.HomeActivity;
import wan.wanmarcos.activities.PlaceActivity;
import wan.wanmarcos.activities.ProfileActivity;
import wan.wanmarcos.activities.TeacherActivity;
import wan.wanmarcos.fragments.EventPageFragment;
import wan.wanmarcos.fragments.EventViewListFragment;
import wan.wanmarcos.fragments.HomeListNewsFragment;
import wan.wanmarcos.fragments.PlaceProfileFragment;
import wan.wanmarcos.fragments.SuggestedEventFragment;
import wan.wanmarcos.fragments.TeacherCourseProfileFragment;
import wan.wanmarcos.fragments.TeacherListFragment;
import wan.wanmarcos.fragments.PopupCommentFragment;
import wan.wanmarcos.fragments.TeacherProfileFragment;

/**
 * Created by javier on 25/09/15.
 */
public class Constants {

    public static final String EMPTY_STRING="";

    public static final int TEACHER_CONTAINER=R.id.home_fragment;
    public static final int EVENT_CONTAINER=R.id.home_fragment;
    public static final int PLACE_CONTAINER=R.id.places_fragment;
    public static final int HOME_CONTAINER = R.id.home_fragment;
    public static final String NOT_FOUND="Busqueda no exitosa";
    //ACTIVITIES
    public static final String TEACHER_ACTIVITY= TeacherActivity.class.getName();
    public static final String HOME_ACTIVITY= HomeActivity.class.getName();
    public static final String EVENT_ACTIVITY= EventsActivity.class.getName();
    public static final String CONTACT_ACTIVITY= ContactanosActivity.class.getName();
    public static final String PLACE_ACTIVITY= PlaceActivity.class.getName();
    public static final String PROFILE_ACTIVITY= ProfileActivity.class.getName();

    public static final String FRAGMENT_LIST_TEACHER= TeacherListFragment.class.getName();
    public static final String FRAGMENT_PROFILE_TEACHER= TeacherProfileFragment.class.getName();
    public static final String FRAGMENT_TEACHER_COURSE= TeacherCourseProfileFragment.class.getName();
    public static final String FRAGMENT_LIST_NEWS =HomeListNewsFragment.class.getName();

    public static final String FRAGMENT_LIST_PLACE= Fragment.class.getName();


    public static final String FRAGMENT_LIST_EVENT= EventViewListFragment.class.getName();
    public static final String FRAGMENT_SUGGEST_EVENT = SuggestedEventFragment.class.getName();
    public static final String FRAGMENT_DETAIL_EVENT= EventPageFragment.class.getName();



    public static final String PREFERENCES = "WanMarcos_preferences";


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
    public static final String TEACHERS ="professors" ;

    //SCREEN DEVICE CONSTANTS
    public static float DEVICE_WIDTH=0;
    public static float DEVICE_WIDTH2=0;
    public static float DEVICE_HEIGHT=0;
    public static float DEVICE_HEIGHT2=0;
    public static float DEVICE_DENSITY=0;
    public static final int CANTIDAD=5;


    //LAYOUTS

    //Entire fragment layouts
    public static final int FRAGMENT_LIST_TEACHER_LAYOUT= R.layout.fragment_teacher_list;
    public static final int FRAGMENT_PROFILE_TEACHER_LAYOUT=R.layout.fragment_teacher_profile;
    public static final int FRAGMENT_TEACHER_COURSE_LAYOUT=R.layout.fragment_teacher_course;
    public static final int FRAGMENT_TEACHER_COMMENT_LAYOUT= R.layout.fragment_teacher_popup_comment;
    public static final int FRAGMENT_LIST_PLACE_LAYOUT=R.layout.fragment_place_list;
    public static final int FRAGMENT_LIST_NEWS_LAYOUT = R.layout.fragment_home_list;
    public static final int FRAGMENT_PROFILE_PLACE_LAYOUT=R.layout.fragment_place_profile;
    //Sections inside fragment

    public static final int FRAGMENT_SECTION_LIST_PLACE_LAYOUT=R.layout.fragment_place__section_list;
    //Items
    public static final int TEACHER_NEW_ITEM=R.layout.teacher_new_item;
    public static final int VALUATION_NEW_ITEM=R.layout.valuation_new_item;
    public static final int HOME_NEW_ITEM=R.layout.home_new_item;
    public static final int RATING_NEW_ITEM=R.layout.rating_new_item;
    public static final int COURSE_NEW_ITEM=R.layout.course_new_item;
    public static final int PROFILE_TEACHER=R.layout.detail_teacher;
    public static final int PLACE_NEW_ITEM=R.layout.place_new_item;
    public static final int EVENT_NEW_ITEM=R.layout.event_new_item;
    public static final int DETAIL_COURSE_TEACHER=R.layout.detail_course_teacher;
    public static final int DETAIL_PLACE=R.layout.detail_place;
    //Generic things


    public static final String EVENTS = "events";
    public static final String EVENTS_DETAIL = "events/{id}";
    //MODALS
    public static String MODAL_TITLE_CONTACTANOS = "Contáctanos";
    public static String MODAL_MESSAGE_CONTACTANOS = "Gracias por darnos tu opinión";
    public static String MODAL_BUTTON_DENADA = "De Nada";

    public static String FRAGMENT_PROFILE_PLACE =PlaceProfileFragment.class.getName();
}
