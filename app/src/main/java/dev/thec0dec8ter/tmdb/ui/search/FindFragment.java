package dev.thec0dec8ter.tmdb.ui.search;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.net.Uri;
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

import java.util.ArrayList;

import dev.thec0dec8ter.tmdb.R;
import dev.thec0dec8ter.tmdb.adapters.KeywordAdapter;
import dev.thec0dec8ter.tmdb.adapters.SearchPagerAdapter;

import static dev.thec0dec8ter.tmdb.BuildConfig.KEY;

public class FindFragment extends Fragment {

    private TextView txtMovie;
    private TextView txtTvShow;
    private TextView btnClear;
    private Button btnSeeResult;

    private RecyclerView runtimeRecycler;
    private RecyclerView ratingRecycler;
    private RecyclerView genreRecycler;
    private RecyclerView yearRecycler;
    private RecyclerView voteRecycler;
    private RecyclerView languageRecycler;

    private KeywordAdapter runtimeAdapter;
    private KeywordAdapter ratingAdapter;
    private KeywordAdapter genreAdapter;
    private KeywordAdapter yearAdapter;
    private KeywordAdapter voteAdapter;
    private KeywordAdapter languageAdapter;

    public FindFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Resources res = getResources();
        runtimeAdapter = new KeywordAdapter(
                res.getStringArray(R.array.runtime),
                res.getStringArray(R.array.runtime_tag),
                "with_runtime.gte");
        ratingAdapter = new KeywordAdapter(
                res.getStringArray(R.array.tmdb_rating),
                res.getStringArray(R.array.tmdb_rating_tag),
                "vote_average.gte");
        genreAdapter = new KeywordAdapter(
                res.getStringArray(R.array.popular_genres),
                res.getStringArray(R.array.runtime_tag),
                "with_genres");
        yearAdapter = new KeywordAdapter(
                res.getStringArray(R.array.release_year),
                res.getStringArray(R.array.release_year),
                "primary_release_year");
        voteAdapter = new KeywordAdapter(
                res.getStringArray(R.array.total_votes),
                res.getStringArray(R.array.runtime_tag),"vote_count.gte");
        languageAdapter = new KeywordAdapter(
                res.getStringArray(R.array.language),
                res.getStringArray(R.array.runtime_tag),
                "language");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find, container, false);
        txtMovie = view.findViewById(R.id.txt_movie);
        txtTvShow = view.findViewById(R.id.txt_tvshow);
        btnClear = view.findViewById(R.id.btn_clear);
        btnSeeResult = view.findViewById(R.id.btn_see_result);
        runtimeRecycler = view.findViewById(R.id.runtime_recycler);
        ratingRecycler = view.findViewById(R.id.tmdb_rating_recycler);
        genreRecycler = view.findViewById(R.id.genre_recycler);
        yearRecycler = view.findViewById(R.id.year_recycler);
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
        yearRecycler.setAdapter(yearAdapter);
        voteRecycler.setAdapter(voteAdapter);
        languageRecycler.setAdapter(languageAdapter);


        txtMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int color = v.getContext().getColor(R.color.dark_blue);
                if(txtMovie.getBackgroundTintList() != ColorStateList.valueOf(color)){
                    KeywordAdapter.changeTextBackground(txtMovie);
                    KeywordAdapter.removeTextBackground(txtTvShow);
                    yearAdapter.setTag("primary_release_year");
                }else {
                    KeywordAdapter.removeTextBackground(txtMovie);
                }
            }
        });

        txtTvShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int color = v.getContext().getColor(R.color.dark_blue);
                if(txtTvShow.getBackgroundTintList() != ColorStateList.valueOf(color)){
                    KeywordAdapter.changeTextBackground(txtTvShow);
                    KeywordAdapter.removeTextBackground(txtMovie);
                    yearAdapter.setTag("first_air_date_year");
                }else {
                    KeywordAdapter.removeTextBackground(txtTvShow);
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
                if(getQuery() != null){
                    Bundle args = new Bundle();
                    args.putString("discover", getQuery());

                    ViewPager2 viewPager = getActivity().findViewById(R.id.view_pager);
                    SearchPagerAdapter searchPagerAdapter = (SearchPagerAdapter) viewPager.getAdapter();
                    viewPager.setCurrentItem(0);
                    searchPagerAdapter.getCurrentFragment(0).setArguments(args);
                    searchPagerAdapter.getCurrentFragment(0).onResume();
                }
            }
        });
    }

    private void clearSelection(RecyclerView recycler){
        //TODO: clear all selections made
    }


    private void clearAll(){
        clearSelection(runtimeRecycler);
        clearSelection(ratingRecycler);
        clearSelection(genreRecycler);
        clearSelection(yearRecycler);
        clearSelection(voteRecycler);
        clearSelection(languageRecycler);
    }

    private String getQuery(){
        String baseUrl = "";
        int color = getContext().getColor(R.color.dark_blue);
        if(txtTvShow.getBackgroundTintList() == ColorStateList.valueOf(color)){
            baseUrl = "https://api.themoviedb.org/3/discover/tv";
        }else if(txtMovie.getBackgroundTintList() == ColorStateList.valueOf(color)) {
            baseUrl = "https://api.themoviedb.org/3/discover/movie";
        }else {
            return null;
        }
        Uri.Builder builder = Uri.parse(baseUrl).buildUpon();
        builder.appendQueryParameter("api_key", KEY);
        for (KeywordAdapter adapter: getAdapters()) {
            if(adapter.getQueryParameter() != null) {
                String[] map = adapter.getQueryParameter();
                builder.appendQueryParameter(map[0], map[1]);
            }
        }
        Uri builtUri = builder.build();
        return builtUri.toString();

    }

    public ArrayList<KeywordAdapter> getAdapters() {
        ArrayList<KeywordAdapter> adapters = new ArrayList<>();
        adapters.add(runtimeAdapter);
        adapters.add(ratingAdapter);
        adapters.add(genreAdapter);
        adapters.add(yearAdapter);
        adapters.add(voteAdapter);
        adapters.add(languageAdapter);
        return adapters;
    }
}





//    <item>en - US</item>
//        <item>fr - CA</item>
//        <item>ge - DE</item>
//        <item>fr - FR</item>
//        <item>ma - NZ</item>
//        <item>en - AU</item>
//        <item>hi - IN</item>

