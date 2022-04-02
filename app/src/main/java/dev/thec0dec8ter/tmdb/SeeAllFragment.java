package dev.thec0dec8ter.tmdb;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SeeAllFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    private String mParam1;

    public SeeAllFragment() {
        // Required empty public constructor
    }

    public static SeeAllFragment newInstance(String param1) {
        SeeAllFragment fragment = new SeeAllFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_see_all, container, false);
        return root;
    }
}