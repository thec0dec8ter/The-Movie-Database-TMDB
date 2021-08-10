package dev.thec0dec8ter.tmdb.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dev.thec0dec8ter.tmdb.R;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.GenreViewHolder> {

    public final ArrayList<String> ids = new ArrayList<>();
    public final ArrayList<String> names = new ArrayList<>();

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
        if(ids.size() < 14){
            return names.size();
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
            genreName.setText(names.get(pos));
        }
    }

    public void addGenre(String id,String genreName){
        ids.add(id);
        names.add(genreName);
        notifyDataSetChanged();
    }

    public String getGenreById(int id){
        for(int i = 0; i < ids.size(); i++){
            if(ids.get(i).equals(String.valueOf(id))){
                return names.get(i);
            }
        }
        return null;
    }
}
