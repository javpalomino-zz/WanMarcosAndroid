package wan.wanmarcos.views.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import wan.wanmarcos.R;

/**
 * Created by carlos-pc on 28/10/15.
 */
public class RecyclerViewDivider extends RecyclerView.ItemDecoration  {

    public RecyclerViewDivider(Context context){
        super();
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left=0;
        outRect.top=10;
        outRect.bottom=10;
        outRect.left=0;
    }
}
