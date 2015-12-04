package wan.wanmarcos.views.adapters.ViewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.squareup.picasso.Picasso;

import wan.wanmarcos.R;
import wan.wanmarcos.models.Valuation;
import wan.wanmarcos.views.widgets.CircleTransform;

/**
 * Created by soporte on 28/11/15.
 */
public class ValuationHolder extends CustomViewHolder<Valuation>  {
    private Valuation valuation;
    private View view;
    private ImageView userImage;
    private TextView userValuation;
    private TextView userName;
    private TextView userMark;

    public ValuationHolder(View itemView) {
        super(itemView);
        userName=(TextView) itemView.findViewById(R.id.valuation_user_name);
        userImage=(ImageView) itemView.findViewById(R.id.userImage);
        userMark=(TextView)itemView.findViewById(R.id.profile_teacher_rating);
        userValuation=(TextView) itemView.findViewById(R.id.userComments);
    }

    @Override
    public void setElements(Valuation object) {
        valuation=object;
        userName.setText(object.getUserName());
        userValuation.setText(object.getUserComment());
        userMark.setText(object.getUserTotalMark() + "");
        if(object.getUserImage()!=null)
        {
            Picasso.with(view.getContext()).load(object.getUserImage()).transform(new CircleTransform()).into(userImage);
        }
        else
        {
            ColorGenerator generator = ColorGenerator.MATERIAL;
            int color = generator.getColor(userName.getText().charAt(0));
            TextDrawable.IBuilder builder = TextDrawable.builder().round();
            TextDrawable textDrawable = builder.build(userName.getText().toString().charAt(0)+"", color);
            userImage.setImageDrawable(textDrawable);
        }
    }

    @Override
    public void onClick(View v) {

    }
}
