package wan.wanmarcos.views.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by postgrado on 17/10/15.
 */
public abstract  class CustomListAdapter<T> extends BaseAdapter implements ListAdapter{
    private int actualChar=56;
    private final int mResource;
    private final Object mLock=new Object();
    private final Context mContext;
    private final LayoutInflater mInflater;
    private List<T> mObjects;



    public CustomListAdapter(Context context,int resourceID,List<T> listObjects ){
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mResource =  resourceID;
        mObjects = listObjects;
    }
    public CustomListAdapter(Context context,int resourceID){
        this(context,resourceID,new ArrayList<T>());
    }
    public Context getContext(){
        return mContext;
    }
    @Override
    public int getCount() {
        return mObjects.size();
    }

    @Override
    public T getItem(int position) {
        return mObjects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        if (convertView == null) {
            view = mInflater.inflate(mResource, parent, false);


        } else {
            view = convertView;
        }
        setElements(view,position);
        return view;
    }
    public void add(T object) {
        synchronized (mLock) {
            mObjects.add(object);
        }
    }


    abstract void setElements(View view,int position);

}