package wan.wanmarcos.utils;

import android.support.v4.app.Fragment;

import wan.wanmarcos.R;
import wan.wanmarcos.activities.ContactanosActivity;
import wan.wanmarcos.activities.EventsActivity;
import wan.wanmarcos.activities.HomeActivity;
import wan.wanmarcos.activities.MainActivity;
import wan.wanmarcos.activities.PlaceActivity;
import wan.wanmarcos.activities.ProfileActivity;
import wan.wanmarcos.activities.TeacherActivity;
import wan.wanmarcos.fragments.EditPreferencesFragment;
import wan.wanmarcos.fragments.EventPageFragment;
import wan.wanmarcos.fragments.EventViewListFragment;
import wan.wanmarcos.fragments.HomeListNewsFragment;
import wan.wanmarcos.fragments.PlaceProfileFragment;
import wan.wanmarcos.fragments.ProfileUserFragment;
import wan.wanmarcos.fragments.SuggestedEventFragment;
import wan.wanmarcos.fragments.TeacherCourseProfileFragment;
import wan.wanmarcos.fragments.TeacherListFragment;
import wan.wanmarcos.fragments.TeacherProfileFragment;

/**
 * Created by javier on 25/09/15.
 */
public class Constants {

    public static final String EMPTY_STRING="";

    public static final int TEACHER_CONTAINER=R.id.home_fragment;
    public static final int PROFILE_CONTAINER=R.id.profile_fragment;
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
    public static final String LOGIN_ACTIVITY= MainActivity.class.getName();

    public static final String FRAGMENT_LIST_TEACHER= TeacherListFragment.class.getName();
    public static final String FRAGMENT_PROFILE_TEACHER= TeacherProfileFragment.class.getName();
    public static final String FRAGMENT_TEACHER_COURSE= TeacherCourseProfileFragment.class.getName();
    public static final String FRAGMENT_LIST_NEWS =HomeListNewsFragment.class.getName();
    public static final String FRAGMENT_PROFILE=ProfileUserFragment.class.getName();

    public static final String FRAGMENT_LIST_PLACE= Fragment.class.getName();


    public static final String FRAGMENT_LIST_EVENT= EventViewListFragment.class.getName();
    public static final String FRAGMENT_SUGGEST_EVENT = SuggestedEventFragment.class.getName();
    public static final String FRAGMENT_DETAIL_EVENT= EventPageFragment.class.getName();
    public static final String FRAGMENT_EDIT_PREFERENCE= EditPreferencesFragment.class.getName();



    public static final String PREFERENCES = "WanMarcos_preferences";


    public static final String WANMARCOS_BASE_URL = "http://52.35.45.112";
    public static final String WANMARCOS_API_VERSION = "v1";
    public static final String WANMARCOS_API_BASE_URL = WANMARCOS_BASE_URL+"/api/"+WANMARCOS_API_VERSION+"/";

    public static final String SIGN_UP = "users";
    public static final String LOGIN = "authenticate";
    public static final String USER_INFO = "users/me";
    public static final String SUGGESTIONS = "suggestions";
    public static final String REFRESH = "refresh";
    public static final String AUTOCOMPLETE_FACULTIES = "autocomplete/faculties";
    public static final String AUTOCOMPLETE_CARREERS = "autocomplete/degrees";
    public static final String AUTOCOMPLETE_PLACES="autocomplete/places";
    public static final String AUTOCOMPLETE_CATEGORIES="autocomplete/categories";
    public static final String CHANGE_PROFILE_INFORMATION="/users/profile";
    public static final String CHANGE_PROFILE_PHOTO="users/image";

    public static final String HEADER = "Bearer ";

    public static final String PLATFORM = "Android";
    public static final String TEACHERS ="professors";
    public static final String TEACHER_DETAIL ="professors/{id}";
    public static final String TEACHER_COURSES="subjects";
    public static final String TEACHER_COURSE_DETAIL="subjects/{subject_id}";
    public static final String COURSE_COMMENTS="comments";
    public static final String NEWS="home";

    //SCREEN DEVICE CONSTANTS
    public static float DEVICE_WIDTH=0;
    public static float DEVICE_WIDTH2=0;
    public static float DEVICE_HEIGHT=0;
    public static float DEVICE_HEIGHT2=0;
    public static float DEVICE_DENSITY=0;
    public static final int CANTIDAD=6;


    //LAYOUTS

    //Entire fragment layouts
    public static final int FRAGMENT_LIST_TEACHER_LAYOUT= R.layout.fragment_teacher_list;
    public static final int FRAGMENT_PROFILE_TEACHER_LAYOUT=R.layout.fragment_teacher_profile;
    public static final int FRAGMENT_TEACHER_COURSE_LAYOUT=R.layout.fragment_teacher_course;
    public static final int FRAGMENT_TEACHER_COMMENT_LAYOUT= R.layout.fragment_teacher_popup_comment;
    public static final int PERSONAL_INFO_POPUP=R.layout.fragment_personal_info_popup;
    public static final int FRAGMENT_LIST_PLACE_LAYOUT=R.layout.fragment_place_list;
    public static final int FRAGMENT_LIST_NEWS_LAYOUT = R.layout.fragment_home_list;
    public static final int FRAGMENT_PROFILE_PLACE_LAYOUT=R.layout.fragment_place_profile;
    public static final int FRAGMENT_PROFILE_PREFERENCES_LIST=R.layout.fragment_preferences_list;
    //Sections inside fragment

    public static final int FRAGMENT_SECTION_LIST_PLACE_LAYOUT=R.layout.fragment_place__section_list;
    //Items
    public static final int TEACHER_NEW_ITEM=R.layout.teacher_new_item;
    public static final int VALUATION_NEW_ITEM=R.layout.valuation_new_item;
    public static final int PREFERENCE_NEW_ITEM=R.layout.preference_list_item;
    public static final int USER_PROFILE_HEADER=R.layout.fragment_user_profile_header;
    public static final int HOME_NEW_ITEM=R.layout.home_new_item;
    public static final int RATING_NEW_ITEM=R.layout.rating_new_item;
    public static final int COURSE_NEW_ITEM=R.layout.course_new_item;
    public static final int PROFILE_TEACHER=R.layout.detail_teacher;
    public static final int PLACE_NEW_ITEM=R.layout.place_new_item;
    public static final int EVENT_NEW_ITEM=R.layout.event_list_item;
    public static final int EDIT_PREFERENCES_NEW_ITEM=R.layout.edit_preferences_faculty_list_item;
    public static final int DETAIL_COURSE_TEACHER=R.layout.detail_course_teacher;
    public static final int DETAIL_PLACE=R.layout.detail_place;
    public static final int MAX_LENGTH_FILE = 2048*1024;
    //Generic things


    public static final String EVENTS = "events";
    public static final String EVENTS_DETAIL = "events/{id}";
    //MODALS
    public static String MODAL_TITLE_CONTACTANOS = "Contáctanos";
    public static String MODAL_MESSAGE_CONTACTANOS = "Gracias por darnos tu opinión";
    public static String MODAL_BUTTON_DENADA = "De Nada";

    public static String MODAL_TITLE_ERROR_PHOTO = "¡ Advertencia !";
    public static String MODAL_MESSAGE_ERROR_PHOTO = "Ocurrio un error al intentar actualizar tu foto de perfil .Por favor , inténtalo de nuevo.";
    public static String MODAL_ERROR_PHOTO_BUTTON_OK = "OK";

    public static String FRAGMENT_PROFILE_PLACE =PlaceProfileFragment.class.getName();
    public static String name;
}
