package dev.thec0dec8ter.tmdb.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import dev.thec0dec8ter.tmdb.BuildConfig;
import dev.thec0dec8ter.tmdb.R;
import dev.thec0dec8ter.tmdb.adapters.GenreAdapter;
import dev.thec0dec8ter.tmdb.adapters.TvAdapter;
import dev.thec0dec8ter.tmdb.network.RetrofitClientInstance;
import dev.thec0dec8ter.tmdb.network.TvResponse;
import dev.thec0dec8ter.tmdb.network.TvService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TvShowsFragment extends Fragment {


    private Call<TvResponse> call;

    private CardView seeAllTopRated;
    private RecyclerView topRatedRecycler;
    private CardView seeAllGenres;
    private RecyclerView genreRecycler;
    private CardView seeAllPopular;
    private RecyclerView popularTvShowsRecycler;

    private TvAdapter topRatedAdapter;
    private GenreAdapter genreAdapter;
    private TvAdapter popularTvShowsAdapter;

    private TvService tvService;


    public TvShowsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tvService = RetrofitClientInstance.getRetrofitInstance().create(TvService.class);
        topRatedAdapter = new TvAdapter();
        genreAdapter = new GenreAdapter();
        popularTvShowsAdapter = new TvAdapter();

        getTopRatedTvShows("1");
        getTvGenres();
        getPopularTvShows("1");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tv_shows, container, false);
        seeAllTopRated = view.findViewById(R.id.see_all_top_rated);
        topRatedRecycler = view.findViewById(R.id.top_rated_recycler);
        seeAllGenres = view.findViewById(R.id.see_all_genres);
        genreRecycler = view.findViewById(R.id.genre_recycler);
        seeAllPopular = view.findViewById(R.id.see_all_popular);
        popularTvShowsRecycler = view.findViewById(R.id.popular_tv_recycler);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        topRatedRecycler.setAdapter(topRatedAdapter);
        genreRecycler.setAdapter(genreAdapter);
        popularTvShowsRecycler.setAdapter(popularTvShowsAdapter);

    }

    private void getTvAiringToday(String page){
        call = tvService.getTvShowAiringToday(BuildConfig.KEY,page);
        call.enqueue(new Callback<TvResponse>() {
            @Override
            public void onResponse(Call<TvResponse> call, Response<TvResponse> response) {
                topRatedAdapter.addTvShows(response.body().getResults());
            }

            @Override
            public void onFailure(Call<TvResponse> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getTopRatedTvShows(String page){
        call = tvService.getTopRatedTvShows(BuildConfig.KEY,page);
        call.enqueue(new Callback<TvResponse>() {
            @Override
            public void onResponse(Call<TvResponse> call, Response<TvResponse> response) {
                topRatedAdapter.addTvShows(response.body().getResults());
            }

            @Override
            public void onFailure(Call<TvResponse> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getTvGenres(){
        call = tvService.getTvShowGenres(BuildConfig.KEY);
        call.enqueue(new Callback<TvResponse>() {
            @Override
            public void onResponse(Call<TvResponse> call, Response<TvResponse> response) {
                for (TvResponse tvResponse:response.body().getGenres()){
                    genreAdapter.addGenre(tvResponse.getId(),tvResponse.getName());
                }
            }

            @Override
            public void onFailure(Call<TvResponse> call, Throwable t) {

            }
        });
    }

    private void getPopularTvShows(String page){
        call = tvService.getPopularTvShows(BuildConfig.KEY, page);
        call.enqueue(new Callback<TvResponse>() {
            @Override
            public void onResponse(Call<TvResponse> call, Response<TvResponse> response) {
                popularTvShowsAdapter.addTvShows(response.body().getResults());
            }

            @Override
            public void onFailure(Call<TvResponse> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}