package dev.thec0dec8ter.tmdb;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import dev.thec0dec8ter.tmdb.adapters.CelebrityAdapter;
import dev.thec0dec8ter.tmdb.adapters.ImageAdapter;
import dev.thec0dec8ter.tmdb.adapters.KeywordAdapter;
import dev.thec0dec8ter.tmdb.adapters.MovieAdapter;
import dev.thec0dec8ter.tmdb.adapters.TvAdapter;
import dev.thec0dec8ter.tmdb.models.Celebrity;
import dev.thec0dec8ter.tmdb.models.Movie;
import dev.thec0dec8ter.tmdb.models.TvShow;
import dev.thec0dec8ter.tmdb.network.MovieService;
import dev.thec0dec8ter.tmdb.network.RetrofitClientInstance;
import dev.thec0dec8ter.tmdb.network.TvService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static dev.thec0dec8ter.tmdb.network.RetrofitClientInstance.IMAGE_BASE_URL;
import static dev.thec0dec8ter.tmdb.network.RetrofitClientInstance.getMovieDetails;
import static dev.thec0dec8ter.tmdb.network.RetrofitClientInstance.getTvShowDetails;

public class DetailActivity extends AppCompatActivity {

    private AppCompatTextView tagLine;
    private AppCompatTextView title;
    private AppCompatTextView year;
    private AppCompatTextView genreName;
    private AppCompatTextView runtime;
    private AppCompatTextView ratingCount;
    private AppCompatTextView overview;
    private AppCompatTextView noKeyword;
    private AppCompatImageView backdrop;
    private AppCompatImageView poster;
    private RecyclerView castRecycler;
    private RecyclerView mediaRecycler;
    private RecyclerView keywordRecycler;
    private RecyclerView similarRecycler;

    private CelebrityAdapter celebrityAdapter;
    private ImageAdapter mediaAdapter;
    private KeywordAdapter keywordAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tagLine = findViewById(R.id.tagline);
        title = findViewById(R.id.title);
        year = findViewById(R.id.year);
        genreName = findViewById(R.id.genre_name);
        runtime = findViewById(R.id.runtime);
        backdrop = findViewById(R.id.img_backdrop);
        poster = findViewById(R.id.img_poster);
        ratingCount = findViewById(R.id.rating_count);
        overview = findViewById(R.id.overview);
        noKeyword = findViewById(R.id.txt_no_keyword);
        castRecycler = findViewById(R.id.cast_recycler);
        mediaRecycler = findViewById(R.id.media_recycler);
        keywordRecycler = findViewById(R.id.keyword_recycler);
        similarRecycler = findViewById(R.id.similar_recycler);

        mediaAdapter = new ImageAdapter();
        mediaRecycler.setAdapter(mediaAdapter);
        keywordAdapter = new KeywordAdapter();
        keywordRecycler.setAdapter(keywordAdapter);
        celebrityAdapter = new CelebrityAdapter();
        castRecycler.setAdapter(celebrityAdapter);

        String movieExtra = getIntent().getStringExtra("movie_id");
        String tvExtra = getIntent().getStringExtra("tv_id");

        if(movieExtra != null) {
            Movie movie = getMovieDetails(movieExtra);
            MovieAdapter movieAdapter = new MovieAdapter();
            similarRecycler.setAdapter(movieAdapter);


            MovieService movieService = RetrofitClientInstance.getRetrofitInstance().create(MovieService.class);
            Call<Movie> call = movieService.getSimilarMovies(movie.getId(), BuildConfig.KEY, "1");
            call.enqueue(new Callback<Movie>() {
                @Override
                public void onResponse(Call<Movie> call, Response<Movie> response) {
                    movieAdapter.addMovies(response.body().getResults());
                }

                @Override
                public void onFailure(Call<Movie> call, Throwable t) {
                    Log.e("DetailActivity: ",t.getMessage());
                }
            });
            call = movieService.getMovieImages(movie.getId(), BuildConfig.KEY);
            call.enqueue(new Callback<Movie>() {
                @Override
                public void onResponse(Call<Movie> call, Response<Movie> response) {
                    ArrayList<String> media = new ArrayList<>();
                    for(Movie movie :response.body().getBackdrops()){
                        media.add(movie.getFile_path());
                    }
                    for(Movie movie :response.body().getPosters()){
                        media.add(movie.getFile_path());
                    }

                }

                @Override
                public void onFailure(Call<Movie> call, Throwable t) {
                    Log.e("DetailActivity",t.getMessage());
                }
            });

            call = movieService.getMovieKeywords(movie.getId(), BuildConfig.KEY);
            call.enqueue(new Callback<Movie>() {
                @Override
                public void onResponse(Call<Movie> call, Response<Movie> response) {
                    List<String> keywords = new ArrayList<>();
                    for(Movie movie :response.body().getKeywords()){
                        keywords.add(movie.getName());
                    }
                    if(keywords.size() > 0){
                        noKeyword.setVisibility(View.GONE);
                        keywordAdapter.addTexts(keywords);
                    }
                }

                @Override
                public void onFailure(Call<Movie> call, Throwable t) {
                    Log.e("DetailActivity: ",t.getMessage());
                }
            });

            genreName.setText(movie.getGenres().get(0).getName());

            int runtimeHours = movie.getRuntime() / 60;
            int runtimeMins = movie.getRuntime() % 60;
            runtime.setText(runtimeHours + "h " + runtimeMins + "m");

            int ratePercent = Math.round(movie.getVote_average())*10;
            ratingCount.setText(String.valueOf(ratePercent));
        }else if(tvExtra != null) {
            TvShow tvShow = getTvShowDetails(tvExtra);
            TvAdapter tvAdapter = new TvAdapter();
            similarRecycler.setAdapter(tvAdapter);

            TvService tvService = RetrofitClientInstance.getRetrofitInstance().create(TvService.class);
            Call<TvShow> call = tvService.getTvShowImages(tvShow.getId(), BuildConfig.KEY);
            call.enqueue(new Callback<TvShow>() {
                @Override
                public void onResponse(Call<TvShow> call, Response<TvShow> response) {
                    ArrayList<String> media = new ArrayList<>();
                    for(TvShow tvShow :response.body().getBackdrops()){
                        media.add(tvShow.getFile_path());
                    }
                    for(TvShow tvShow :response.body().getPosters()){
                        media.add(tvShow.getFile_path());
                    }

                }

                @Override
                public void onFailure(Call<TvShow> call, Throwable t) {
                    Log.e("DetailActivity",t.getMessage());
                }
            });

            call = tvService.getTvShowKeywords(tvShow.getId(), BuildConfig.KEY);
            call.enqueue(new Callback<TvShow>() {
                @Override
                public void onResponse(Call<TvShow> call, Response<TvShow> response) {
                    List<String> keywords = new ArrayList<>();
                    if (response.body().getKeywords() != null) {
                        for (TvShow tvShow : response.body().getKeywords()) {
                            keywords.add(tvShow.getName());
                        }
                    }
                    if(keywords.size() > 0){
                        noKeyword.setVisibility(View.GONE);
                        keywordAdapter.addTexts(keywords);
                    }
                }

                @Override
                public void onFailure(Call<TvShow> call, Throwable t) {
                    Log.e("DetailActivity",t.getMessage());
                }
            });

            call = tvService.getSimilarTvShows(tvShow.getId(), BuildConfig.KEY, "1");
            call.enqueue(new Callback<TvShow>() {
                @Override
                public void onResponse(Call<TvShow> call, Response<TvShow> response) {
                    tvAdapter.addTvShows(response.body().getResults());
                }

                @Override
                public void onFailure(Call<TvShow> call, Throwable t) {
                    Log.e("DetailActivity",t.getMessage());
                }
            });

            Call<Celebrity> creditCall = tvService.getTvShowCredits(tvShow.getId(), BuildConfig.KEY);
            creditCall.enqueue(new Callback<Celebrity>() {
                @Override
                public void onResponse(Call<Celebrity> call, Response<Celebrity> response) {

                }

                @Override
                public void onFailure(Call<Celebrity> call, Throwable t) {
                    Log.e("DetailActivity",t.getMessage());
                }
            });

            Picasso.get()
                    .load(IMAGE_BASE_URL+ tvShow.getBackdrop_path())
                    .fit()
                    .into(backdrop);
            tagLine.setText(tvShow.getTagline());
            title.setText(tvShow.getName());
            year.setText(tvShow.getFirst_air_date().split("-")[0]);
            genreName.setText(tvShow.getGenres().get(0).getName());
            if (tvShow.getEpisode_runtime() != null) {
                int runtimeHours = tvShow.getEpisode_runtime()[0] / 60;
                int runtimeMins = tvShow.getEpisode_runtime()[0] % 60;
                StringBuilder builder = new StringBuilder();
                builder.append(runtimeHours).append("h ").append(runtimeMins).append("m");
                runtime.setText(builder.toString());
            }
            Picasso.get()
                    .load(IMAGE_BASE_URL+ tvShow.getPoster_path())
                    .fit()
                    .into(poster);
            overview.setText(tvShow.getOverview());
            int ratePercent = Math.round(tvShow.getVote_average())*10;
            ratingCount.setText(String.valueOf(ratePercent));
        }else {
            finish();
        }

    }

}