package wan.wanmarcos.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import wan.wanmarcos.R;
import wan.wanmarcos.managers.FragmentsMethods;
import wan.wanmarcos.models.Valuation;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.views.adapters.ValuationPlaceListAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlaceProfileFragment extends Fragment implements FragmentsMethods{

    private RecyclerView recyclerViewTeacherCourses;
    private ValuationPlaceListAdapter valuationPlaceListAdapter;

    public PlaceProfileFragment() {
        // Required empty public constructor

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        valuationPlaceListAdapter=new ValuationPlaceListAdapter(this);
        getData();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(Constants.FRAGMENT_PROFILE_PLACE_LAYOUT, container, false);
        setUpElements(view);
        addListeners();
        return view;
    }


    @Override
    public void setUpElements(View view) {
        recyclerViewTeacherCourses=(RecyclerView)view.findViewById(R.id.comments_list);
        recyclerViewTeacherCourses.setAdapter(valuationPlaceListAdapter);
        recyclerViewTeacherCourses.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void addListeners() {

    }

    public void getData() {
        valuationPlaceListAdapter.add(new Valuation("Carlos","d","Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha sido el texto de relleno estándar de las industrias desde el año 1500, cuando un impresor (N. del T. persona que se dedica a la imprenta) desconocido usó una galería de textos y los mezcló de tal manera que logró hacer un libro de textos especimen. No sólo sobrevivió 500 años, sino que tambien ingresó como texto de relleno en documentos electrónicos, quedando esencialmente igual al original. Fue popularizado en los 60s con la creación de las hojas las cuales contenian pasajes de Lorem Ipsum, y más recientemente con software de autoedición, como por ejemplo Aldus PageMaker, el cual incluye versiones de Lorem Ipsum.",14));
        valuationPlaceListAdapter.add(new Valuation("Carlos","d","Curso Boni",14));
        valuationPlaceListAdapter.add(new Valuation("Carlos","d","Curso Boni",14));
        valuationPlaceListAdapter.add(new Valuation("Carlos","d","Curso Boni",14));
        valuationPlaceListAdapter.add(new Valuation("Carlos", "d", "Curso Boni", 14));
        valuationPlaceListAdapter.add(new Valuation("Carlos", "d", "Curso Boni", 14));
    }
}
