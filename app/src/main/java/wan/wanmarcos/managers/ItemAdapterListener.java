package wan.wanmarcos.managers;

import android.view.View;

import java.util.List;

/**
 * Created by carlos-pc on 25/10/15.
 */
public interface ItemAdapterListener<T> {
    void itemClicked(View view,T object);

    void addClicked(String fragmentProfileTeacher);
}
