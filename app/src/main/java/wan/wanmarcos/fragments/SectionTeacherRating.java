package wan.wanmarcos.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import wan.wanmarcos.R;
import wan.wanmarcos.models.Rating;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.views.adapters.RatingListAdapter;


public class SectionTeacherRating extends Fragment {
    private RatingListAdapter ratingListAdapter;
    private ListView listView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ratingListAdapter=new RatingListAdapter(getActivity(), Constants.RATING_NEW_ITEM);
        ratingListAdapter.add(new Rating((float)4.0,"Desempeño"));
        ratingListAdapter.add(new Rating((float)3.0,"Entusiasmo"));
        ratingListAdapter.add(new Rating((float)2.5,"Valor"));
        ratingListAdapter.add(new Rating((float)1.4,"Pedagogia"));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(Constants.SECTION_LIST,container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView=(ListView)view.findViewById(R.id.generic_listView);
        listView.setAdapter(ratingListAdapter);
    }
}
