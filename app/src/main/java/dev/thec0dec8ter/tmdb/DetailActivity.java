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
import dev.thec0dec8ter.tmdb.adapters.KeywordAdapter;
import dev.thec0dec8ter.tmdb.adapters.MediaAdapter;
import dev.thec0dec8ter.tmdb.adapters.MovieAdapter;
import dev.thec0dec8ter.tmdb.adapters.TvAdapter;
import dev.thec0dec8ter.tmdb.network.CelebrityResponse;
import dev.thec0dec8ter.tmdb.network.MovieResponse;
import dev.thec0dec8ter.tmdb.network.MovieService;
import dev.thec0dec8ter.tmdb.network.NetworkUtils;
import dev.thec0dec8ter.tmdb.network.RetrofitClientInstance;
import dev.thec0dec8ter.tmdb.network.TvResponse;
import dev.thec0dec8ter.tmdb.network.TvService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static dev.thec0dec8ter.tmdb.BuildConfig.KEY;

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
    private MediaAdapter mediaAdapter;
    private KeywordAdapter keywordAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        String movieExtra = getIntent().getStringExtra("movie_id");
        String tvExtra = getIntent().getStringExtra("tv_id");

        if( movieExtra== null && tvExtra == null){
            finish();
        }

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


        mediaAdapter = new MediaAdapter();
        mediaRecycler.setAdapter(mediaAdapter);
        keywordAdapter = new KeywordAdapter();
        keywordRecycler.setAdapter(keywordAdapter);
        celebrityAdapter = new CelebrityAdapter();
        castRecycler.setAdapter(celebrityAdapter);

        if(getIntent().getStringExtra("movie_id") != null) {
            getMovieDetails(getIntent().getStringExtra("movie_id"));
        }else if(getIntent().getStringExtra("tv_id") != null) {
            getTvShowDetails(getIntent().getStringExtra("tv_id"));
        }

    }

    private void getMovieDetails(String id){
        MovieAdapter movieAdapter = new MovieAdapter();
        similarRecycler.setAdapter(movieAdapter);

        MovieService movieService = RetrofitClientInstance.getRetrofitInstance().create(MovieService.class);
        Call<MovieResponse> call = movieService.getMovieDetails(id, BuildConfig.KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                MovieResponse movieResponse = response.body();
                Picasso.get()
                        .load(NetworkUtils.IMAGE_BASE_URL + movieResponse.getBackdrop_path())
                        .fit()
                        .into(backdrop);
                tagLine.setText(movieResponse.getTagline());
                title.setText(movieResponse.getTitle());
                if(movieResponse.getGenres().size() > 0){
                    genreName.setText(movieResponse.getGenres().get(0).getName());
                }
                year.setText(movieResponse.getRelease_date().split("-")[0]);
                Picasso.get()
                        .load(NetworkUtils.IMAGE_BASE_URL + movieResponse.getPoster_path())
                        .fit()
                        .into(poster);
                overview.setText(movieResponse.getOverview());
                int runtimeHours = movieResponse.getRuntime() / 60;
                int runtimeMins = movieResponse.getRuntime() % 60;
                runtime.setText(runtimeHours + "h " + runtimeMins + "m");
                int ratePercent = Math.round(movieResponse.getVote_average())*10;
                ratingCount.setText(String.valueOf(ratePercent));
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.e("DetailActivity: ",t.getMessage());
            }
        });

        call = movieService.getMovieImages(id, BuildConfig.KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                ArrayList<String> media = new ArrayList<>();
                for(MovieResponse movieResponse:response.body().getBackdrops()){
                    media.add(movieResponse.getFile_path());
                }
                for(MovieResponse movieResponse:response.body().getPosters()){
                    media.add(movieResponse.getFile_path());
                }
                mediaAdapter.addMedia(media,false);
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.e("DetailActivity",t.getMessage());
            }
        });

        call = movieService.getMovieKeywords(id, BuildConfig.KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                List<String> keywords = new ArrayList<>();
                for(MovieResponse movieResponse:response.body().getKeywords()){
                    keywords.add(movieResponse.getName());
                }
                if(keywords.size() > 0){
                  noKeyword.setVisibility(View.GONE);
                  keywordAdapter.addTexts(keywords);
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.e("DetailActivity: ",t.getMessage());
            }
        });

        call = movieService.getSimilarMovies(id, BuildConfig.KEY, "1");
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                movieAdapter.addMovies(response.body().getResults());
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.e("DetailActivity: ",t.getMessage());
            }
        });

        Call<CelebrityResponse> creditCall = movieService.getMovieCredits(id, BuildConfig.KEY);
        creditCall.enqueue(new Callback<CelebrityResponse>() {
            @Override
            public void onResponse(Call<CelebrityResponse> call, Response<CelebrityResponse> response) {
                celebrityAdapter.addCelebrities(response.body().getCast());
            }

            @Override
            public void onFailure(Call<CelebrityResponse> call, Throwable t) {
                Log.e("DetailActivity: ",t.getMessage());
            }
        });

    }

    private void getTvShowDetails(String id){
        TvAdapter tvAdapter = new TvAdapter();
        similarRecycler.setAdapter(tvAdapter);

        TvService tvService = RetrofitClientInstance.getRetrofitInstance().create(TvService.class);
        Call<TvResponse> call = tvService.getTvShowDetails(id, KEY);
        call.enqueue(new Callback<TvResponse>() {
            @Override
            public void onResponse(Call<TvResponse> call, Response<TvResponse> response) {
                TvResponse tvResponse = response.body();
                Picasso.get()
                        .load(NetworkUtils.IMAGE_BASE_URL+tvResponse.getBackdrop_path())
                        .fit()
                        .into(backdrop);
                tagLine.setText(tvResponse.getTagline());
                title.setText(tvResponse.getName());
                year.setText(tvResponse.getFirst_air_date().split("-")[0]);
                genreName.setText(tvResponse.getGenres().get(0).getName());
                if (tvResponse.getEpisode_runtime() != null) {
                    int runtimeHours = tvResponse.getEpisode_runtime()[0] / 60;
                    int runtimeMins = tvResponse.getEpisode_runtime()[0] % 60;
                    StringBuilder builder = new StringBuilder();
                    builder.append(runtimeHours).append("h ").append(runtimeMins).append("m");
                    runtime.setText(builder.toString());
                }
                Picasso.get()
                        .load(NetworkUtils.IMAGE_BASE_URL+tvResponse.getPoster_path())
                        .fit()
                        .into(poster);
                overview.setText(tvResponse.getOverview());
                int ratePercent = Math.round(tvResponse.getVote_average())*10;
                ratingCount.setText(String.valueOf(ratePercent));
            }

            @Override
            public void onFailure(Call<TvResponse> call, Throwable t) {
                Log.e("DetailActivity",t.getMessage());
            }
        });

        call = tvService.getTvShowImages(id, BuildConfig.KEY);
        call.enqueue(new Callback<TvResponse>() {
            @Override
            public void onResponse(Call<TvResponse> call, Response<TvResponse> response) {
                ArrayList<String> media = new ArrayList<>();
                for(TvResponse tvResponse:response.body().getBackdrops()){
                    media.add(tvResponse.getFile_path());
                }
                for(TvResponse tvResponse:response.body().getPosters()){
                    media.add(tvResponse.getFile_path());
                }
                mediaAdapter.addMedia(media,false);
            }

            @Override
            public void onFailure(Call<TvResponse> call, Throwable t) {
                Log.e("DetailActivity",t.getMessage());
            }
        });

        call = tvService.getTvShowKeywords(id, BuildConfig.KEY);
        call.enqueue(new Callback<TvResponse>() {
            @Override
            public void onResponse(Call<TvResponse> call, Response<TvResponse> response) {
                List<String> keywords = new ArrayList<>();
                if (response.body().getKeywords() != null) {
                    for (TvResponse tvResponse : response.body().getKeywords()) {
                        keywords.add(tvResponse.getName());
                    }
                }
                if(keywords.size() > 0){
                    noKeyword.setVisibility(View.GONE);
                    keywordAdapter.addTexts(keywords);
                }
            }

            @Override
            public void onFailure(Call<TvResponse> call, Throwable t) {
                Log.e("DetailActivity",t.getMessage());
            }
        });

        call = tvService.getSimilarTvShows(id, BuildConfig.KEY, "1");
        call.enqueue(new Callback<TvResponse>() {
            @Override
            public void onResponse(Call<TvResponse> call, Response<TvResponse> response) {
                tvAdapter.addTvShows(response.body().getResults());
            }

            @Override
            public void onFailure(Call<TvResponse> call, Throwable t) {
                Log.e("DetailActivity",t.getMessage());
            }
        });

        Call<CelebrityResponse> creditCall = tvService.getTvShowCredits(id, BuildConfig.KEY);
        creditCall.enqueue(new Callback<CelebrityResponse>() {
            @Override
            public void onResponse(Call<CelebrityResponse> call, Response<CelebrityResponse> response) {
                celebrityAdapter.addCelebrities(response.body().getCast());
            }

            @Override
            public void onFailure(Call<CelebrityResponse> call, Throwable t) {
                Log.e("DetailActivity",t.getMessage());
            }
        });

    }

}