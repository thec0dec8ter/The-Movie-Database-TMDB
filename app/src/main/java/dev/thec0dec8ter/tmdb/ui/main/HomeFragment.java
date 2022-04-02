package dev.thec0dec8ter.tmdb.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import dev.thec0dec8ter.tmdb.BuildConfig;
import dev.thec0dec8ter.tmdb.R;
import dev.thec0dec8ter.tmdb.adapters.GenreAdapter;
import dev.thec0dec8ter.tmdb.adapters.ShowAdapter;
import dev.thec0dec8ter.tmdb.custom_views.CustomSwitch;
import dev.thec0dec8ter.tmdb.models.Genre;
import dev.thec0dec8ter.tmdb.models.Movie;
import dev.thec0dec8ter.tmdb.models.TvShow;
import dev.thec0dec8ter.tmdb.network.MovieService;
import dev.thec0dec8ter.tmdb.network.RetrofitClientInstance;
import dev.thec0dec8ter.tmdb.network.TvService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static dev.thec0dec8ter.tmdb.BuildConfig.KEY;

public class HomeFragment extends Fragment{

    private CustomSwitch popularSwitch;
    private CustomSwitch nowPlayingSwitch;
    private CustomSwitch trendingSwitch;
    private RecyclerView popularRecycler;
    private RecyclerView nowPlayingRecycler;
    private RecyclerView trendingRecycler;

    private ShowAdapter popularShowAdapter;
    private ShowAdapter nowPlayingMovie;
    private ShowAdapter trendingShowAdapter;
    private ShowAdapter popularTvAdapter;
    private ShowAdapter nowPlayingTv;
    private ShowAdapter trendingTvAdapter;

    public static GenreAdapter movieGenreAdapter;
    public static GenreAdapter tvGenreAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        popularTvAdapter = new ShowAdapter();
        nowPlayingTv = new ShowAdapter();
        trendingTvAdapter = new ShowAdapter();
        popularShowAdapter = new ShowAdapter();
        trendingShowAdapter = new ShowAdapter();
        nowPlayingMovie = new ShowAdapter();

        movieGenreAdapter = new GenreAdapter();
        tvGenreAdapter = new GenreAdapter();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        popularSwitch = view.findViewById(R.id.popular_switch);
        popularRecycler = view.findViewById(R.id.popular_recycler);
        nowPlayingSwitch = view.findViewById(R.id.now_playing_switch);
        nowPlayingRecycler = view.findViewById(R.id.now_playing_recycler);
        trendingSwitch = view.findViewById(R.id.trending_switch);
        trendingRecycler = view.findViewById(R.id.trending_recycler);
        addSwitchListeners();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getTvGenres();
        getMovieGenres();
    }

    private void addSwitchListeners(){
        popularSwitch.setOnCheckedChangeListener(new CustomSwitch.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(TextView thumbText, boolean isChecked) {
                if(thumbText.getText().toString().equals("In Theaters")){
                    popularRecycler.setAdapter(popularShowAdapter);
                    if(popularShowAdapter.getItemCount() < 1){
                        getPopularMovies("1");
                    }
                }else{
                    popularRecycler.setAdapter(popularTvAdapter);
                    if(popularTvAdapter.getItemCount()  < 1){
                        getPopularTvShows("1");
                    }
                }

            }
        });

        nowPlayingSwitch.setThumbColor(R.color.light_green);
        nowPlayingSwitch.setThumbTextColor(R.color.dark_blue);
        nowPlayingSwitch.setTrackTextColor(R.color.white);
        nowPlayingSwitch.setOnCheckedChangeListener(new CustomSwitch.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(TextView thumbText, boolean isChecked) {
                if(thumbText.getText().toString().equals("In Theaters")){
                    nowPlayingRecycler.setAdapter(nowPlayingMovie);
                    if(nowPlayingMovie.getItemCount() < 1){
                        getNowPlayingMovies("2");
                    }
                }else{
                    nowPlayingRecycler.setAdapter(nowPlayingTv);
                    if(nowPlayingTv.getItemCount()  < 1){
                        getTvShowAiringToday("2");
                    }
                }

            }
        });

        trendingSwitch.setOnCheckedChangeListener(new CustomSwitch.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(TextView thumbText, boolean isChecked) {
                if(thumbText.getText().toString().equals("In Theaters")){
                    trendingRecycler.setAdapter(trendingShowAdapter);
                    if(trendingShowAdapter.getItemCount() < 1){
                        getTrendingMovies("1");
                    }
                }else{
                    trendingRecycler.setAdapter(trendingTvAdapter);
                    if(trendingTvAdapter.getItemCount()  < 1){
                        getTrendingTvShows("1");
                    }
                }

            }
        });
    }

    private void getPopularTvShows(String page){
        popularRecycler.setAdapter(popularTvAdapter);
        TvService tvService = RetrofitClientInstance.getRetrofitInstance().create(TvService.class);
        Call<TvShow> call = tvService.getPopularTvShows(KEY, page);
        call.enqueue(new Callback<TvShow>() {
            @Override
            public void onResponse(Call<TvShow> call, Response<TvShow> response) {
                ArrayList<Genre> genres = new ArrayList<>();
                for(TvShow tvShow: response.body().getResults()){
                    for (int id:tvShow.getGenre_ids()){
                        genres.add(tvGenreAdapter.getGenreById(id));
                    }
                    tvShow.setGenres(genres);
                    popularTvAdapter.addTvShow(tvShow);
                }
            }

            @Override
            public void onFailure(Call<TvShow> call, Throwable t) {
                Toast.makeText(getContext(), "An error occurred.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getTvShowAiringToday(String page){
        nowPlayingRecycler.setAdapter(nowPlayingTv);
        TvService tvService = RetrofitClientInstance.getRetrofitInstance().create(TvService.class);
        Call<TvShow> call = tvService.getTvShowAiringToday(KEY, page);
        call.enqueue(new Callback<TvShow>() {
            @Override
            public void onResponse(Call<TvShow> call, Response<TvShow> response) {
                ArrayList<Genre> genres = new ArrayList<>();
                for(TvShow tvShow: response.body().getResults()){
                    for (int id:tvShow.getGenre_ids()){
                        genres.add(tvGenreAdapter.getGenreById(id));
                    }
                    tvShow.setGenres(genres);
                    nowPlayingTv.addTvShow(tvShow);
                }
            }

            @Override
            public void onFailure(Call<TvShow> call, Throwable t) {
                Toast.makeText(getContext(), "An error occurred.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getTrendingTvShows(String page){
        trendingRecycler.setAdapter(trendingTvAdapter);
        TvService tvService = RetrofitClientInstance.getRetrofitInstance().create(TvService.class);
        Call<TvShow>call = tvService.getTvShowsTrendingThisWeek(KEY, page);
        call.enqueue(new Callback<TvShow>() {
            @Override
            public void onResponse(Call<TvShow> call, Response<TvShow> response) {
                ArrayList<Genre> genres = new ArrayList<>();
                for(TvShow tvShow: response.body().getResults()){
                    for (int id:tvShow.getGenre_ids()){
                        genres.add(tvGenreAdapter.getGenreById(id));
                    }
                    tvShow.setGenres(genres);
                    trendingTvAdapter.addTvShow(tvShow);
                }
            }

            @Override
            public void onFailure(Call<TvShow> call, Throwable t) {
                Toast.makeText(getContext(), "An error occurred.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getTvGenres(){
        TvService tvService = RetrofitClientInstance.getRetrofitInstance().create(TvService.class);
        Call<Genre> call = tvService.getTvShowGenres(BuildConfig.KEY);
        call.enqueue(new Callback<Genre>() {
            @Override
            public void onResponse(Call<Genre> call, Response<Genre> response) {
                for (Genre genre :response.body().getGenres()){
                    tvGenreAdapter.addGenre(genre);
                }
                getPopularTvShows("1");
                getTvShowAiringToday("1");
                getTrendingTvShows("2");
            }

            @Override
            public void onFailure(Call<Genre> call, Throwable t) {

            }
        });
    }

    private void getPopularMovies(String page){
        popularRecycler.setAdapter(popularShowAdapter);
        MovieService movieService = RetrofitClientInstance.getRetrofitInstance().create(MovieService.class);
        Call<Movie>call = movieService.getPopularMovies(KEY, page);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                ArrayList<Genre> genres = new ArrayList<>();
                for(Movie movie: response.body().getResults()){
                    for (int id:movie.getGenre_ids()){
                        genres.add(movieGenreAdapter.getGenreById(id));
                    }
                    movie.setGenres(genres);
                    popularShowAdapter.addMovie(movie);
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

            }
        });
    }

    private void getNowPlayingMovies(String page){
        nowPlayingRecycler.setAdapter(nowPlayingMovie);
        MovieService movieService = RetrofitClientInstance.getRetrofitInstance().create(MovieService.class);
        Call<Movie>call = movieService.getNowPlayingMovies(KEY,"en-US", page);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                ArrayList<Genre> genres = new ArrayList<>();
                for(Movie movie: response.body().getResults()){
                    for (int id:movie.getGenre_ids()){
                        genres.add(movieGenreAdapter.getGenreById(id));
                    }
                    movie.setGenres(genres);
                    nowPlayingMovie.addMovie(movie);
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

            }
        });
    }

    private void getTrendingMovies(String page){
        trendingRecycler.setAdapter(trendingShowAdapter);
        MovieService movieService = RetrofitClientInstance.getRetrofitInstance().create(MovieService.class);
        Call<Movie>call = movieService.getMoviesTrendingThisWeek(KEY, page);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                ArrayList<Genre> genres = new ArrayList<>();
                for(Movie movie: response.body().getResults()){
                    for (int id:movie.getGenre_ids()){
                        genres.add(movieGenreAdapter.getGenreById(id));
                    }
                    movie.setGenres(genres);
                    trendingShowAdapter.addMovie(movie);
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

            }
        });
    }

    private void getMovieGenres(){
        MovieService movieService = RetrofitClientInstance.getRetrofitInstance().create(MovieService.class);
        Call<Genre> call = movieService.getMovieGenres(BuildConfig.KEY);
        call.enqueue(new Callback<Genre>() {
            @Override
            public void onResponse(Call<Genre> call, Response<Genre> response) {
                for (Genre genre :response.body().getGenres()){
                    movieGenreAdapter.addGenre(genre);
                }
            }

            @Override
            public void onFailure(Call<Genre> call, Throwable t) {

            }
        });
    }

}