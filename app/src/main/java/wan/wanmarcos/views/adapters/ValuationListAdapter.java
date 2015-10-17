package wan.wanmarcos.views.adapters;

import android.content.Context;
import android.view.View;

import java.util.List;

import wan.wanmarcos.models.Valuation;

/**
 * Created by postgrado on 17/10/15.
 */
public class ValuationListAdapter extends CustomListAdapter<Valuation> {
    public ValuationListAdapter(Context context, int resourceID, List<Valuation> listObjects) {
        super(context, resourceID, listObjects);
    }

    @Override
    void setElements(View view, int position) {

    }
}
