package dev.thec0dec8ter.tmdb.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import dev.thec0dec8ter.tmdb.R;

public class KeywordAdapter extends RecyclerView.Adapter<KeywordAdapter.KeywordViewHolder> {

    private final ArrayList<String> keywords = new ArrayList<>();

    public KeywordAdapter(){

    }

    public KeywordAdapter(boolean jk, List<String> h){

    }

    @NonNull
    @NotNull
    @Override
    public KeywordViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_keyword, parent, false);
        return new KeywordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull KeywordAdapter.KeywordViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 14;
    }

    public class KeywordViewHolder extends RecyclerView.ViewHolder{
        TextView keyword;

        public KeywordViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            keyword = itemView.findViewById(R.id.txt_keyword);

            keyword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
