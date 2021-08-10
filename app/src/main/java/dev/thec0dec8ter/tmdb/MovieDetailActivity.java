package dev.thec0dec8ter.tmdb;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import dev.thec0dec8ter.tmdb.adapters.CelebrityAdapter;
import dev.thec0dec8ter.tmdb.adapters.ImageAdapter;
import dev.thec0dec8ter.tmdb.adapters.KeywordAdapter;
import dev.thec0dec8ter.tmdb.adapters.MovieAdapter;
import dev.thec0dec8ter.tmdb.models.Celebrity;
import dev.thec0dec8ter.tmdb.models.Movie;
import dev.thec0dec8ter.tmdb.network.MovieService;
import dev.thec0dec8ter.tmdb.network.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static dev.thec0dec8ter.tmdb.network.RetrofitClientInstance.IMAGE_BASE_URL;

public class MovieDetailActivity extends AppCompatActivity {

    private TextView tagLine;
    private TextView title;
    private TextView year;
    private TextView genre;
    private TextView runtime;
    private TextView ratingCount;
    private TextView overview;
    private TextView noKeyword;
    private ImageView backdrop;
    private ImageView poster;
    private RecyclerView castRecycler;
    private RecyclerView imageRecycler;
    private RecyclerView keywordRecycler;
    private RecyclerView similarRecycler;

    private CelebrityAdapter celebrityAdapter;
    private ImageAdapter imageAdapter;
    private KeywordAdapter keywordAdapter;
    private MovieAdapter similarAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        tagLine = findViewById(R.id.tagline);
        title = findViewById(R.id.title);
        year = findViewById(R.id.year);
        genre = findViewById(R.id.genre);
        runtime = findViewById(R.id.runtime);
        backdrop = findViewById(R.id.img_backdrop);
        poster = findViewById(R.id.img_poster);
        ratingCount = findViewById(R.id.rating_count);
        overview = findViewById(R.id.overview);
        noKeyword = findViewById(R.id.txt_no_keyword);
        castRecycler = findViewById(R.id.cast_recycler);
        imageRecycler = findViewById(R.id.image_recycler);
        keywordRecycler = findViewById(R.id.keyword_recycler);
        similarRecycler = findViewById(R.id.similar_recycler);

        imageAdapter = new ImageAdapter();
        keywordAdapter = new KeywordAdapter();
        celebrityAdapter = new CelebrityAdapter();
        similarAdapter = new MovieAdapter();

        imageRecycler.setAdapter(imageAdapter);
        keywordRecycler.setAdapter(keywordAdapter);
        castRecycler.setAdapter(celebrityAdapter);

        String id = getIntent().getStringExtra("movie_id");

        assert id != null;
        similarRecycler.setAdapter(similarAdapter);

        MovieService movieService = RetrofitClientInstance.getRetrofitInstance().create(MovieService.class);
        Call<Movie> call = movieService.getMovieDetails(id, BuildConfig.KEY);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                tagLine.setText(response.body().getTagline());
                title.setText(response.body().getTitle());
                year.setText(response.body().getRelease_date().split("-")[0]);
                overview.setText(response.body().getOverview());

                Picasso.get()
                        .load(IMAGE_BASE_URL+ response.body().getBackdrop_path())
                        .fit()
                        .into(backdrop);
                Picasso.get()
                        .load(IMAGE_BASE_URL+ response.body().getPoster_path())
                        .fit()
                        .into(poster);

                int runtimeHours = response.body().getRuntime() / 60;
                int runtimeMins = response.body().getRuntime() % 60;
                int ratePercent = Math.round(response.body().getVote_average()) * 10;

                StringBuilder builder = new StringBuilder();
                builder.append(runtimeHours).append("h ").append(runtimeMins).append("m");

                runtime.setText(builder.toString());
                ratingCount.setText(String.valueOf(ratePercent));

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

        call = movieService.getMovieKeywords(id, BuildConfig.KEY);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                List<String> keywords = new ArrayList<>();
                for(Movie movie :response.body().getKeywords()){
                    keywords.add(movie.getGenreName());
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

        call = movieService.getSimilarMovies(id, BuildConfig.KEY, "1");
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                similarAdapter.addMovies(response.body().getResults());

            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.e("DetailActivity: ",t.getMessage());
            }
        });
    }
}