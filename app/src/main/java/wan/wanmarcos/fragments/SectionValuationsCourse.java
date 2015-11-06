package wan.wanmarcos.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
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
import wan.wanmarcos.models.Valuation;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.views.adapters.ValuationListAdapter;


public class SectionValuationsCourse extends Fragment implements ItemAdapterListener<Valuation> {
    private RecyclerView recyclerView;
    private ValuationListAdapter valuationListAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //courseListAdapter=new CourseListAdapter(getActivity(), Constants.COURSE_NEW_ITEM);
        //courseListAdapter.add(new Course("Matematica",(float)4.0,"FISI"));
        //courseListAdapter.add(new Course("Letras",(float)3.4,"FIEE"));
        //courseListAdapter.add(new Course("Humanidades",(float)3.5,"FLCHs"));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout=inflater.inflate(Constants.SECTION_GENERIC_LIST_LAYOUT,container,false);
        setUpElements(layout);
        addListeners();
        return layout;
    }

    private void addListeners() {
    }

    public void setUpElements(View view){
        recyclerView=(RecyclerView) view.findViewById(R.id.generic_listView);
        valuationListAdapter=new ValuationListAdapter(getActivity(),getData());
        valuationListAdapter.setListener(this);
        recyclerView.setAdapter(valuationListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void itemClicked(View view, Valuation object) {
        //TODO:implementar
    }

    @Override
    public List<Valuation> getData() {
        List<Valuation> valuations=new ArrayList();
        valuations.add(new Valuation("Carlos","http://lorempixel.com/350/230/","El curso es muy aburrido",(float)1.0));
        valuations.add(new Valuation("Miguel","http://lorempixel.com/350/230/","El curso paltea",(float)1.0));
        valuations.add(new Valuation("Juan","http://lorempixel.com/350/230/","Panxooooooooooo",(float)3.0));
        valuations.add(new Valuation("Parce","http://lorempixel.com/350/230/","Metemos la six",(float)2.5));
        valuations.add(new Valuation("Javier","http://lorempixel.com/350/230/","todo aburre",(float)1.0));
        valuations.add(new Valuation("Abigail","http://lorempixel.com/350/230/","El curso ",(float)2.0));
        valuations.add(new Valuation("Lel","http://lorempixel.com/350/230/","El paltea",(float)1.4));


        return valuations;
    }
}
