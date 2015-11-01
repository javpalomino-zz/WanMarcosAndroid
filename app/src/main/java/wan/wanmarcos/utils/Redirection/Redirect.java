package wan.wanmarcos.utils.Redirection;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import wan.wanmarcos.R;
import wan.wanmarcos.activities.HomeActivity;
import wan.wanmarcos.activities.MainActivity;
import wan.wanmarcos.activities.TeacherActivity;
import wan.wanmarcos.fragments.TeacherCourseProfileFragment;
import wan.wanmarcos.fragments.TeacherListFragment;
import wan.wanmarcos.fragments.TeacherProfileFragment;
import wan.wanmarcos.models.Course;
import wan.wanmarcos.models.Teacher;
import wan.wanmarcos.utils.Constants;

/**
 * Created by postgrado on 31/10/15.
 */
public class Redirect implements Redirection {
    private static Redirect singletonObject;
    private final String DOC_ACTIVITY= Constants.TEACHER_ACTIVITY   ;
    private final String EVT_ACTIVITY="EVENTOS";
    private String mActualActivity;
    private Bundle mdata;
    private Object mLock;
    private Activity mActivity;
    private Redirect(){
        mdata=new Bundle();
        mLock=new Object();
        mActivity=null;
        mActualActivity="";
    }

    public static synchronized Redirect getSingletonInstance(){
        if(singletonObject==null){
            singletonObject=new Redirect();
        }
        return singletonObject;
    }
    public void setActivity(Activity activity){
        mActualActivity=activity.getClass().getName();
        mActivity=activity;
    }
    @Override
    public void changeActivity(String tag, Object data) {
        //TODO:poner data en el bundle
        changeActivity(tag);
    }

    @Override
    public void changeActivity(String tag) {
        if(mActivity!=null && !tag.equals(mActualActivity)){
            Class activity = null;

            if(tag.equals(DOC_ACTIVITY)){
                activity=TeacherActivity.class;
            }
            else{
                activity=HomeActivity.class;
            }
            Intent intent=new Intent(mActivity.getApplicationContext(),activity);
            mActivity.startActivity(intent);
        }
    }

    @Override
    public void changeFragment(Object data) {
        if(mActualActivity.equals(DOC_ACTIVITY)){
            changeTeacherFragments(data);
        }

    }
    private void changeTeacherFragments(Object object){
        Fragment fragment=null;
        if(object instanceof Teacher){
            fragment=new TeacherProfileFragment();
        }
        else if(object instanceof Course){
            fragment=new TeacherCourseProfileFragment();
        }
        else{
            fragment=new TeacherListFragment();
        }
        tooglingFragments(fragment);
    }
    private void tooglingFragments(Fragment fragment){
        try{
            FragmentTransaction fragmentTransaction=mActivity.getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.home_fragment,fragment);
            Log.d("D",":C");
            fragmentTransaction.commit();

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }/*
    @Override
    public void toListTeachers() {

        TeacherListFragment teacherListFragment= new TeacherListFragment();
        try {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.event_new_fragment, teacherListFragment);
            transaction.addToBackStack("teacherFragment");

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*
    @Override
    public void toShowEvents() {
        EventNewsFragment eventNewsFragment=new EventNewsFragment();
        try {
            FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.event_new_fragment,eventNewsFragment);
            fragmentTransaction.addToBackStack("events");
            fragmentTransaction.commit();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void toProfileTeacher(Teacher teacher) {
        TeacherProfileFragment profileFragment=new TeacherProfileFragment();
        try {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.event_new_fragment, profileFragment);
            transaction.addToBackStack("profilefragment");

            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void toTeacherCourseInformation(Course course) {
        TeacherCourseProfileFragment teacherCourseProfileFragment=new TeacherCourseProfileFragment();
        try{
            FragmentTransaction transaction= getFragmentManager().beginTransaction();
            transaction.replace(R.id.event_new_fragment, teacherCourseProfileFragment);
            transaction.addToBackStack("profileteachercoursefragment");

            transaction.commit();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        addCourseInformation(course);
    }

    @Override
    public void addTeacherInformation(Teacher teacher) {
        dataTeacher.putString("teachername", teacher.getName());
        dataTeacher.putString("facultyname",teacher.getFaculties());
        dataTeacher.putString("imageurl", teacher.getImageUrl());
    }

    @Override
    public void addCourseInformation(Course course) {
        dataTeacher.putString("coursename",course.getName());
        dataTeacher.putFloat("courserating", course.getRating());
    }

    @Override
    public float getFloatInformation(String key) {
        return 0;
    }

    @Override
    public int getIntInformation(String key) {
        return 0;
    }

    @Override
    public String getStringInformation(String key) {
        if (dataTeacher.containsKey(key)) {
            return dataTeacher.getString(key);
        } else {
            return null;
        }
    }
    */
}
