package wan.wanmarcos.fragments;


import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import wan.wanmarcos.R;
import wan.wanmarcos.managers.FragmentsMethods;
import wan.wanmarcos.managers.ItemAdapterListener;
import wan.wanmarcos.models.Course;
import wan.wanmarcos.models.Rating;
import wan.wanmarcos.models.Teacher;
import wan.wanmarcos.models.Valuation;
import wan.wanmarcos.utils.Constants;
import wan.wanmarcos.utils.Redirection.Redirect;
import wan.wanmarcos.utils.Storage;
import wan.wanmarcos.views.adapters.RatingListAdapter;
import wan.wanmarcos.views.adapters.ValuationListAdapter;
import wan.wanmarcos.views.widgets.CircleTransform;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeacherCourseProfileFragment extends Fragment implements FragmentsMethods,ItemAdapterListener<Valuation> {

    private ValuationListAdapter valuationListAdapter;
    private RecyclerView recyclerViewComments;
    private FloatingActionButton floatingActionButton;

    public TeacherCourseProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view=inflater.inflate(Constants.FRAGMENT_TEACHER_COURSE_LAYOUT, container, false);
        setUpElements(view);
        addListeners();
        return view;
    }

    @Override
    public void setUpElements(View view) {
        recyclerViewComments=(RecyclerView)view.findViewById(R.id.comments_list);
       //recyclerViewRating=(RecyclerView)view.findViewById(R.id.rating_list);
        valuationListAdapter=new ValuationListAdapter(this,getActivity(),getData(""));
        valuationListAdapter.setListener(this);
        recyclerViewComments.setAdapter(valuationListAdapter);
        //recyclerViewRating.setAdapter(ratingListAdapter);
        recyclerViewComments.setLayoutManager(new LinearLayoutManager(getActivity()));
        //recyclerViewRating.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    public void addListeners(){

    }
    @Override
    public void itemClicked(View view, Valuation object) {
        //
    }

    @Override
    public void addClicked(String fragmentProfileTeacher) {
        FragmentManager fm = getFragmentManager();
        TeacherPopupComment editNameDialog = new TeacherPopupComment();
        editNameDialog.show(fm, "fragment_edit_name");
    }

    public List<Rating> getStaticData(){
        List<Rating> ratings=new ArrayList<>();
        ratings.add(new Rating((float) 4.0,"tecnica"));
        ratings.add(new Rating((float) 2.5,"salud"));
        ratings.add(new Rating((float) 1.3, "conmosion"));
        ratings.add(new Rating((float) 3.0, "desarrollo"));
        return ratings;
    }
    public List<Valuation> getData(String data) {
        List<Valuation> valuations=new ArrayList<>();
        valuations.add(new Valuation("Carlos","d","Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha sido el texto de relleno estándar de las industrias desde el año 1500, cuando un impresor (N. del T. persona que se dedica a la imprenta) desconocido usó una galería de textos y los mezcló de tal manera que logró hacer un libro de textos especimen. No sólo sobrevivió 500 años, sino que tambien ingresó como texto de relleno en documentos electrónicos, quedando esencialmente igual al original. Fue popularizado en los 60s con la creación de las hojas las cuales contenian pasajes de Lorem Ipsum, y más recientemente con software de autoedición, como por ejemplo Aldus PageMaker, el cual incluye versiones de Lorem Ipsum.",14));
        valuations.add(new Valuation("Carlos","d","Curso Boni",14));
        valuations.add(new Valuation("Carlos","d","Curso Boni",14));
        valuations.add(new Valuation("Carlos","d","Curso Boni",14));
        valuations.add(new Valuation("Carlos", "d", "Curso Boni", 14));
        valuations.add(new Valuation("Carlos", "d", "Curso Boni", 14));
        return  valuations;

    }
}
