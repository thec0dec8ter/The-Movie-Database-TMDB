package dev.thec0dec8ter.tmdb;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    private Toolbar toolbar;
    private LinearLayout back;
    private ShapeableImageView imgProfile;
    private TextView txtName;
    private TextView txtWatchlistCount;
    private TextView txtRecent;
    private Button btnWatchlist;
    private Button btnSignOut;
    private RecyclerView watchlistRecycler;
    private RecyclerView recentRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(null);

        back = findViewById(R.id.back);
        imgProfile = findViewById(R.id.img_profile);
        txtName = findViewById(R.id.txt_name);
        txtWatchlistCount = findViewById(R.id.txt_watchlist_count);
        btnWatchlist = findViewById(R.id.btn_watchlist);
        btnSignOut = findViewById(R.id.btn_sign_out);
        watchlistRecycler = findViewById(R.id.watchlist_recycler);
        recentRecycler = findViewById(R.id.recent_recycler);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}