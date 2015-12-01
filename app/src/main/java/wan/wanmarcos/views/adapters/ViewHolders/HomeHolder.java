package wan.wanmarcos.views.adapters.ViewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import wan.wanmarcos.R;
import wan.wanmarcos.models.Home;

/**
 * Created by carlos-pc on 29/11/15.
 */
public class HomeHolder extends CustomViewHolder<Home>{
    private TextView referenceName;
    private ImageView referenceImage;
    private TextView referenceTypeDescription;
    private TextView referenceDescription;

    public HomeHolder(View itemView) {
        super(itemView);
        referenceImage=(ImageView)itemView.findViewById(R.id.teacher_item_picture);
        referenceName=(TextView)itemView.findViewById(R.id.home_item_name);
        referenceTypeDescription=(TextView)itemView.findViewById(R.id.home_item_description_label);
        referenceDescription=(TextView)itemView.findViewById(R.id.home_item_description);
    }

    @Override
    public void setElements(Home object) {
        Picasso.with(itemView.getContext()).load("http://lorempixel.com/g/400/200/").into(referenceImage);
        referenceName.setText("O thi");
        referenceTypeDescription.setText("Teacher");
        referenceDescription.setText("O no se que hacer");
    }

    @Override
    public void onClick(View v) {
        //recyclerViewClickListener.recyclerViewListClicked();

    }
}