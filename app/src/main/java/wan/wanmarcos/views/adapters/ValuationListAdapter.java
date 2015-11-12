package wan.wanmarcos.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import wan.wanmarcos.R;
import wan.wanmarcos.managers.ItemAdapterListener;
import wan.wanmarcos.managers.ViewHolderSetters;
import wan.wanmarcos.models.Valuation;
import wan.wanmarcos.utils.Constants;

/**
 * Created by postgrado on 17/10/15.
 */
public class ValuationListAdapter extends RecyclerView.Adapter<ValuationListAdapter.ValuationHolder>{

    private LayoutInflater inflater;
    private List<Valuation> valuations= Collections.emptyList();
    private ItemAdapterListener itemAdapterListener;
    @Override
    public ValuationHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view=inflater.inflate(Constants.VALUATION_NEW_ITEM, parent, false);
        ValuationHolder valuationHolder= new ValuationHolder(view);
        return valuationHolder;
    }

    public ValuationListAdapter(Context context,List<Valuation> valuations){
        inflater=LayoutInflater.from(context);
        this.valuations=valuations;

    }
    @Override
    public void onBindViewHolder(ValuationHolder holder, int position) {
        Valuation valuation=valuations.get(position);
        holder.setElements(valuation);
    }

    @Override
    public int getItemCount() {
        return valuations.size();
    }

    public void setListener(ItemAdapterListener listener) {
        itemAdapterListener= listener;
    }

    public class ValuationHolder extends RecyclerView.ViewHolder implements ViewHolderSetters<Valuation>,View.OnClickListener {
        private Valuation valuation;
        private View view;
        private ImageView userImage;
        private RatingBar userRate;
        private TextView userValuation;
        public ValuationHolder(View itemView) {
            super(itemView);
            userImage=(ImageView) itemView.findViewById(R.id.userImage);
            userValuation=(TextView) itemView.findViewById(R.id.userComments);
            userRate=(RatingBar) itemView.findViewById(R.id.userRate);
            view=itemView;
        }

        @Override
        public void onClick(View v) {
            itemAdapterListener.itemClicked(v,getAdapterPosition());
        }

        @Override
        public void setElements(Valuation elements) {
            valuation=elements;
            userValuation.setText(elements.getUserComment());
            userRate.setRating(elements.getUserTotalMark());
            Picasso.with(view.getContext()).load(elements.getUserImage()).into(userImage);
        }
    }
}/*
public class ValuationListAdapter extends CustomListAdapter<Valuation> {
    public ValuationListAdapter(Context context, int resourceID, List<Valuation> listObjects) {
        super(context, resourceID, listObjects);
    }

    @Override
    void setElements(View view, int position) {

    }
}
*/