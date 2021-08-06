package dev.thec0dec8ter.tmdb.network;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

import dev.thec0dec8ter.tmdb.BuildConfig;
import dev.thec0dec8ter.tmdb.models.Movie;
import dev.thec0dec8ter.tmdb.models.TvShow;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static dev.thec0dec8ter.tmdb.BuildConfig.KEY;

public class RetrofitClientInstance {

    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500/";

    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static Movie getMovieDetails(String id){
        final Movie movie = new Movie();

        MovieService movieService = getRetrofitInstance().create(MovieService.class);
        Call<Movie> call = movieService.getMovieDetails(id, BuildConfig.KEY);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                movie.setBackdrop_path(response.body().getBackdrop_path());
                movie.setTitle(response.body().getTitle());
                movie.setTagline(response.body().getTagline());
                if(response.body().getGenres().size() > 0){
                    movie.setGenres(response.body().getGenres());
                }
                movie.setRelease_date(response.body().getRelease_date());
                movie.setPoster_path(response.body().getPoster_path());
                movie.setOverview(response.body().getOverview());
                movie.setRuntime(response.body().getRuntime());
                movie.setVote_average(response.body().getVote_average()*10);
            }
            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.e("DetailActivity: ",t.getMessage());
            }
        });
        return movie;
    }

    public static TvShow getTvShowDetails(String id){
        final TvShow tvShow = new TvShow();

        TvService tvService = getRetrofitInstance().create(TvService.class);
        Call<TvShow> call = tvService.getTvShowDetails(id, KEY);
        call.enqueue(new Callback<TvShow>() {
            @Override
            public void onResponse(@NotNull Call<TvShow> call, @NotNull Response<TvShow> response) {
                tvShow.setBackdrop_path(response.body().getBackdrop_path());
                tvShow.setTagline(response.body().getTagline());
                tvShow.setName(response.body().getName());
                tvShow.setFirst_air_date(response.body().getFirst_air_date());
                tvShow.setGenres(response.body().getGenres());
                tvShow.setEpisode_runtime(response.body().getEpisode_runtime());
                tvShow.setPoster_path(response.body().getPoster_path());
                tvShow.setVote_average(response.body().getVote_average());
            }

            @Override
            public void onFailure(@NotNull Call<TvShow> call, @NotNull Throwable t) {
                Log.e("DetailActivity",t.getMessage());
            }
        });
        return tvShow;
    }
}
