package dev.thec0dec8ter.tmdb.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import dev.thec0dec8ter.tmdb.R;

public class KeywordAdapter extends RecyclerView.Adapter<KeywordAdapter.KeywordViewHolder> {

    private final ArrayList<String> texts = new ArrayList<>();

    public KeywordAdapter(){

    }

    public KeywordAdapter(List<String> texts){
        this.texts.addAll(texts);
    }

    @NonNull
    @Override
    public KeywordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_keyword, parent, false);
        return new KeywordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KeywordViewHolder holder, int position) {
        holder.bind(texts.get(position));
    }

    @Override
    public int getItemCount() {
        return texts.size();
    }

    public class KeywordViewHolder extends RecyclerView.ViewHolder{
        TextView keyword;

        public KeywordViewHolder(@NonNull View itemView) {
            super(itemView);
             keyword = itemView.findViewById(R.id.keyword_txt);
        }

        public void bind(String text){
            keyword.setText(text);
        }
    }

    public void addTexts(List<String> texts){
        this.texts.addAll(texts);
        notifyDataSetChanged();
    }
}
