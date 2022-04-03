package dev.thec0dec8ter.tmdb;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import org.jetbrains.annotations.NotNull;

import dev.thec0dec8ter.tmdb.adapters.ImageAdapter;

public class SeeAllFragment extends Fragment {

    private static final String ARG_TITLE = "fragment_title";

    private String title;

    private Toolbar toolbar;
    private RecyclerView posterRecycler;

    private ImageAdapter posterAdapter;

    public SeeAllFragment() {
        // Required empty public constructor
    }

    public static SeeAllFragment newInstance(String title) {
        SeeAllFragment fragment = new SeeAllFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(ARG_TITLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_see_all, container, false);
        toolbar = root.findViewById(R.id.toolbar);
        posterRecycler = root.findViewById(R.id.poster_recycler);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        posterAdapter = new ImageAdapter();
        posterRecycler.setAdapter(posterAdapter);
    }
}