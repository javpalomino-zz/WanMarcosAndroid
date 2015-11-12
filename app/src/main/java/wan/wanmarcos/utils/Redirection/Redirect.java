
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

import java.util.Objects;
import java.util.Stack;

import wan.wanmarcos.R;
import wan.wanmarcos.activities.ContactanosActivity;
import wan.wanmarcos.activities.EventsActivity;
import wan.wanmarcos.activities.HomeActivity;
import wan.wanmarcos.activities.TeacherActivity;
import wan.wanmarcos.fragments.EventPageFragment;
import wan.wanmarcos.fragments.EventViewListFragment;
import wan.wanmarcos.fragments.NavigationDrawerFragment;
import wan.wanmarcos.fragments.SuggestedEventFragment;
import wan.wanmarcos.fragments.TeacherCourseProfileFragment;
import wan.wanmarcos.fragments.TeacherListFragment;
import wan.wanmarcos.fragments.TeacherProfileFragment;
import wan.wanmarcos.models.Course;
import wan.wanmarcos.models.Event;
import wan.wanmarcos.models.Teacher;
import wan.wanmarcos.utils.Constants;

/**
 * Created by postgrado on 31/10/15.
 */
public class Redirect implements Redirection {
    private boolean defaultConfiguration;
    private AppCompatActivity homeActivity;
    private static Redirect singeltonObject;
    private Redirect(){
        defaultConfiguration=true;
    }

    public static Redirect getSingelton(){
        if(singeltonObject==null){
            singeltonObject=new Redirect();
        }
        return singeltonObject;
    }

    public void showActivity(Fragment actualFragment, String name) {
        showActivity((AppCompatActivity) actualFragment.getActivity(),name);
    }
    public void showActivity(AppCompatActivity myPreviousActivity,String name){
        Class activity=getActivityClass(name);
        if(!activity.getName().equals(Object.class.getName())){
            showActivity(myPreviousActivity,activity);
        }
    }

    @Override
    public void showActivity(AppCompatActivity myPreviousActivity,Class myActivity) {
        Intent myNewView=new Intent(myPreviousActivity.getApplicationContext(),myActivity);
        if(!myPreviousActivity.getClass().getName().equals(Constants.HOME_ACTIVITY)){
            myPreviousActivity.finish();
        }
        myPreviousActivity.startActivity(myNewView);
    }

    private Class getActivityClass(String name){
        if(name.equals(Constants.HOME_ACTIVITY)){
            return HomeActivity.class;
        }
        else if(name.equals(Constants.EVENT_ACTIVITY)){
            return EventsActivity.class;
        }
        else if(name.equals(Constants.TEACHER_ACTIVITY)){
            return TeacherActivity.class;
        }
        else if(name.equals(Constants.CONTACT_ACTIVITY)){
            return ContactanosActivity.class;
        }
        else{
            return Object.class;
        }
    }
    private Fragment getFragment(String name){
        if(name.equals(Constants.FRAGMENT_LIST_TEACHER)){
            return new TeacherListFragment();
        }
        else if(name.equals(Constants.FRAGMENT_PROFILE_TEACHER)){
            return new TeacherProfileFragment();
        }
        else if(name.equals(Constants.FRAGMENT_TEACHER_COURSE)){
            return new TeacherCourseProfileFragment();
        }
        else{
            return new Fragment();
        }
    }
    public void showFragment(Fragment myFragment,int containerID,String fragmentName){
        showFragment((AppCompatActivity) myFragment.getActivity(),containerID,fragmentName);
    }
    public void showFragment(AppCompatActivity myActivity,int containerID,String fragmentName){
        Fragment myFragment=getFragment(fragmentName);
        if(!myFragment.getClass().getName().equals(Fragment.class.getName())){
            showFragment(myActivity,containerID,myFragment);
        }
    }

    public void showFragment(AppCompatActivity myActivity, int containerID, Fragment fragmentView) {
        try{
            FragmentTransaction fragmentTransaction=myActivity.getSupportFragmentManager().beginTransaction();
            if(!defaultConfiguration){
                fragmentTransaction.replace(containerID, fragmentView);
                fragmentTransaction.addToBackStack(fragmentView.getClass().getName());
            }

            else{
                fragmentTransaction.add(containerID, fragmentView);
                defaultConfiguration=false;
            }
            fragmentTransaction.commitAllowingStateLoss();
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

    /*
    private Stack<String> mActivityStack;
    private int mFragmentStack;
    private boolean firstAction;
    private Boolean firstConfiguration;
    private int screenOrientation;
    private static Redirect singletonObject;
    private final String DOC_ACTIVITY= Constants.TEACHER_ACTIVITY;
    private final String HOME_ACTIVITY= Constants.HOME_ACTIVITY;
    private final String EVT_ACTIVITY=Constants.EVENT_ACTIVITY;
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
    public void setActivity(AppCompatActivity activity,int id){
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
            else if(data instanceof Event){
                storeEventData((Event)data);
            }
        }
    }
    private void storeEventData(Event event){
        mdata.putString("eventimage",event.getImgUrl());
        mdata.putString("eventreference",event.getReferencePlace());
        mdata.putString("eventdatestart",event.CalendarToString(event.getStartDateTime()));
        mdata.putString("eventdateend",event.CalendarToString(event.getFinishDateTime()));
        mdata.putString("event_description",event.getDescription());
        mdata.putString("eventlink",event.getEventLink());
        mdata.putString("eventname",event.getName());
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
                else if (tag.equals(Constants.EVENT_ACTIVITY)){
                    activity=EventsActivity.class;
                }
                else if(tag.equals(Constants.CONTACT_ACTIVITY)){
                    activity= ContactanosActivity.class;
                }
                Intent intent=new Intent(mActivity.getApplicationContext(),activity);
                mActivity.startActivity(intent);
            }
            else if(!firstAction){
                reload();
            }
        }
    }
    public void reload(){
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
            else if(mActivityStack.peek().equals(EVT_ACTIVITY)){
                changeEventFragments(data);
            }
        }
        else{
            screenOrientation=(screenOrientation==1)?0:1;
        }
    }

    @Override
    public void toContactanosActivity() {

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
    private void changeEventFragments(Object object){
        Fragment fragment=null;
        if(object instanceof Event){
            mFragmentStack++;
            fragment=new EventPageFragment();
        }
        else if(object instanceof Object){
            mFragmentStack++;
            fragment=new SuggestedEventFragment();
        }
        else{
            mFragmentStack=0;
            firstAction=true;
            fragment=new EventViewListFragment();
        }
        tooglingFragments(fragment);
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
        int homeFragment=getContainer();
        try{
            FragmentTransaction fragmentTransaction=mActivity.getSupportFragmentManager().beginTransaction();
            if(!firstConfiguration){
                fragmentTransaction.replace(homeFragment, fragment);
                fragmentTransaction.addToBackStack(fragment.getClass().getName());
            }

            else{
                fragmentTransaction.add(homeFragment, fragment);
                firstConfiguration=false;
            }
            fragmentTransaction.commitAllowingStateLoss();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    private int getContainer(){
        return R.id.home_fragment;
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

    }*/
}
