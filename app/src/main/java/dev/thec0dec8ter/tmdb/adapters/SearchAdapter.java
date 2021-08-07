package dev.thec0dec8ter.tmdb.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import dev.thec0dec8ter.tmdb.R;
import dev.thec0dec8ter.tmdb.models.Search;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchviewHolder>{

    private final ArrayList<Search> results = new ArrayList<>();

    @NonNull
    @NotNull
    @Override
    public SearchviewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search, parent, false);
       return new SearchviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull SearchAdapter.SearchviewHolder holder, int position) {
        holder.bind(results.get(position));
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class SearchviewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView ratingCount;
        TextView mediaType;
        TextView year;
        TextView genreName;

        public SearchviewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            ratingCount = itemView.findViewById(R.id.rating_count);
            mediaType = itemView.findViewById(R.id.media_type);
            year = itemView.findViewById(R.id.year);
            genreName = itemView.findViewById(R.id.genre_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        public void bind(Search search){
            title.setTag(search.getId());
            if(search.getMedia_type().equals("person")){
                title.setText(search.getName());
            }else if(search.getMedia_type().equals("movie")){
                title.setText(search.getTitle());
            }else {
                title.setText(search.getName());
            }

        }
    }

    public void addResult(Search result){
        this.results.add(result);
        notifyDataSetChanged();
    }

    public void addResults(ArrayList<Search> results){
        this.results.addAll(results);
        notifyDataSetChanged();
    }
}
