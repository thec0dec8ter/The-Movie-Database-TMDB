package dev.thec0dec8ter.tmdb.ui.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.util.Arrays;

import dev.thec0dec8ter.tmdb.R;
import dev.thec0dec8ter.tmdb.adapters.KeywordAdapter;

public class FindFragment extends Fragment {

    private TextView txtMovie;
    private TextView txtTvShow;
    private TextView txtCeleb;

    private Button btnClear;
    private Button btnSeeResult;

    private RecyclerView runtimeRecycler;
    private RecyclerView ratingRecycler;
    private RecyclerView genreRecycler;
    private RecyclerView decadeRecycler;
    private RecyclerView voteRecycler;
    private RecyclerView languageRecycler;

    private KeywordAdapter runtimeAdapter;
    private KeywordAdapter ratingAdapter;
    private KeywordAdapter genreAdapter;
    private KeywordAdapter decadeAdapter;
    private KeywordAdapter voteAdapter;
    private KeywordAdapter languageAdapter;

    public FindFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        runtimeAdapter = new KeywordAdapter(true,Arrays.asList(getResources().getStringArray(R.array.runtime)));
        ratingAdapter = new KeywordAdapter(true,Arrays.asList(getResources().getStringArray(R.array.tmdb_rating)));
        genreAdapter = new KeywordAdapter(true,Arrays.asList(getResources().getStringArray(R.array.genres)));
        decadeAdapter = new KeywordAdapter(true,Arrays.asList(getResources().getStringArray(R.array.decades)));
        voteAdapter = new KeywordAdapter(true,Arrays.asList(getResources().getStringArray(R.array.total_votes)));
        languageAdapter = new KeywordAdapter(true,Arrays.asList(getResources().getStringArray(R.array.country)));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find, container, false);
        txtMovie = view.findViewById(R.id.txt_movie);
        txtTvShow = view.findViewById(R.id.txt_tv_show);
        txtCeleb = view.findViewById(R.id.txt_celeb);
        btnClear = view.findViewById(R.id.btn_clear);
        btnSeeResult = view.findViewById(R.id.btn_see_result);
        runtimeRecycler = view.findViewById(R.id.runtime_recycler);
        ratingRecycler = view.findViewById(R.id.tmdb_rating_recycler);
        genreRecycler = view.findViewById(R.id.genre_recycler);
        decadeRecycler = view.findViewById(R.id.decade_recycler);
        voteRecycler = view.findViewById(R.id.vote_recycler);
        languageRecycler = view.findViewById(R.id.language_recycler);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        runtimeRecycler.setAdapter(runtimeAdapter);
        ratingRecycler.setAdapter(ratingAdapter);
        genreRecycler.setAdapter(genreAdapter);
        decadeRecycler.setAdapter(decadeAdapter);
        voteRecycler.setAdapter(voteAdapter);
        languageRecycler.setAdapter(languageAdapter);

        txtMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtMovie.getCurrentTextColor() != getResources().getColor(R.color.light_green, null)){
                    changeTextBackground(txtMovie);
                    removeTextBackground(txtTvShow);
                    removeTextBackground(txtCeleb);
                    enableAdapters();
                }else {
                    removeTextBackground(txtMovie);
                }
            }
        });

        txtTvShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtTvShow.getCurrentTextColor() != getResources().getColor(R.color.light_green, null)){
                    changeTextBackground(txtTvShow);
                    removeTextBackground(txtMovie);
                    removeTextBackground(txtCeleb);
                    enableAdapters();
                }else {
                    removeTextBackground(txtTvShow);
                }
            }
        });

        txtCeleb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtCeleb.getCurrentTextColor() != getResources().getColor(R.color.light_green, null)){
                    changeTextBackground(txtCeleb);
                    removeTextBackground(txtTvShow);
                    removeTextBackground(txtMovie);
                    clearAll();
                    disableAdapters();
                }else {
                    removeTextBackground(txtCeleb);
                    enableAdapters();
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAll();
            }
        });

        btnSeeResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ViewPager2) getActivity().findViewById(R.id.view_pager)).setCurrentItem(0);
            }
        });
    }

    private void changeTextBackground(View view){
        ((TextView)view).setTextColor(getResources().getColorStateList(R.color.light_green, null));
        view.setBackgroundTintList(getResources().getColorStateList(R.color.dark_blue, null));
    }

    private void removeTextBackground(View view){
        ((TextView)view).setTextColor(getResources().getColorStateList(R.color.black, null));
        view.setBackground(getResources().getDrawable(R.drawable.curved_corners,null));
        view.setBackgroundTintList(null);
    }

    private void enableAdapters(){
        runtimeAdapter.setClickable(true);
        ratingAdapter.setClickable(true);
        genreAdapter.setClickable(true);
        decadeAdapter.setClickable(true);
        voteAdapter.setClickable(true);
        languageAdapter.setClickable(true);
    }

    private void disableAdapters(){
        runtimeAdapter.setClickable(false);
        ratingAdapter.setClickable(false);
        genreAdapter.setClickable(false);
        decadeAdapter.setClickable(false);
        voteAdapter.setClickable(false);
        languageAdapter.setClickable(false);
    }

    private void clearSelection(RecyclerView recycler, KeywordAdapter adapter){
        for (int i = 0; i < adapter.getItemCount(); i++){
            removeTextBackground(recycler.getLayoutManager().findViewByPosition(i));
        }
    }

    private void clearAll(){
        clearSelection(runtimeRecycler,runtimeAdapter);
        clearSelection(ratingRecycler,ratingAdapter);
        clearSelection(genreRecycler,genreAdapter);
        clearSelection(decadeRecycler,decadeAdapter);
        clearSelection(voteRecycler,voteAdapter);
        clearSelection(languageRecycler,languageAdapter);
    }



//    <item>en - US</item>
//        <item>fr - CA</item>
//        <item>ge - DE</item>
//        <item>fr - FR</item>
//        <item>ma - NZ</item>
//        <item>en - AU</item>
//        <item>hi - IN</item>

}