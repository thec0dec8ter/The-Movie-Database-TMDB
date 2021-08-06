package dev.thec0dec8ter.tmdb;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import dev.thec0dec8ter.tmdb.adapters.MovieAdapter;
import dev.thec0dec8ter.tmdb.adapters.SearchPagerAdapter;
import dev.thec0dec8ter.tmdb.models.Movie;
import dev.thec0dec8ter.tmdb.network.RetrofitClientInstance;
import dev.thec0dec8ter.tmdb.network.SearchService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static dev.thec0dec8ter.tmdb.BuildConfig.KEY;

public class SearchActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageView arrowBack;
    private EditText editQuery;
    private TabLayout tabLayout;
    private ViewPager2 viewPager;

    private SearchPagerAdapter searchPagerAdapter;
    private TabLayoutMediator mediator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        toolbar = findViewById(R.id.toolbar);
        arrowBack = findViewById(R.id.arrow_back);
        editQuery = findViewById(R.id.edit_query);
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);

        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                navController.navigate(R.id.action_search_to_main);
                finish();
            }
        });

        editQuery.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    viewPager.setCurrentItem(0);
                    searchForMovies(editQuery.getText().toString());
                    return true;
                }
                return false;
            }
        });

        searchPagerAdapter = new SearchPagerAdapter(this);
        viewPager.setAdapter(searchPagerAdapter);
        viewPager.setUserInputEnabled(false);

        mediator = new TabLayoutMediator(tabLayout, viewPager,
                true,false, (tab, position) -> {
            switch (position){
                case 0:
                    tab.setText("Recent");
                    viewPager.setCurrentItem(position);
                    break;
                case 1:
                    tab.setText("Find Movies & Tv Shows");
                    break;
            }

        });
        mediator.attach();

    }

    private void searchForMovies(String query){
        MovieAdapter movieAdapter = new MovieAdapter();
        SearchService searchService = RetrofitClientInstance.getRetrofitInstance().create(SearchService.class);
        Call<Movie> call = searchService.searchForMovies(KEY, query, "1");
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                movieAdapter.addMovies(response.body().getResults());
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

            }
        });
    }

}