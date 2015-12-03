
package wan.wanmarcos.utils.Redirection;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.JsonElement;

import java.util.Objects;
import java.util.Stack;

import retrofit.Callback;
import wan.wanmarcos.R;
import wan.wanmarcos.activities.ContactanosActivity;
import wan.wanmarcos.activities.EventsActivity;
import wan.wanmarcos.activities.HomeActivity;
import wan.wanmarcos.activities.MainActivity;
import wan.wanmarcos.activities.PlaceActivity;
import wan.wanmarcos.activities.ProfileActivity;
import wan.wanmarcos.activities.TeacherActivity;
import wan.wanmarcos.fragments.EventPageFragment;
import wan.wanmarcos.fragments.EventViewListFragment;
import wan.wanmarcos.fragments.HomeListNewsFragment;
import wan.wanmarcos.fragments.NavigationDrawerFragment;
import wan.wanmarcos.fragments.PlaceListFragment;
import wan.wanmarcos.fragments.PlaceProfileFragment;
import wan.wanmarcos.fragments.Place_SectionListFragment;
import wan.wanmarcos.fragments.ProfileUserFragment;
import wan.wanmarcos.fragments.SuggestedEventFragment;
import wan.wanmarcos.fragments.TeacherCourseProfileFragment;
import wan.wanmarcos.fragments.TeacherListFragment;
import wan.wanmarcos.fragments.TeacherProfileFragment;
import wan.wanmarcos.models.Course;
import wan.wanmarcos.models.Event;
import wan.wanmarcos.models.Teacher;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.utils.Storage;

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
            showActivity(myPreviousActivity, activity);
        }
    }
    public void logOut(AppCompatActivity myPreviousActivity){
        Storage.getSingelton().clearData();
        Intent myLogin=new Intent(myPreviousActivity.getApplicationContext(), getActivityClass(Constants.LOGIN_ACTIVITY));
        myLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        myPreviousActivity.finish();
        myPreviousActivity.startActivity(myLogin);
    }

    @Override
    public void showActivity(AppCompatActivity myPreviousActivity,Class myActivity) {
        if(!myPreviousActivity.getClass().getName().equals(myActivity.getName())){
            defaultConfiguration=true;
            Intent myNewView=new Intent(myPreviousActivity.getApplicationContext(),myActivity);
            if(!myPreviousActivity.getClass().getName().equals(Constants.HOME_ACTIVITY)){
                myPreviousActivity.finish();
            }
            myPreviousActivity.startActivity(myNewView);
        }
        else{
            clearFragments(myPreviousActivity);
        }
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
        else if(name.equals(Constants.PLACE_ACTIVITY)){
            return PlaceActivity.class;
        }
        else if(name.equals(Constants.PROFILE_ACTIVITY)){
            return ProfileActivity.class;
        }else if(name.equals(Constants.LOGIN_ACTIVITY)){
            return MainActivity.class;
        }else
        {
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

        else if(name.equals(Constants.FRAGMENT_LIST_EVENT)){
            return new EventViewListFragment();
        }
        else if(name.equals(Constants.FRAGMENT_DETAIL_EVENT)){
            return new EventPageFragment();
        }
        else if(name.equals(Constants.FRAGMENT_SUGGEST_EVENT)){
            return new SuggestedEventFragment();
        }
        else if(name.equals(Constants.FRAGMENT_LIST_PLACE)){
            return new PlaceListFragment();
        }
        else if(name.equals(Constants.FRAGMENT_PROFILE_PLACE)){
            return new PlaceProfileFragment();
        }
        else if(name.equals(Constants.FRAGMENT_LIST_NEWS)){
            return new HomeListNewsFragment();
        }
        else if(name.equals(Constants.FRAGMENT_PROFILE)){
            return new ProfileUserFragment();
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
    public void clearFragments(AppCompatActivity myAppcoAppCompatActivity){
        FragmentManager fragmentManager=myAppcoAppCompatActivity.getSupportFragmentManager();
        int fragmentCount=fragmentManager.getBackStackEntryCount();
        for(int i=0;i<fragmentCount;i++){
            fragmentManager.popBackStack();
        }
    }
}
