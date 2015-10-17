package wan.wanmarcos.utils;

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

    public static final String PLATFORM = "Android";

    public static final int FRAGMENT_LIST_TEACHER_LAYOUT= R.layout.fragment_teacher_list;
    public static final int FRAGMENT_PROFILE_TEACHER_LAYOUT=R.layout.fragment_teacher_profile;
    public static final int FRAGMENT_TEACHER_COURSE_LAYOUT=R.layout.fragment_teacher_course;
    public static final int PROFILE_INFORMATION=R.layout.generic_profile_view;
    public static final int TEACHER_NEW_ITEM=R.layout.teacher_new_item;
    public static final int SECTION_LIST=R.layout.fragment_generic_list;
    public static final int RATING_NEW_ITEM=R.layout.rating_new_item;
    public static final int COURSE_NEW_ITEM=R.layout.course_new_item;
    public static final int TEACHER_TITLE=R.layout.generic_teacher_title;
    public static final int COURSE_TITLE=R.layout.generic_course_title;

}
