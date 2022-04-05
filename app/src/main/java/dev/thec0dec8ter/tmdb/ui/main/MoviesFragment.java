package dev.thec0dec8ter.tmdb.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dev.thec0dec8ter.tmdb.BuildConfig;
import dev.thec0dec8ter.tmdb.R;
import dev.thec0dec8ter.tmdb.SeeAllFragment;
import dev.thec0dec8ter.tmdb.adapters.GenreAdapter;
import dev.thec0dec8ter.tmdb.adapters.ShowAdapter;
import dev.thec0dec8ter.tmdb.custom_views.CustomButton;
import dev.thec0dec8ter.tmdb.models.Genre;
import dev.thec0dec8ter.tmdb.models.Movie;
import dev.thec0dec8ter.tmdb.network.MovieService;
import dev.thec0dec8ter.tmdb.network.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesFragment extends Fragment {

    private CustomButton seeAllTopRated;
    private RecyclerView topRatedRecycler;
    private CustomButton seeAllGenres;
    private RecyclerView genreRecycler;
    private CustomButton seeAllPopular;
    private RecyclerView popularMoviesRecycler;
    private RecyclerView upcomingRecycler;

    private ShowAdapter topRatedAdapter;
    private GenreAdapter genreAdapter;
    private ShowAdapter popularMoviesAdapter;
    private ShowAdapter upcomingAdapter;

    private MovieService movieService;
    private Call<Movie> call;


    public MoviesFragment(){
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movieService = RetrofitClientInstance.getRetrofitInstance().create(MovieService.class);
        topRatedAdapter = new ShowAdapter();
        genreAdapter = HomeFragment.movieGenreAdapter;
        popularMoviesAdapter = new ShowAdapter();
        upcomingAdapter = new ShowAdapter();

        getTopRatedMovies("1");
        getPopularMovies("2");
        getUpcomingMovies("1");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies, container, false);
        seeAllTopRated = view.findViewById(R.id.see_all_top_rated);
        topRatedRecycler = view.findViewById(R.id.top_rated_recycler);
        seeAllGenres = view.findViewById(R.id.see_all_genres);
        genreRecycler = view.findViewById(R.id.genre_recycler);
        seeAllPopular = view.findViewById(R.id.see_all_popular);
        popularMoviesRecycler = view.findViewById(R.id.popular_movies_recycler);
        upcomingRecycler = view.findViewById(R.id.upcoming_recycler);
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        topRatedRecycler.setAdapter(topRatedAdapter);
        genreRecycler.setAdapter(genreAdapter);
        popularMoviesRecycler.setAdapter(popularMoviesAdapter);
        upcomingRecycler.setAdapter(upcomingAdapter);

        seeAllTopRated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.activity_main, new SeeAllFragment("Top rated movies", topRatedAdapter))
                        .commit();
            }
        });

        seeAllGenres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.activity_main, new SeeAllFragment("Popular Genres", genreAdapter))
                        .commit();
            }
        });

        seeAllPopular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.activity_main, new SeeAllFragment("Popular Movies", popularMoviesAdapter))
                        .commit();
            }
        });


    }

    private void getTopRatedMovies(String page){
        call = movieService.getTopRatedMovies(BuildConfig.KEY, page);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                ArrayList<Genre> genres = new ArrayList<>();
                for(Movie movie: response.body().getResults()){
                    for (int id:movie.getGenre_ids()){
                        genres.add(genreAdapter.getGenreById(id));
                    }
                    movie.setGenres(genres);
                    topRatedAdapter.addMovie(movie);
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getPopularMovies(String page){
        call = movieService.getPopularMovies(BuildConfig.KEY, page);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                ArrayList<Genre> genres = new ArrayList<>();
                for(Movie movie: response.body().getResults()){
                    for (int id:movie.getGenre_ids()){
                        genres.add(genreAdapter.getGenreById(id));
                    }
                    movie.setGenres(genres);
                    popularMoviesAdapter.addMovie(movie);
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getUpcomingMovies(String page){
        call = movieService.getUpcomingMovies(BuildConfig.KEY, page);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                ArrayList<Genre> genres = new ArrayList<>();
                for(Movie movie: response.body().getResults()){
                    for (int id:movie.getGenre_ids()){
                        genres.add(genreAdapter.getGenreById(id));
                    }
                    movie.setGenres(genres);
                    upcomingAdapter.addMovie(movie);
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.e("KKKKKKK",t.getMessage());

            }
        });
    }

}