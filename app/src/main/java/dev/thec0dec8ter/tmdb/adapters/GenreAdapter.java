package dev.thec0dec8ter.tmdb.adapters;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

import dev.thec0dec8ter.tmdb.R;
import dev.thec0dec8ter.tmdb.SearchActivity;
import dev.thec0dec8ter.tmdb.models.Genre;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.GenreViewHolder> {

    public final String type;
    public final ArrayList<Genre> genres = new ArrayList<>();

    public GenreAdapter(String type){
        this.type = type;
    }

    @NonNull
    @Override
    public GenreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_genre, parent, false);
        return new GenreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GenreViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        if(genres.size() < 14){
            return genres.size();
        }else {
            return 14;
        }
    }

    public class GenreViewHolder extends RecyclerView.ViewHolder{
        TextView genreName;

        public GenreViewHolder(@NonNull View itemView) {
            super(itemView);
            genreName = itemView.findViewById(R.id.genre_name);

            genreName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HashMap<String, String> queryMap = new HashMap<>();
                    queryMap.put("with_genres", String.valueOf(genreName.getTag()));

                    Bundle args = new Bundle();
                    Intent intent = new Intent(v.getContext(), SearchActivity.class);
                    if(type.equalsIgnoreCase("movie")){
                        args.putSerializable("discover_movie", queryMap);
                    }else{
                        args.putSerializable("discover_tv", queryMap);
                    }

                    intent.putExtras(args);
                    v.getContext().startActivity(intent);
                }
            });
        }

        public  void bind(int pos){
            genreName.setTag(genres.get(pos).getId());
            genreName.setText(genres.get(pos).getName());
        }
    }

    public void addGenre(Genre genre){
        genres.add(genre);
        notifyDataSetChanged();
    }

    public Genre getGenreById(int id){
        for (Genre genre:genres){
            if(id == genre.getId()){
                return genre;
            }
        }
        return null;
    }

    public String[] getGenreNames(){
        String[] genreNames = new String[genres.size()];
        for(int i = 0; i < genres.size(); i++){
            genreNames[i] = genres.get(i).getName();
        }
        return genreNames;
    }

    public String[] getGenreIds(){
        String[] genreIds = new String[genres.size()];
        for(int i = 0; i < genres.size(); i++){
            genreIds[i] = String.valueOf(genres.get(i).getId());
        }
        return genreIds;
    }
}
