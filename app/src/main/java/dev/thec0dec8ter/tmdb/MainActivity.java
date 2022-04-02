package dev.thec0dec8ter.tmdb;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import dev.thec0dec8ter.tmdb.adapters.ViewPagerAdapter;
import dev.thec0dec8ter.tmdb.ui.main.HomeFragment;

public class MainActivity extends AppCompatActivity {


    //TODO: Implement disable "See All" button
    //TODO: Create see All fragment
    //TODO: Add progress bar to search fragment
    //TODO: Add icons to settings
    //TODO; Make genres clickable
    //TODO: change app icon

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager2 viewPager;

    private ViewPagerAdapter viewPagerAdapter;
    private TabLayoutMediator mediator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        getFragmentManager()
                .beginTransaction()
                .add(R.id.view_pager, new SeeAllFragment())
                .commit();

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);

        viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setUserInputEnabled(false);

        mediator = new TabLayoutMediator(tabLayout, viewPager,
                true,false, (tab, position) -> {
            switch (position){
                case 0:
                    tab.setText("Home");
                    viewPager.setCurrentItem(position);
                    break;
                case 1:
                    tab.setText("Movies");
                    break;
                case 2:
                    tab.setText("Tv Shows");
                    break;
                case 3:
                    tab.setText("Celebrities");
                    break;
            }

        });
        mediator.attach();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.search:
                intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
                return true;
            case R.id.profile:
                intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}