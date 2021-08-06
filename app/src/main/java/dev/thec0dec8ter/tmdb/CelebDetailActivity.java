package dev.thec0dec8ter.tmdb;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.Calendar;

import dev.thec0dec8ter.tmdb.adapters.ImageAdapter;
import dev.thec0dec8ter.tmdb.models.Celebrity;
import dev.thec0dec8ter.tmdb.network.CelebrityService;
import dev.thec0dec8ter.tmdb.network.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static dev.thec0dec8ter.tmdb.BuildConfig.KEY;
import static dev.thec0dec8ter.tmdb.network.RetrofitClientInstance.IMAGE_BASE_URL;

public class CelebDetailActivity extends AppCompatActivity {

    ImageView poster;
    TextView ratingCount;
    TextView celebName;
    TextView celebAge;
    TextView placeOfBirth;
    TextView biography;
    RecyclerView relatedMovies;
    RecyclerView taggedImages;
    RecyclerView relatedTvShows;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_celeb_detail);

        poster = findViewById(R.id.img_poster);
        ratingCount = findViewById(R.id.rating_count);
        celebName = findViewById(R.id.celeb_name);
        celebAge = findViewById(R.id.age);
        placeOfBirth = findViewById(R.id.place_of_birth);
        biography = findViewById(R.id.biography);
        relatedMovies = findViewById(R.id.related_movie_recycler);
        taggedImages = findViewById(R.id.media_recycler);
        relatedTvShows = findViewById(R.id.related_tv_recycler);

        String celebExtra = getIntent().getStringExtra("celeb_id");
        if(celebExtra != null) {
            getCelebDetails(celebExtra);
        }else {
            finish();
        }

    }

    private void getCelebDetails(String id){
        CelebrityService celebService = RetrofitClientInstance.getRetrofitInstance().create(CelebrityService.class);
        Call<Celebrity> call = celebService.getCelebDetails(id, KEY);
        call.enqueue(new Callback<Celebrity>() {
            @Override
            public void onResponse(Call<Celebrity> call, Response<Celebrity> response) {
                Celebrity celebResponse = response.body();
                Picasso.get()
                        .load(IMAGE_BASE_URL+celebResponse.getProfile_path())
                        .fit()
                        .into(poster);
                float rating = celebResponse.getPopularity();
                ratingCount.setText(String.valueOf(rating));
                celebName.setText(Html.fromHtml("<b>Name: </b>" + celebResponse.getName()));
                int age = Calendar.getInstance().get(Calendar.YEAR) - Integer.parseInt(celebResponse.getBirthday().split("-")[0]);
                celebAge.setText(Html.fromHtml("<b>Age: </b>"+age));
                placeOfBirth.setText(Html.fromHtml("<b>Born in: </b>"+celebResponse.getPlace_of_birth()));
                biography.setText(celebResponse.getBiography().split("-")[0]);
            }

            @Override
            public void onFailure(Call<Celebrity> call, Throwable t) {
                Log.e("CelebDetailActivity: ",t.getMessage());
            }
        });

        call = celebService.getMovieCredits(id, BuildConfig.KEY);
        call.enqueue(new Callback<Celebrity>() {
            @Override
            public void onResponse(Call<Celebrity> call, Response<Celebrity> response) {

            }

            @Override
            public void onFailure(Call<Celebrity> call, Throwable t) {
                Log.e("CelebDetailActivity: ",t.getMessage());
            }
        });

        call = celebService.getTaggedImages(id, BuildConfig.KEY);
        call.enqueue(new Callback<Celebrity>() {
            @Override
            public void onResponse(Call<Celebrity> call, Response<Celebrity> response) {
                Celebrity celebResponse = response.body();
                ImageAdapter mediaAdapter = new ImageAdapter();
                for(Celebrity c:celebResponse.getResults()){
                    mediaAdapter.addImage(c.getFile_path());
                }
                taggedImages.setAdapter(mediaAdapter);
            }

            @Override
            public void onFailure(Call<Celebrity> call, Throwable t) {
                Log.e("CelebDetailActivity: ",t.getMessage());
            }
        });
    }

}