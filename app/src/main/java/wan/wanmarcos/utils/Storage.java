package wan.wanmarcos.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;

import wan.wanmarcos.models.Course;
import wan.wanmarcos.models.Teacher;

/**
 * Created by soporte on 11/11/15.
 */
public class Storage {
    private String KEY_TEACHER_ID="teacherid";
    private String KEY_COURSE_ID="courseid";
    private String KEY_TEACHER_NAME="teachername";
    private String KEY_COURSE_NAME="coursename";
    private String KEY_TEACHER_IMAGE="teacherimage";

    public void storage(Object data,Fragment myFragment){
        SharedPreferences myPreferences=myFragment.getActivity().getSharedPreferences(Constants.PREFERENCES, Context.MODE_PRIVATE);
        if(data==null){
            if(data instanceof Teacher){
                storageTeacher(data,myPreferences);
            }
            else if(data instanceof Course){
                storageCourse(data,myPreferences);
            }
        }
    }
    private void storageCourse(Object course,SharedPreferences myPreferences){
        Course courseItem=(Course)course;
        saveData(courseItem.getName(),KEY_COURSE_NAME,myPreferences);
    }
    private void storageTeacher(Object teacher,SharedPreferences myPrefereces){
        Teacher teacherItem=(Teacher) teacher;
        saveData(teacherItem.getName(),KEY_TEACHER_NAME,myPrefereces);
        saveData(teacherItem.getImageUrl(),KEY_TEACHER_IMAGE,myPrefereces);
    }
    private void saveData(String value,String keyValue,SharedPreferences myPreferences){
        SharedPreferences.Editor editor = myPreferences.edit();
        editor.putString(keyValue, value);
        editor.commit();
    }
    public String getInfo(Fragment myFragmet,String key){
        SharedPreferences myPreferences=myFragmet.getActivity().getSharedPreferences(Constants.PREFERENCES, Context.MODE_PRIVATE);
        return myPreferences.getString(key,Constants.NOT_FOUND);
    }
}
