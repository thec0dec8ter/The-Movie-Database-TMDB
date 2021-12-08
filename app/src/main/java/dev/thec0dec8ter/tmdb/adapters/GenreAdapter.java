package dev.thec0dec8ter.tmdb.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dev.thec0dec8ter.tmdb.R;
import dev.thec0dec8ter.tmdb.models.Genre;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.GenreViewHolder> {

    public final ArrayList<Genre> genres = new ArrayList<>();

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
        }

        public  void bind(int pos){
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
}
