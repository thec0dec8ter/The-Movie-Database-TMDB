package dev.thec0dec8ter.tmdb.ui.search;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

import dev.thec0dec8ter.tmdb.R;
import dev.thec0dec8ter.tmdb.adapters.SearchAdapter;
import dev.thec0dec8ter.tmdb.models.Search;
import dev.thec0dec8ter.tmdb.network.RetrofitClientInstance;
import dev.thec0dec8ter.tmdb.network.SearchService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static dev.thec0dec8ter.tmdb.BuildConfig.KEY;


public class RecentFragment extends Fragment {

    private RecyclerView searchRecycler;

    private SearchAdapter searchAdapter;

    public RecentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchAdapter = new SearchAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_recent, container, false);
        searchRecycler = root.findViewById(R.id.search_recycler);
        return root;
    }

    @Override
    public void onSaveInstanceState(@NonNull @NotNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();

        if(getArguments() != null){
            if(getArguments().getString("query") != null){
                String query = getArguments().getString("query");
                performSearch(query);
            }else if(getArguments().getSerializable("discover_movie") != null){
                discoverMovies((HashMap<String, String>) getArguments().getSerializable("discover_movie"));
            }else if(getArguments().getSerializable("discover_tv") != null){
                discoverTvShows((HashMap<String, String>) getArguments().getSerializable("discover_tv"));
            }
            searchRecycler.setAdapter(searchAdapter);
        }
    }

    public void performSearch(String query){
        SearchService searchService = RetrofitClientInstance.getRetrofitInstance().create(SearchService.class);
        Call<Search> call = searchService.multiSearch(KEY, query, "1");
        call.enqueue(new Callback<Search>() {
            @Override
            public void onResponse(Call<Search> call, Response<Search> response) {
                searchAdapter.clearResults();

                searchAdapter.addResults(response.body().getResults());
            }

            @Override
            public void onFailure(Call<Search> call, Throwable t) {

            }
        });
    }

    private void discoverMovies(HashMap<String, String> optionalParams){
        SearchService movieService = RetrofitClientInstance.getRetrofitInstance().create(SearchService.class);
        Call<Search> movieCall = movieService.discoverMovies(KEY, "1", optionalParams);
        movieCall.enqueue(new Callback<Search>() {
            @Override
            public void onResponse(Call<Search> call, Response<Search> response) {
                searchAdapter.clearResults();
                for(Search searchResult: response.body().getResults()){
                    searchResult.setMedia_type("movie");
                    searchAdapter.addResult(searchResult);
                }
            }

            @Override
            public void onFailure(Call<Search> call, Throwable t) {

            }
        });
    }

    private void discoverTvShows(HashMap<String, String> optionalParams){
        SearchService tvShowService = RetrofitClientInstance.getRetrofitInstance().create(SearchService.class);
        Call<Search> tvCall = tvShowService.discoverTvShows(KEY, "1", optionalParams);
        tvCall.enqueue(new Callback<Search>() {
            @Override
            public void onResponse(Call<Search> call, Response<Search> response) {
                searchAdapter.clearResults();
                for(Search searchResult: response.body().getResults()){
                    searchResult.setMedia_type("tv");
                    searchAdapter.addResult(searchResult);
                }
            }

            @Override
            public void onFailure(Call<Search> call, Throwable t) {

            }
        });
    }
}