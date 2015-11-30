package wan.wanmarcos.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import wan.wanmarcos.activities.MainActivity;
import wan.wanmarcos.models.Course;
import wan.wanmarcos.models.Event;
import wan.wanmarcos.models.Teacher;

/**
 * Created by soporte on 11/11/15.
 */
public class Storage {
    public static String KEY_TEACHER_ID="teacherid";
    public static String KEY_COURSE_ID="courseid";
    public static String KEY_TEACHER_NAME="teachername";
    public static String KEY_COURSE_NAME="coursename";
    public static String KEY_TEACHER_IMAGE="teacherimage";
    public static String KEY_FACULTY_NAME="facultyname";
    public static String KEY_EVENT_ID="eventid";
    public static String KEY_EVENT_IMAGE="eventimage";
    public static String KEY_EVENT_DESCRIPTION="eventdescription";
    public static String KEY_EVENT_LINK="eventlink";
    public static String KEY_EVENT_START_DATE="eventstartdate";
    public static String KEY_EVENT_FINISH_DATE="eventfinishdate";
    public static String KEY_EVENT_REFERENCE="eventreference";
    public static String KEY_EVENT_NAME="eventname";
    public static String KEY_TEACHER_RATING="teacherrating";
    private static Storage singeltonObject;
    private Storage(){

    }
    public static Storage getSingelton(){
        if(singeltonObject==null){
            singeltonObject=new Storage();
        }
        return singeltonObject;
    }

    public void storage(Object data,Fragment myFragment){
        SharedPreferences mySharedPreferences=myFragment.getActivity().getSharedPreferences(Constants.PREFERENCES,Context.MODE_PRIVATE);
        if(data!=null){
            if(data instanceof Teacher){
                storageTeacher(data,mySharedPreferences);
            }
            else if(data instanceof Course){
                storageCourse(data,mySharedPreferences);
            }
            else if(data instanceof Event){
                storageEvent(data,mySharedPreferences);
            }
        }
    }
    private void storageEvent(Object event,SharedPreferences mySharedPreferences){
        Event eventItem=(Event)event;
        saveData(Integer.toString(eventItem.getEventId()), KEY_EVENT_ID, mySharedPreferences);
    }
    private void storageCourse(Object course,SharedPreferences mySharedPreferences){
        Course courseItem=(Course)course;
        saveData(courseItem.getName(),KEY_COURSE_NAME,mySharedPreferences);
        saveData(courseItem.getFaculty(),KEY_FACULTY_NAME,mySharedPreferences);
    }
    private void storageTeacher(Object teacher,SharedPreferences mySharedPreferences){
        Teacher teacherItem=(Teacher) teacher;
        saveData(teacherItem.getName(), KEY_TEACHER_NAME,mySharedPreferences);
        saveData(teacherItem.getImageUrl(),KEY_TEACHER_IMAGE,mySharedPreferences);
    }
    private void saveData(String value,String keyValue,SharedPreferences mySharedPreferences){
        SharedPreferences.Editor editor=mySharedPreferences.edit();
        editor.putString(keyValue, value);
        editor.commit();
    }
    public String getInfo(Fragment myFragmet,String key){
        SharedPreferences myPreferences=myFragmet.getActivity().getSharedPreferences(Constants.PREFERENCES, Context.MODE_PRIVATE);
        return myPreferences.getString(key,Constants.NOT_FOUND);
    }
    public void clearData(AppCompatActivity myFragment){
        SharedPreferences preferences = myFragment.getSharedPreferences(Constants.PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =  preferences.edit();
        editor.clear();
        editor.commit();
    }
    public void clearTeacherActivity(AppCompatActivity myFragment){
        SharedPreferences preferences = myFragment.getSharedPreferences(Constants.PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =  preferences.edit();
        editor.remove(KEY_TEACHER_NAME);
        editor.remove(KEY_TEACHER_IMAGE);
        editor.remove(KEY_TEACHER_ID);
        editor.remove(KEY_COURSE_ID);
        editor.remove(KEY_COURSE_NAME);
        editor.commit();
    }
    public void clearEventActivity(AppCompatActivity myFragment){
        SharedPreferences preferences = myFragment.getSharedPreferences(Constants.PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =  preferences.edit();
        editor.remove(KEY_EVENT_FINISH_DATE);
        editor.remove(KEY_EVENT_LINK);
        editor.remove(KEY_EVENT_DESCRIPTION);
        editor.remove(KEY_EVENT_IMAGE);
        editor.remove(KEY_EVENT_START_DATE);
        editor.remove(KEY_EVENT_REFERENCE);
        editor.commit();
    }
    public void clearActivityData(Fragment myFragment){
        AppCompatActivity myAppCompatActivity= (AppCompatActivity) myFragment.getActivity();
        String activityName=myAppCompatActivity.getClass().getName();
        if(activityName.equals(Constants.TEACHER_ACTIVITY)){
            clearTeacherActivity(myAppCompatActivity);
        }
        else if(activityName.equals(Constants.EVENT_ACTIVITY)){
            clearEventActivity(myAppCompatActivity);
        }

    }
}
