package dev.thec0dec8ter.tmdb.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import dev.thec0dec8ter.tmdb.BuildConfig;
import dev.thec0dec8ter.tmdb.R;
import dev.thec0dec8ter.tmdb.adapters.CelebrityAdapter;
import dev.thec0dec8ter.tmdb.models.Celebrity;
import dev.thec0dec8ter.tmdb.network.CelebrityService;
import dev.thec0dec8ter.tmdb.network.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CelebritiesFragment extends Fragment {

    private RecyclerView popularRecycler;


    private CelebrityAdapter celebrityAdapter;

    private CelebrityService celebrityService;
    private Call<Celebrity> call;

    public CelebritiesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        celebrityService = RetrofitClientInstance.getRetrofitInstance().create(CelebrityService.class);
        celebrityAdapter = new CelebrityAdapter();

        getPopularCelebrities("1");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_celebrities, container, false);
        popularRecycler = view.findViewById(R.id.popular_recycler);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        popularRecycler.setAdapter(celebrityAdapter);
    }

    private void getPopularCelebrities(String page){
        call = celebrityService.getPopularCelebrities(BuildConfig.KEY, page);
        call.enqueue(new Callback<Celebrity>() {
            @Override
            public void onResponse(Call<Celebrity> call, Response<Celebrity> response) {
                celebrityAdapter.addCelebrities(response.body().getResults());
            }

            @Override
            public void onFailure(Call<Celebrity> call, Throwable t) {
                Log.e("Error",t.getMessage());
            }
        });
    }

}