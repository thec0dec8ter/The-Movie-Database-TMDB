package dev.thec0dec8ter.tmdb.ui.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;

import dev.thec0dec8ter.tmdb.R;
import dev.thec0dec8ter.tmdb.adapters.KeywordAdapter;

public class FindFragment extends Fragment {

    private TextView txtMovie;
    private TextView txtTvShow;
    private TextView txtCeleb;


    private RecyclerView runtimeRecycler;
    private RecyclerView tmdbRatingRecycler;
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
        languageAdapter = new KeywordAdapter(true,Arrays.asList(getResources().getStringArray(R.array.languages)));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find, container, false);
        txtMovie = view.findViewById(R.id.txt_movie);
        txtTvShow = view.findViewById(R.id.txt_tv_show);
        txtCeleb = view.findViewById(R.id.txt_celeb);
        runtimeRecycler = view.findViewById(R.id.runtime_recycler);
        tmdbRatingRecycler = view.findViewById(R.id.tmdb_rating_recycler);
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
        tmdbRatingRecycler.setAdapter(ratingAdapter);
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
                    disableAdapters();
                }else {
                    removeTextBackground(txtCeleb);
                    enableAdapters();
                }

            }
        });
    }

    private void changeTextBackground(TextView textView){
        textView.setTextColor(getResources().getColorStateList(R.color.light_green, null));
        textView.setBackgroundTintList(getResources().getColorStateList(R.color.dark_blue, null));
    }

    private void removeTextBackground(TextView textView){
        textView.setTextColor(getResources().getColorStateList(R.color.black, null));
        textView.setBackground(getResources().getDrawable(R.drawable.curved_corners,null));
        textView.setBackgroundTintList(null);
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

}