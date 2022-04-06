package dev.thec0dec8ter.tmdb;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class SeeAllFragment extends Fragment {

    private static final String ARG_TITLE = "fragment_title";

    private String title;

    private ImageView imgArrowBack;
    private TextView txtTitle;
    private RecyclerView posterRecycler;

    private RecyclerView.Adapter adapter;

    public SeeAllFragment() {
        // Required empty public constructor
    }

    public SeeAllFragment(String title, RecyclerView.Adapter adapter) {
        this.title = title;
        this.adapter = adapter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_see_all, container, false);
        imgArrowBack = root.findViewById(R.id.img_arrow_back);
        txtTitle = root.findViewById(R.id.txt_title);
        posterRecycler = root.findViewById(R.id.poster_recycler);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtTitle.setText(title);
        posterRecycler.setAdapter(adapter);
        
        imgArrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeFragment();
            }
        });
    }

    public void closeFragment(){
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .remove(SeeAllFragment.this)
                .commit();
    }



}