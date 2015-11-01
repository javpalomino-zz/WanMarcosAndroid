package wan.wanmarcos.utils.Redirection;

import android.os.Bundle;

import wan.wanmarcos.models.Course;
import wan.wanmarcos.models.Teacher;

/**
 * Created by carlos-pc on 09/10/15.
 */
public interface Redirection {
    void changeActivity(String tag,Object data);
    void changeActivity(String tag);
    void changeFragment(Object data);
    /*void toListTeachers();
    void toShowEvents();
    void toProfileTeacher(Teacher teacher);
    void toTeacherCourseInformation(Course course);
    void addTeacherInformation(Teacher teacher);
    void addCourseInformation(Course course);
    float getFloatInformation(String key);
    int getIntInformation(String key);
    String getStringInformation(String key);*/
}
