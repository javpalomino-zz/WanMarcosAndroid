package wan.wanmarcos.views.adapters.ViewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import wan.wanmarcos.R;
import wan.wanmarcos.models.Valuation;

/**
 * Created by soporte on 28/11/15.
 */
public class ValuationHolder extends CustomViewHolder<Valuation>  {
    private Valuation valuation;
    private View view;
    private ImageView userImage;
    private RatingBar userRate;
    private TextView userValuation;
    private TextView userName;

    public ValuationHolder(View itemView) {
        super(itemView);
        userName=(TextView) itemView.findViewById(R.id.valuation_user_name);
        userImage=(ImageView) itemView.findViewById(R.id.userImage);
        userValuation=(TextView) itemView.findViewById(R.id.userComments);
        view=itemView;
    }

    @Override
    public void setElements(Valuation object) {
        valuation=object;
        userName.setText(object.getUserName());
        userValuation.setText(object.getUserComment());
        ColorGenerator generator = ColorGenerator.MATERIAL;
        int color = generator.getColor(userName.getText().charAt(0));
        TextDrawable.IBuilder builder = TextDrawable.builder().round();
        TextDrawable textDrawable = builder.build(userName.getText().toString().charAt(0)+"", color);
        userImage.setImageDrawable(textDrawable);
    }

    @Override
    public void onClick(View v) {

    }
}
