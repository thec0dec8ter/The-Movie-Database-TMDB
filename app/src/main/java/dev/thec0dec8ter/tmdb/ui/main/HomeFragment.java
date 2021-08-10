package dev.thec0dec8ter.tmdb.ui.main;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import dev.thec0dec8ter.tmdb.BuildConfig;
import dev.thec0dec8ter.tmdb.R;
import dev.thec0dec8ter.tmdb.adapters.GenreAdapter;
import dev.thec0dec8ter.tmdb.adapters.MovieAdapter;
import dev.thec0dec8ter.tmdb.adapters.TvAdapter;
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

    private FrameLayout popularSwitch;
    private RecyclerView popularRecycler;
    private FrameLayout nowPlayingSwitch;
    private RecyclerView nowPlayingRecycler;
    private FrameLayout trendingSwitch;
    private RecyclerView trendingRecycler;

    private Animator animator;

    private TvAdapter popularTvAdapter;
    private TvAdapter nowPlayingTv;
    private TvAdapter trendingTvAdapter;
    private MovieAdapter popularMovieAdapter;
    private MovieAdapter nowPlayingMovie;
    private MovieAdapter trendingMovieAdapter;

    public static GenreAdapter movieGenreAdapter;
    public static GenreAdapter tvGenreAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        popularTvAdapter = new TvAdapter();
        nowPlayingTv = new TvAdapter();
        trendingTvAdapter = new TvAdapter();
        popularMovieAdapter = new MovieAdapter();
        trendingMovieAdapter = new MovieAdapter();
        nowPlayingMovie = new MovieAdapter();

        movieGenreAdapter = new GenreAdapter();
        tvGenreAdapter = new GenreAdapter();

        getMovieGenres();
        getTvGenres();

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

        ((TextView) popularSwitch.findViewById(R.id.thumb_text)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.toString().equals("In Theaters")){
                    popularRecycler.setAdapter(popularMovieAdapter);
                    if(popularMovieAdapter.getItemCount() < 1){
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

        ((TextView) nowPlayingSwitch.findViewById(R.id.thumb_text)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.toString().equals("In Theaters")){
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

        ((TextView) trendingSwitch.findViewById(R.id.thumb_text)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.toString().equals("In Theaters")){
                    trendingRecycler.setAdapter(trendingMovieAdapter);
                    if(trendingMovieAdapter.getItemCount() < 1){
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

        addSwitchListeners(popularSwitch);
        addSwitchListeners(nowPlayingSwitch);
        addSwitchListeners(trendingSwitch);

        getPopularTvShows("1");
        getTvShowAiringToday("2");
        getTrendingTvShows("1");

        return view;
    }

    private void getMovieGenres(){
        MovieService movieService = RetrofitClientInstance.getRetrofitInstance().create(MovieService.class);
        Call<Movie> call = movieService.getMovieGenres(BuildConfig.KEY);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                for (Movie movie :response.body().getGenres()){
                    movieGenreAdapter.addGenre(movie.getId(), movie.getGenreName());
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

            }
        });
    }

    private void getTvGenres(){
        TvService tvService = RetrofitClientInstance.getRetrofitInstance().create(TvService.class);
        Call<TvShow> call = tvService.getTvShowGenres(BuildConfig.KEY);
        call.enqueue(new Callback<TvShow>() {
            @Override
            public void onResponse(Call<TvShow> call, Response<TvShow> response) {
                for (TvShow tvShow :response.body().getGenres()){
                    tvGenreAdapter.addGenre(tvShow.getId(), tvShow.getName());
                }
            }

            @Override
            public void onFailure(Call<TvShow> call, Throwable t) {

            }
        });
    }

    private void addSwitchListeners(FrameLayout frameLayout){
        TextView leftTrack = frameLayout.findViewById(R.id.left_track);
        TextView rightTrack = frameLayout.findViewById(R.id.right_track);
        TextView thumbText = frameLayout.findViewById(R.id.thumb_text);
        CardView thumb = frameLayout.findViewById(R.id.thumb);

        leftTrack.setOnClickListener(view -> {
            String thumb_text = thumbText.getText().toString();
            String left_text = leftTrack.getText().toString();
            if (!thumb_text.equals(left_text)) {
                animator = AnimatorInflater.loadAnimator(view.getContext(), R.animator.switch_slide_left);
                animator.setTarget(thumb);
                animator.start();
                thumbText.setText(leftTrack.getText());
            }
        });

        rightTrack.setOnClickListener(view -> {
            String thumb_text = thumbText.getText().toString();
            String right_text = rightTrack.getText().toString();
            if (!thumb_text.equals(right_text)) {
                animator = AnimatorInflater.loadAnimator(view.getContext(),R.animator.switch_slide_right);
                animator.setTarget(thumb);
                animator.start();
                thumbText.setText(rightTrack.getText());
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
                popularTvAdapter.addTvShows(response.body().getResults());
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
                nowPlayingTv.addTvShows(response.body().getResults());
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
                trendingTvAdapter.addTvShows(response.body().getResults());
            }

            @Override
            public void onFailure(Call<TvShow> call, Throwable t) {
                Toast.makeText(getContext(), "An error occurred.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getPopularMovies(String page){
        popularRecycler.setAdapter(popularMovieAdapter);
        MovieService movieService = RetrofitClientInstance.getRetrofitInstance().create(MovieService.class);
        Call<Movie>call = movieService.getPopularMovies(KEY, page);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                popularMovieAdapter.addMovies(response.body().getResults());
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
                nowPlayingMovie.addMovies(response.body().getResults());
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

            }
        });
    }

    private void getTrendingMovies(String page){
        trendingRecycler.setAdapter(trendingMovieAdapter);
        MovieService movieService = RetrofitClientInstance.getRetrofitInstance().create(MovieService.class);
        Call<Movie>call = movieService.getMoviesTrendingThisWeek(KEY, page);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                trendingMovieAdapter.addMovies(response.body().getResults());

            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

            }
        });
    }

}