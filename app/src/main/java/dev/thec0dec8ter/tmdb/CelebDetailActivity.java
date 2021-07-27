package dev.thec0dec8ter.tmdb;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import dev.thec0dec8ter.tmdb.adapters.CelebrityAdapter;
import dev.thec0dec8ter.tmdb.network.CelebrityResponse;
import dev.thec0dec8ter.tmdb.network.CelebrityService;
import dev.thec0dec8ter.tmdb.network.NetworkUtils;
import dev.thec0dec8ter.tmdb.network.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static dev.thec0dec8ter.tmdb.BuildConfig.KEY;

public class CelebDetailActivity extends AppCompatActivity {

    ImageView poster;
    TextView celebName;
    TextView biography;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_celeb_detail);

        poster = findViewById(R.id.img_poster);
        celebName = findViewById(R.id.celeb_name);
        biography = findViewById(R.id.biography);

        String celebExtra = getIntent().getStringExtra("celeb_id");
        if(celebExtra != null) {
            getCelebDetails(celebExtra);
        }else {
            finish();
        }

    }

    private void getCelebDetails(String id){
        CelebrityService celebService = RetrofitClientInstance.getRetrofitInstance().create(CelebrityService.class);
        Call<CelebrityResponse> call = celebService.getCelebDetails(id, KEY);
        call.enqueue(new Callback<CelebrityResponse>() {
            @Override
            public void onResponse(Call<CelebrityResponse> call, Response<CelebrityResponse> response) {
                CelebrityResponse celebResponse = response.body();
                Picasso.get()
                        .load(NetworkUtils.IMAGE_BASE_URL+celebResponse.getProfile_path())
                        .fit()
                        .into(poster);
                celebName.setText(celebResponse.getName());
                biography.setText(celebResponse.getBiography().split("-")[0]);
            }

            @Override
            public void onFailure(Call<CelebrityResponse> call, Throwable t) {
                Log.e("CelebDetailActivity: ",t.getMessage());
            }
        });

        call = celebService.getCelebImages(id, BuildConfig.KEY);
        call.enqueue(new Callback<CelebrityResponse>() {
            @Override
            public void onResponse(Call<CelebrityResponse> call, Response<CelebrityResponse> response) {

            }

            @Override
            public void onFailure(Call<CelebrityResponse> call, Throwable t) {
                Log.e("CelebDetailActivity: ",t.getMessage());
            }
        });

    }
}