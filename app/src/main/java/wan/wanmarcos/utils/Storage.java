package wan.wanmarcos.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import wan.wanmarcos.activities.MainActivity;
import wan.wanmarcos.models.Course;
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
        }
    }
    private void storageCourse(Object course,SharedPreferences mySharedPreferences){
        Course courseItem=(Course)course;
        saveData(courseItem.getName(),KEY_COURSE_NAME,mySharedPreferences);
    }
    private void storageTeacher(Object teacher,SharedPreferences mySharedPreferences){
        Teacher teacherItem=(Teacher) teacher;
        saveData(teacherItem.getName(), KEY_TEACHER_NAME,mySharedPreferences);
        saveData(teacherItem.getImageUrl(),KEY_TEACHER_IMAGE,mySharedPreferences);
    }
    private void saveData(String value,String keyValue,SharedPreferences mySharedPreferences){
        SharedPreferences.Editor editor=mySharedPreferences.edit();
        editor.putString(keyValue,value);
        editor.commit();
    }
    public String getInfo(Fragment myFragmet,String key){
        SharedPreferences myPreferences=myFragmet.getActivity().getSharedPreferences(Constants.PREFERENCES, Context.MODE_PRIVATE);
        return myPreferences.getString(key,Constants.NOT_FOUND);
    }
}