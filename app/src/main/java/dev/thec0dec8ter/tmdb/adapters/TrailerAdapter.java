package dev.thec0dec8ter.tmdb.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;

import dev.thec0dec8ter.tmdb.R;
import dev.thec0dec8ter.tmdb.network.MovieResponse;
import dev.thec0dec8ter.tmdb.network.NetworkUtils;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.TrailerViewHolder> {

    private final ArrayList<MovieResponse> trailers = new ArrayList<>();

    @NonNull
    @Override
    public TrailerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trailer_home, parent, false);
        return new TrailerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class TrailerViewHolder extends RecyclerView.ViewHolder{
        ImageView poster;
        CardView play;
        TextView title;
        TextView trailerName;

        public TrailerViewHolder(@NonNull View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.poster);
            play = itemView.findViewById(R.id.play_btn_card);
            title = itemView.findViewById(R.id.title);
            trailerName = itemView.findViewById(R.id.trailer_name);

            play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }

        public void bind(MovieResponse movieResponse){
            Picasso.get()
                    .load(NetworkUtils.IMAGE_BASE_URL + movieResponse.getBackdrop_path())
                    .fit()
                    .into(poster);
            title.setText(movieResponse.getTitle());
            trailerName.setText(movieResponse.getResults().get(0).getName());
        }
    }

    public void addTrailers(ArrayList<MovieResponse> trailers){
        this.trailers.addAll(trailers);
        Collections.shuffle(trailers);
        notifyDataSetChanged();
    }

    public void addTrailer(MovieResponse movieResponse){
        trailers.add(movieResponse);
        Collections.shuffle(trailers);
        notifyDataSetChanged();
    }
}
