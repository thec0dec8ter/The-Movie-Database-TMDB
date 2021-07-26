package dev.thec0dec8ter.tmdb.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

import dev.thec0dec8ter.tmdb.DetailActivity;
import dev.thec0dec8ter.tmdb.R;
import dev.thec0dec8ter.tmdb.network.CelebrityResponse;
import dev.thec0dec8ter.tmdb.network.NetworkUtils;

public class CelebrityAdapter extends RecyclerView.Adapter<CelebrityAdapter.CelebrityViewHolder> {

    private final ArrayList<CelebrityResponse> celebrities = new ArrayList<>();

    @NonNull
    @Override
    public CelebrityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_celebrity, parent, false);
        return new CelebrityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CelebrityViewHolder holder, int position) {
        holder.bind(celebrities.get(position));
    }

    @Override
    public int getItemCount() {
        return celebrities.size();
    }

    public class CelebrityViewHolder extends RecyclerView.ViewHolder{
        ImageView poster;
        TextView celebName;
        TextView knownFor;
        TextView characterName;

        public CelebrityViewHolder(@NonNull View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.poster);
            celebName = itemView.findViewById(R.id.celeb_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Intent intent = new Intent(view.getContext(), DetailActivity.class);
//                    intent.putExtra("movie_id", moviesList.get(getAdapterPosition()).getId());
//                    view.getContext().startActivity(intent);
                }
            });
        }

        public  void bind(CelebrityResponse celebrity){
            Picasso.get()
                    .load(NetworkUtils.IMAGE_BASE_URL + celebrity.getProfile_path())
                    .fit()
                    .into(poster);
            if(celebrity.getOriginal_name() == null){
                celebName.setText(celebrity.getName());
            }else {
                celebName.setText(celebrity.getOriginal_name());
            }
        }
    }

    public void addCelebrities(ArrayList<CelebrityResponse> celebrities){
        this.celebrities.addAll(celebrities);
        notifyDataSetChanged();
    }
}
