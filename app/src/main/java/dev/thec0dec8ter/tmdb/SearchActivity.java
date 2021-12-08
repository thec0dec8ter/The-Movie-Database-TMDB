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

import dev.thec0dec8ter.tmdb.adapters.SearchPagerAdapter;

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

        searchPagerAdapter = new SearchPagerAdapter(this);
        viewPager.setAdapter(searchPagerAdapter);
        viewPager.setUserInputEnabled(false);

        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        editQuery.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    Bundle args = new Bundle();
                    args.putString("query", editQuery.getText().toString());
                    viewPager.setCurrentItem(0);
                    searchPagerAdapter.getCurrentFragment(0).setArguments(args);
                    searchPagerAdapter.getCurrentFragment(0).onResume();
                    return true;
                }
                return false;
            }
        });

        mediator = new TabLayoutMediator(tabLayout, viewPager,
                true,false, (tab, position) -> {
            switch (position){
                case 0:
                    tab.setText("Recent searches");
                    viewPager.setCurrentItem(position);
                    break;
                case 1:
                    tab.setText("Find Movies & Tv Shows");
                    break;
            }
        });
        mediator.attach();

    }

}