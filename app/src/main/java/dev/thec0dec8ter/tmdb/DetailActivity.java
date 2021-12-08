package dev.thec0dec8ter.tmdb;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import dev.thec0dec8ter.tmdb.adapters.CelebrityAdapter;
import dev.thec0dec8ter.tmdb.adapters.ImageAdapter;
import dev.thec0dec8ter.tmdb.adapters.ShowAdapter;
import dev.thec0dec8ter.tmdb.models.Celebrity;
import dev.thec0dec8ter.tmdb.models.Genre;
import dev.thec0dec8ter.tmdb.models.Movie;
import dev.thec0dec8ter.tmdb.models.TvShow;
import dev.thec0dec8ter.tmdb.network.MovieService;
import dev.thec0dec8ter.tmdb.network.RetrofitClientInstance;
import dev.thec0dec8ter.tmdb.network.TvService;
import dev.thec0dec8ter.tmdb.ui.main.HomeFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static dev.thec0dec8ter.tmdb.network.RetrofitClientInstance.IMAGE_BASE_URL;

public class DetailActivity extends AppCompatActivity {

    private TextView tagLine;
    private TextView title;
    private TextView release_info;
    private TextView genre;
    private TextView ratingCount;
    private TextView synopsis;
    private ImageView imgBackdrop;
    private ImageView imgPoster;
    private RecyclerView castRecycler;
    private RecyclerView posterRecycler;
    private RecyclerView similarRecycler;

    private CelebrityAdapter celebrityAdapter;
    private ImageAdapter imageAdapter;
    private ShowAdapter similarAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        title = findViewById(R.id.title);
        release_info = findViewById(R.id.release_info);
        tagLine = findViewById(R.id.tagline);
        genre = findViewById(R.id.genre);
        synopsis = findViewById(R.id.synopsis);

        imgBackdrop = findViewById(R.id.img_backdrop);
        imgPoster = findViewById(R.id.img_poster);

        castRecycler = findViewById(R.id.cast_recycler);
        posterRecycler = findViewById(R.id.poster_recycler);
        similarRecycler = findViewById(R.id.similar_recycler);

        imageAdapter = new ImageAdapter();
        celebrityAdapter = new CelebrityAdapter();
        similarAdapter = new ShowAdapter();

        posterRecycler.setAdapter(imageAdapter);
        castRecycler.setAdapter(celebrityAdapter);
        similarRecycler.setAdapter(similarAdapter);


        if(getIntent().getStringExtra("movie_id") != null){
            String id = getIntent().getStringExtra("movie_id");
            getMovieDetail(id);
        }else if(getIntent().getStringExtra("tv_id") != null){
            String id = getIntent().getStringExtra("tv_id");
            getTvShowDetails(id);
        }else {
            finish();
        }
    }

    public void getMovieDetail(String id){
        MovieService movieService = RetrofitClientInstance.getRetrofitInstance().create(MovieService.class);
        Call<Movie> call = movieService.getMovieDetails(id, BuildConfig.KEY);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                title.setText(response.body().getTitle());
                tagLine.setText(response.body().getTagline());
                synopsis.setText(response.body().getOverview());

                Picasso.get()
                        .load(IMAGE_BASE_URL+ response.body().getPoster_path())
                        .fit()
                        .into(imgPoster);

                Picasso.get()
                        .load(IMAGE_BASE_URL+ response.body().getBackdrop_path())
                        .fit()
                        .into(imgBackdrop);

                int runtimeHours = response.body().getRuntime() / 60;
                int runtimeMins = response.body().getRuntime() % 60;
                StringBuilder builder = new StringBuilder(response.body().getRelease_date());
                builder.append(" | ");
                builder.append(runtimeHours + " hours ");
                builder.append(runtimeMins + " mins");
                release_info.setText(builder.toString());


                int ratePercent = Math.round(response.body().getVote_average()) * 10;

            }
            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.e("DetailActivity: ",t.getMessage());
            }
        });

        Call<Celebrity> creditCall = movieService.getMovieCredits(id, BuildConfig.KEY);
        creditCall.enqueue(new Callback<Celebrity>() {
            @Override
            public void onResponse(Call<Celebrity> call, Response<Celebrity> response) {
                celebrityAdapter.addCelebrities(response.body().getCredits());
            }

            @Override
            public void onFailure(Call<Celebrity> call, Throwable t) {

            }
        });

        call = movieService.getMovieImages(id, BuildConfig.KEY);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                ArrayList<String> images = new ArrayList<>();
                for(Movie movie :response.body().getBackdrops()){
                    images.add(movie.getFile_path());
                }
                for(Movie movie :response.body().getPosters()){
                    images.add(movie.getFile_path());
                }
                imageAdapter.addImages(images);
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.e("DetailActivity",t.getMessage());
            }
        });

        call = movieService.getSimilarMovies(id, BuildConfig.KEY, "1");
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                ArrayList<Genre> genres = new ArrayList<>();
                for(Movie movie: response.body().getResults()){
                    for (int id:movie.getGenre_ids()){
                        genres.add(HomeFragment.movieGenreAdapter.getGenreById(id));
                    }
                    movie.setGenres(genres);
                    similarAdapter.addMovie(movie);
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.e("DetailActivity: ",t.getMessage());
            }
        });

    }

    public void getTvShowDetails(String id){
        TvService tvService = RetrofitClientInstance.getRetrofitInstance().create(TvService.class);
        Call<TvShow> call = tvService.getTvShowDetails(id, BuildConfig.KEY);
        call.enqueue(new Callback<TvShow>() {
            @Override
            public void onResponse(@NotNull Call<TvShow> call, @NotNull Response<TvShow> response) {
                tagLine.setText(response.body().getTagline());
                title.setText(response.body().getName());
                synopsis.setText(response.body().getOverview());

                Picasso.get()
                        .load(IMAGE_BASE_URL+ response.body().getBackdrop_path())
                        .fit()
                        .into(imgBackdrop);
                Picasso.get()
                        .load(IMAGE_BASE_URL+ response.body().getPoster_path())
                        .fit()
                        .into(imgBackdrop);

                if (response.body().getEpisode_runtime() != null) {
                    int runtimeHours = response.body().getEpisode_runtime()[0] / 60;
                    int runtimeMins = response.body().getEpisode_runtime()[0] % 60;

                    StringBuilder builder = new StringBuilder(response.body().getFirst_air_date());
                    builder.append(" | ");
                    builder.append(runtimeHours + " hours");
                    builder.append(runtimeMins + " mins");
                    release_info.setText(builder.toString());
                }

            }

            @Override
            public void onFailure(@NotNull Call<TvShow> call, @NotNull Throwable t) {
                Log.e("DetailActivity",t.getMessage());
            }
        });

        Call<Celebrity> creditCall = tvService.getTvShowCredits(id, BuildConfig.KEY);
        creditCall.enqueue(new Callback<Celebrity>() {
            @Override
            public void onResponse(Call<Celebrity> call, Response<Celebrity> response) {
                celebrityAdapter.addCelebrities(response.body().getCredits());
            }

            @Override
            public void onFailure(Call<Celebrity> call, Throwable t) {

            }
        });

        call = tvService.getTvShowImages(id, BuildConfig.KEY);
        call.enqueue(new Callback<TvShow>() {
            @Override
            public void onResponse(Call<TvShow> call, Response<TvShow> response) {
                ArrayList<String> images = new ArrayList<>();
                for(TvShow tvShow :response.body().getBackdrops()){
                    images.add(tvShow.getFile_path());
                }
                for(TvShow tvShow :response.body().getPosters()){
                    images.add(tvShow.getFile_path());
                }
                imageAdapter.addImages(images);
            }

            @Override
            public void onFailure(Call<TvShow> call, Throwable t) {
                Log.e("DetailActivity",t.getMessage());
            }
        });

        call = tvService.getSimilarTvShows(id, BuildConfig.KEY, "1");
        call.enqueue(new Callback<TvShow>() {
            @Override
            public void onResponse(Call<TvShow> call, Response<TvShow> response) {
                ArrayList<Genre> genres = new ArrayList<>();
                for(TvShow tvShow: response.body().getResults()){
                    for (int id:tvShow.getGenre_ids()){
                        genres.add(HomeFragment.tvGenreAdapter.getGenreById(id));
                    }
                    tvShow.setGenres(genres);
                    similarAdapter.addTvShow(tvShow);
                }
            }

            @Override
            public void onFailure(Call<TvShow> call, Throwable t) {
                Log.e("DetailActivity",t.getMessage());
            }
        });
    }
}