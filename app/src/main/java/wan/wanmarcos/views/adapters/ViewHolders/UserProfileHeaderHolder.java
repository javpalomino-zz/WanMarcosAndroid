package wan.wanmarcos.views.adapters.ViewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import java.sql.SQLOutput;

import retrofit.Call;
import retrofit.Response;
import wan.wanmarcos.R;
import wan.wanmarcos.models.UserProfile;
import wan.wanmarcos.utils.Builder;
import wan.wanmarcos.utils.RestClient;
import wan.wanmarcos.views.widgets.CircleTransform;

/**
 * Created by Francisco on 1/12/2015.
 */
public class UserProfileHeaderHolder extends CustomHeaderViewHolder {
    private TextView userName;
    private TextView userEmail;
    private TextView userFaculty;
    private TextView userCarreer;
    private ImageView userImage;
    private ImageView userBackground;
    private UserProfile userProfile;
    private RestClient restClient;
    private Builder builder;
    private View view;
    private boolean gotCorrectResponse;

    String token="Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI1IiwiaXNzIjoiaHR0cDpcL1wvNTIuODkuMTI0LjBcL2FwaVwvdjFcL2F1dGhlbnRpY2F0ZSIsImlhdCI6IjE0NDcxMDQ5MzQiLCJleHAiOiIxNDU1NzQ0OTM0IiwibmJmIjoiMTQ0NzEwNDkzNCIsImp0aSI6IjcxZjM2NjgwN2EwZTIyZTY1ODM0OWYzZDMyOTcxNDQ1In0.gQK_MjKSRx6BhVCsy0CyhvJTEZB-wK2EWvKKJrDpUm4";


    public UserProfileHeaderHolder(View itemView) {
        super(itemView);
        System.out.println("ENTRO A CONSTRUCTOR");
        userName= (TextView) itemView.findViewById(R.id.profile_name);
        userEmail= (TextView) itemView.findViewById(R.id.profile_email);
        userFaculty= (TextView) itemView.findViewById(R.id.profile_faculty);
        userCarreer= (TextView) itemView.findViewById(R.id.profile_carreer);
        userImage= (ImageView) itemView.findViewById(R.id.profile_image);
        userBackground= (ImageView) itemView.findViewById(R.id.user_profile_backgound);
        restClient=new RestClient();
        builder=new Builder();
        gotCorrectResponse=false;
        view=itemView;
        setElements();
    }

    @Override
    public void setElements() {

        Call<JsonElement> userInfo=restClient.getConsumerService().me(token);
        userInfo.enqueue(new retrofit.Callback<JsonElement>() {
            @Override
            public void onResponse(Response<JsonElement> response) {
                System.out.println("Entro al ONresponse");
                JsonObject jsonObject = response.body().getAsJsonObject();
                if (jsonObject.has("email")) {

                    System.out.println("YAY");
                    userName.setText(jsonObject.get("first_name").getAsString() + jsonObject.get("last_name").getAsString());
                    userEmail.setText(jsonObject.get("email").getAsString());
                    userCarreer.setText(" ");
                    userFaculty.setText(" ");
                    System.out.println("ALOWING BACKGROUND");
                    Picasso.with(view.getContext()).load(R.mipmap.backgroundprofile).fit().centerCrop().into(userBackground);
                    ColorGenerator generator = ColorGenerator.MATERIAL;
                    int color = generator.getColor(userName.getText().charAt(0));
                    TextDrawable.IBuilder builder = TextDrawable.builder().round();
                    TextDrawable textDrawable = builder.build(userName.getText().toString().charAt(0)+"", color);
                    userImage.setImageDrawable(textDrawable);

                } else {
                    if (jsonObject.has("error")) {
                        System.out.println("Nope");
                        wan.wanmarcos.models.Error error = builder.buildError(jsonObject.get("error").getAsJsonObject());
                        System.out.println(error.toString());
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
    public void addListeners(){
    }
}
