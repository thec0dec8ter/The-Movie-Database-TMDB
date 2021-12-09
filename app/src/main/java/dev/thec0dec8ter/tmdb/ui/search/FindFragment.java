package dev.thec0dec8ter.tmdb.ui.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import dev.thec0dec8ter.tmdb.R;
import dev.thec0dec8ter.tmdb.adapters.KeywordAdapter;
import dev.thec0dec8ter.tmdb.adapters.SearchPagerAdapter;

public class FindFragment extends Fragment {

    private String searchQuery = "";

    private TextView btnClear;
    private Button btnSeeResult;

    private RecyclerView typeRecycler;
    private RecyclerView runtimeRecycler;
    private RecyclerView ratingRecycler;
    private RecyclerView genreRecycler;
    private RecyclerView decadeRecycler;
    private RecyclerView voteRecycler;
    private RecyclerView languageRecycler;


    private KeywordAdapter typeAdapter;
    private KeywordAdapter runtimeAdapter;
    private KeywordAdapter ratingAdapter;
    private KeywordAdapter genreAdapter;
    private KeywordAdapter decadeAdapter;
    private KeywordAdapter voteAdapter;
    private KeywordAdapter languageAdapter;

    public FindFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        typeAdapter = new KeywordAdapter(new String[]{"Movies", "Tv Shows"});
        runtimeAdapter = new KeywordAdapter(getResources().getStringArray(R.array.runtime));
        ratingAdapter = new KeywordAdapter(getResources().getStringArray(R.array.tmdb_rating));
        genreAdapter = new KeywordAdapter(getResources().getStringArray(R.array.popular_genres));
        decadeAdapter = new KeywordAdapter(getResources().getStringArray(R.array.decades));
        voteAdapter = new KeywordAdapter(getResources().getStringArray(R.array.total_votes));
        languageAdapter = new KeywordAdapter(getResources().getStringArray(R.array.country));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find, container, false);
        btnClear = view.findViewById(R.id.btn_clear);
        btnSeeResult = view.findViewById(R.id.btn_see_result);
        typeRecycler = view.findViewById(R.id.type_recycler);
        runtimeRecycler = view.findViewById(R.id.runtime_recycler);
        ratingRecycler = view.findViewById(R.id.tmdb_rating_recycler);
        genreRecycler = view.findViewById(R.id.genre_recycler);
        decadeRecycler = view.findViewById(R.id.decade_recycler);
        voteRecycler = view.findViewById(R.id.vote_recycler);
        languageRecycler = view.findViewById(R.id.language_recycler);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        typeRecycler.setAdapter(typeAdapter);
        runtimeRecycler.setAdapter(runtimeAdapter);
        ratingRecycler.setAdapter(ratingAdapter);
        genreRecycler.setAdapter(genreAdapter);
        decadeRecycler.setAdapter(decadeAdapter);
        voteRecycler.setAdapter(voteAdapter);
        languageRecycler.setAdapter(languageAdapter);

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAll();
            }
        });

        btnSeeResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putString("query", searchQuery);

                ViewPager2 viewPager = getActivity().findViewById(R.id.view_pager);
                SearchPagerAdapter searchPagerAdapter = (SearchPagerAdapter) viewPager.getAdapter();
                viewPager.setCurrentItem(0);
                searchPagerAdapter.getCurrentFragment(0).setArguments(args);
                searchPagerAdapter.getCurrentFragment(0).onResume();

            }
        });
    }

    private void clearSelection(RecyclerView recycler){
//        int total_count = recycler.getAdapter().getItemCount();
//        for(int i = 0; i < total_count; i++){
//            Log.e("KKKK", String.valueOf(recycler.findViewHolderForLayoutPosition(i) != null));
//            TextView textView =
//                    recycler.findViewHolderForAdapterPosition(i)
//                    .itemView
//                    .findViewById(R.id.txt_keyword);
//            KeywordAdapter.removeTextBackground(textView);
//        }
    }


    private void clearAll(){
        clearSelection(typeRecycler);
        clearSelection(runtimeRecycler);
        clearSelection(ratingRecycler);
        clearSelection(genreRecycler);
        clearSelection(decadeRecycler);
        clearSelection(voteRecycler);
        clearSelection(languageRecycler);
    }




//    <item>en - US</item>
//        <item>fr - CA</item>
//        <item>ge - DE</item>
//        <item>fr - FR</item>
//        <item>ma - NZ</item>
//        <item>en - AU</item>
//        <item>hi - IN</item>

}