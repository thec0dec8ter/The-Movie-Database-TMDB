package dev.thec0dec8ter.tmdb.ui.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;

import dev.thec0dec8ter.tmdb.R;
import dev.thec0dec8ter.tmdb.adapters.KeywordAdapter;

public class FindTrendingFragment extends Fragment {

    private RecyclerView tmdbRatingRecycler;
    private RecyclerView genreRecycler;
    private RecyclerView decadeRecycler;
    private RecyclerView keywordRecycler;
    private RecyclerView voteRecycler;
    private RecyclerView languageRecycler;

    private KeywordAdapter ratingAdapter;
    private KeywordAdapter genreAdapter;
    private KeywordAdapter decadeAdapter;
    private KeywordAdapter keywordAdapter;
    private KeywordAdapter voteAdapter;
    private KeywordAdapter languageAdapter;

    public FindTrendingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ratingAdapter = new KeywordAdapter(Arrays.asList(getResources().getStringArray(R.array.tmdb_rating)));
        genreAdapter = new KeywordAdapter(Arrays.asList(getResources().getStringArray(R.array.genres)));
        decadeAdapter = new KeywordAdapter(Arrays.asList(getResources().getStringArray(R.array.decades)));
        voteAdapter = new KeywordAdapter(Arrays.asList(getResources().getStringArray(R.array.total_votes)));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find_trending, container, false);
        tmdbRatingRecycler = view.findViewById(R.id.tmdb_rating_recycler);
        genreRecycler = view.findViewById(R.id.genre_recycler);
        decadeRecycler = view.findViewById(R.id.decade_recycler);
        keywordRecycler = view.findViewById(R.id.keyword_recycler);
        voteRecycler = view.findViewById(R.id.vote_recycler);
        languageRecycler = view.findViewById(R.id.language_recycler);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tmdbRatingRecycler.setAdapter(ratingAdapter);
        genreRecycler.setAdapter(genreAdapter);
        decadeRecycler.setAdapter(decadeAdapter);
        keywordRecycler.setAdapter(decadeAdapter);
        voteRecycler.setAdapter(voteAdapter);
        languageRecycler.setAdapter(decadeAdapter);
    }
}