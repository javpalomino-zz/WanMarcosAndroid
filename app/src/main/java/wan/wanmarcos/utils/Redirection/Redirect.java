
package wan.wanmarcos.utils.Redirection;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.Stack;

import wan.wanmarcos.R;
import wan.wanmarcos.activities.HomeActivity;
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
    private Stack<String> mActivityStack;
    private int mFragmentStack;
    private boolean firstAction;
    private Boolean firstConfiguration;
    private int screenOrientation;
    private static Redirect singletonObject;
    private final String DOC_ACTIVITY= Constants.TEACHER_ACTIVITY;
    private final String HOME_ACTIVITY= Constants.HOME_ACTIVITY;
    private final String EVT_ACTIVITY="EVENTOS";
    private Bundle mdata;
    private Object mLock;
    private AppCompatActivity mActivity;
    private Redirect(){
        mdata=new Bundle();
        mLock=new Object();
        firstAction=true;
        mActivity=null;
        firstConfiguration=true;
        mActivityStack=new Stack<String>();
        mActivityStack.add(HOME_ACTIVITY);
        mFragmentStack=0;
    }

    public static synchronized Redirect getSingletonInstance(){
        if(singletonObject==null){
            singletonObject=new Redirect();
        }
        return singletonObject;
    }
    public void setActivity(AppCompatActivity activity){
        mActivity=activity;
        if(firstConfiguration){
            screenOrientation=mActivity.getResources().getConfiguration().orientation-1;
            firstConfiguration=true;
        }
    }
    @Override
    public void changeActivity(String tag, Object data) {
        changeActivity(tag);
    }
    private void storeData(Object data){
        if(data!=null){
            if(data instanceof Teacher){
                storeTeacherData((Teacher) data);
            }
            else if(data instanceof Course){
                storeCourseData((Course)data);
            }
        }
    }
    private void storeTeacherData(Teacher teacher){
        mdata.putString("teachername",teacher.getName());
        mdata.putString("teacherimage", teacher.getImageUrl());
    }
    private void storeCourseData(Course course){
        mdata.putString("coursename",course.getName());
        mdata.putString("coursefaculty", course.getFaculty());

    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void changeActivity(String tag) {
        if(mActivity!=null){
            if(!tag.equals(mActivityStack.peek())){
                firstConfiguration=true;
                firstAction=false;
                mActivityStack.add(tag);
                Class activity = null;

                if(tag.equals(DOC_ACTIVITY)){
                    activity=TeacherActivity.class;
                }
                else if(tag.equals(HOME_ACTIVITY)){
                    activity=HomeActivity.class;
                }
                else{
                    activity=HomeActivity.class;
                }
                Intent intent=new Intent(mActivity.getApplicationContext(),activity);
                mActivity.startActivity(intent);
            }
            else if(!firstAction){
                reload();
            }
        }
    }
    private void reload(){
        int max=mFragmentStack;
        for(int i=0;i<max;i++){
            mActivity.onBackPressed();
        }
    }
    @Override
    public void changeFragment(Object data) {
        storeData(data);
        if(screenOrientation==mActivity.getResources().getConfiguration().orientation-1){
            firstAction=false;
            if(mActivityStack.peek().equals(DOC_ACTIVITY)){
                changeTeacherFragments(data);
            }
        }
        else{
            screenOrientation=(screenOrientation==1)?0:1;
        }
    }

    @Override
    public String getInformation(String key) {
        if(key!=null){
            if(mdata.containsKey(key)){
                return mdata.getString(key);
            }
            return key;
        }
        return key;
    }

    private void changeTeacherFragments(Object object){
        Fragment fragment=null;
        if(object instanceof Teacher){
            mFragmentStack++;
            fragment=new TeacherProfileFragment();
        }
        else if(object instanceof Course){
            mFragmentStack++;
            fragment=new TeacherCourseProfileFragment();
        }
        else{
            mFragmentStack=0;
            firstAction=true;
            fragment=new TeacherListFragment();
        }
        tooglingFragments(fragment);
    }
    private void tooglingFragments(Fragment fragment){
        try{
            FragmentTransaction fragmentTransaction=mActivity.getSupportFragmentManager().beginTransaction();
            if(!firstConfiguration){
                fragmentTransaction.replace(R.id.home_fragment, fragment);
                fragmentTransaction.addToBackStack(fragment.getClass().getName());
            }

            else{
                fragmentTransaction.add(R.id.home_fragment, fragment);
                firstConfiguration=false;
            }
            fragmentTransaction.commit();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    public void setContent(Fragment parent,int id, Fragment fragment){
        FragmentManager fragmentManager=parent.getChildFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(id,fragment);
        fragmentTransaction.commit();
    }
    public void updateActivityStack(){
        if(mFragmentStack==0){
            mActivityStack.pop();
        }
        else{
            mFragmentStack--;
        }

    }
}
