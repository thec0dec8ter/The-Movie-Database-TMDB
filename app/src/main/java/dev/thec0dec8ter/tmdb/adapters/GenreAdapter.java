package dev.thec0dec8ter.tmdb.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import dev.thec0dec8ter.tmdb.R;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.GenreViewHolder> {

    private final ArrayList<String> genreIds = new ArrayList<>();
    private final ArrayList<String> genreNames = new ArrayList<>();

    @NonNull
    @Override
    public GenreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_genre, parent, false);
        return new GenreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GenreViewHolder holder, int position) {
        holder.bind(genreNames.get(position));
    }

    @Override
    public int getItemCount() {
        if(genreNames.size() < 14){
            return genreNames.size();
        }else {
            return 14;
        }
    }

    public class GenreViewHolder extends RecyclerView.ViewHolder{
        ImageView genrePoster;
        TextView genreName;

        public GenreViewHolder(@NonNull View itemView) {
            super(itemView);
            genrePoster = itemView.findViewById(R.id.genre_poster);
            genreName = itemView.findViewById(R.id.genre_name);
        }

        public  void bind(String genre){
            Picasso.get()
                    .load(genre)
                    .fit()
                    .into(genrePoster);
            genreName.setText(genre);
        }
    }

    public void addGenre(String id,String genreName){
        genreIds.add(id);
        genreNames.add(genreName);
        notifyDataSetChanged();
    }
}
