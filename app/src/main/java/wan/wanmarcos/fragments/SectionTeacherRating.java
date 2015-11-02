package wan.wanmarcos.fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import wan.wanmarcos.R;
import wan.wanmarcos.utils.Redirection.Redirection;
import wan.wanmarcos.managers.ItemAdapterListener;
import wan.wanmarcos.models.Rating;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.views.adapters.RatingListAdapter;


public class SectionTeacherRating extends Fragment implements ItemAdapterListener {
    private RecyclerView recyclerView;
    private RatingListAdapter ratingListAdapter;

    public SectionTeacherRating(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout=inflater.inflate(Constants.SECTION_LIST,container,false);
        setUpElements(layout);
        addListeners();
        return layout;
    }

    private void addListeners() {
    }

    public void setUpElements(View view){
        recyclerView=(RecyclerView) view.findViewById(R.id.generic_listView);
        ratingListAdapter=new RatingListAdapter(getActivity(),getData());
        ratingListAdapter.setListener(this);
        recyclerView.setAdapter(ratingListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }


    @Override
    public void itemClicked(View view, Object object) {
        //TODO:implementaritemclick
    }

    @Override
    public List<Rating> getData() {
        List<Rating> ratingList=new ArrayList<>();
        ratingList.add(new Rating((float)4.0,"Desempeño"));
        ratingList.add(new Rating((float)3.0,"Entusiasmo"));
        ratingList.add(new Rating((float)2.5,"Valor"));
        ratingList.add(new Rating((float)1.4,"Pedagogia"));
        return ratingList;
    }
}
