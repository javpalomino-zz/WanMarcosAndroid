package wan.wanmarcos.views.adapters;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import wan.wanmarcos.R;
import wan.wanmarcos.managers.ItemAdapterListener;
import wan.wanmarcos.managers.ViewHolderSetters;
import wan.wanmarcos.models.Valuation;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.utils.Redirection.Redirect;
import wan.wanmarcos.utils.Storage;
import wan.wanmarcos.views.widgets.CircleTransform;

/**
 * Created by postgrado on 17/10/15.
 */
public class ValuationListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final int TYPE_ITEM =1 ;
    private static final int TYPE_HEADER = 0;
    private LayoutInflater inflater;
    private Fragment mFragment;
    private List<Valuation> valuations= Collections.emptyList();
    private ItemAdapterListener itemAdapterListener;
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==TYPE_ITEM){
            View view=inflater.inflate(Constants.VALUATION_NEW_ITEM,parent,false);
            ValuationHolder courseHolder=new ValuationHolder(view);
            return courseHolder;
        }
        else if(viewType==TYPE_HEADER){
            View view=inflater.inflate(Constants.DETAIL_COURSE_TEACHER,parent,false);
            ValuationHeaderHolder courseHeaderHolder=new ValuationHeaderHolder(view);
            return courseHeaderHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ValuationHolder) {
            Valuation valuation=valuations.get(position-1);
            ((ValuationHolder)holder).setElements(valuation);
        } else if (holder instanceof ValuationHeaderHolder) {
            ((ValuationHeaderHolder)holder).setElements();
        }
    }

    public ValuationListAdapter(Fragment myFragment,Context context,List<Valuation> valuations){
        inflater=LayoutInflater.from(context);
        this.valuations=valuations;
        mFragment=myFragment;

    }
    @Override
    public int getItemViewType(int position) {
        if (!isPositionHeader(position))
            return TYPE_ITEM;

        return TYPE_HEADER;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    @Override
    public int getItemCount() {
        return valuations.size()+1;
    }

    public void setListener(ItemAdapterListener listener) {
        itemAdapterListener= listener;
    }
    public class ValuationHeaderHolder extends RecyclerView.ViewHolder {
        private TextView teacherName;
        private TextView courseName;
        private TextView facultyName;
        private ImageView teacherImage;
        private ImageView teacherCardBackground;
        private FloatingActionButton floatingActionButton;
        public ValuationHeaderHolder(View itemView) {
            super(itemView);
            teacherCardBackground= (ImageView) itemView.findViewById(R.id.teacher_course_card_background);
            teacherName=(TextView)itemView.findViewById(R.id.profile_course_teacher_name);
            courseName=(TextView)itemView.findViewById(R.id.profile_course_course_name);
            facultyName=(TextView)itemView.findViewById(R.id.profile_course_faculty_name);
            teacherImage=(ImageView)itemView.findViewById(R.id.profile_course_teacher_image);
            floatingActionButton=(FloatingActionButton)itemView.findViewById(R.id.addComment);
        }

        public void setElements() {
            Picasso.with(mFragment.getActivity()).load("https://newevolutiondesigns.com/images/freebies/google-material-design-wallpaper-17.jpg").fit().centerCrop().into(teacherCardBackground);
            Picasso.with(itemView.getContext()).load(Storage.getSingelton().getInfo(mFragment, Storage.KEY_TEACHER_IMAGE)).transform(new CircleTransform()).into(teacherImage);
            facultyName.setText(Storage.getSingelton().getInfo(mFragment, Storage.KEY_FACULTY_NAME));
            teacherName.setText(Storage.getSingelton().getInfo(mFragment,Storage.KEY_TEACHER_NAME));
            courseName.setText(Storage.getSingelton().getInfo(mFragment,Storage.KEY_COURSE_NAME));
            addListeners();
        }
        public void addListeners(){
            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("d","d");
                    itemAdapterListener.addClicked(Constants.FRAGMENT_PROFILE_TEACHER);

                }
            });
        }
    }

    public class ValuationHolder extends RecyclerView.ViewHolder implements ViewHolderSetters<Valuation>,View.OnClickListener {
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
            //userRate=(RatingBar) itemView.findViewById(R.id.userRate);
            view=itemView;
        }

        @Override
        public void onClick(View v) {
            itemAdapterListener.itemClicked(v,getAdapterPosition());
        }

        @Override
        public void setElements(Valuation elements) {
            valuation=elements;

            userName.setText(elements.getUserName());
            userValuation.setText(elements.getUserComment());
            ColorGenerator generator = ColorGenerator.MATERIAL;
            int color = generator.getColor(userName.getText().charAt(0));
            TextDrawable.IBuilder builder = TextDrawable.builder().round();
            TextDrawable textDrawable = builder.build(userName.getText().toString().charAt(0)+"", color);
            userImage.setImageDrawable(textDrawable);
            //userRate.setRating(elements.getUserTotalMark());
            //Picasso.with(view.getContext()).load(elements.getUserImage()).into(userImage);
        }
    }
}