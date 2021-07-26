package dev.thec0dec8ter.tmdb.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import dev.thec0dec8ter.tmdb.DetailActivity;
import dev.thec0dec8ter.tmdb.R;
import dev.thec0dec8ter.tmdb.network.MovieResponse;

import static dev.thec0dec8ter.tmdb.network.NetworkUtils.IMAGE_BASE_URL;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>{

    private final ArrayList<MovieResponse> moviesList = new ArrayList<>();

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.bind(moviesList.get(position));
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder{
        ImageView poster;
        CardView ratingCard;
        TextView rating;
        TextView title;
        TextView year;
        ImageView info;

        View bottomSheet;
        ImageView bottomSheetPoster;
        TextView bottomSheetTitle;
        TextView bottomSheetYear;
        TextView bottomSheetGenre;
        TextView bottomSheetRuntime;
        TextView bottomSheetOverview;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            Context context = itemView.getContext();

            poster = itemView.findViewById(R.id.poster);
            ratingCard = itemView.findViewById(R.id.rating_card);
            rating = itemView.findViewById(R.id.rating);
            title = itemView.findViewById(R.id.title);
            year = itemView.findViewById(R.id.year);
            info = itemView.findViewById(R.id.info);

            bottomSheet = LayoutInflater.from(context).inflate(R.layout.bottom_sheet, null);
            bottomSheetPoster = bottomSheet.findViewById(R.id.poster);
            bottomSheetTitle = bottomSheet.findViewById(R.id.title);
            bottomSheetYear = bottomSheet.findViewById(R.id.year);
            bottomSheetGenre = bottomSheet.findViewById(R.id.genre);
            bottomSheetRuntime = bottomSheet.findViewById(R.id.runtime);
            bottomSheetOverview = bottomSheet.findViewById(R.id.overview);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), DetailActivity.class);
                    intent.putExtra("movie_id", moviesList.get(getAdapterPosition()).getId());
                    view.getContext().startActivity(intent);
                }
            });

            info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MovieResponse movieResponse = moviesList.get(getAdapterPosition());
                    Picasso.get().load(IMAGE_BASE_URL + movieResponse.getPoster_path()).fit().into(bottomSheetPoster);
                    bottomSheetTitle.setText(movieResponse.getTitle());
                    bottomSheetYear.setText(movieResponse.getRelease_date().split("-")[0]);
                    bottomSheetOverview.setText(movieResponse.getOverview());

                    BottomSheetDialog dialog = new BottomSheetDialog(context);
                    dialog.setContentView(bottomSheet);
                    dialog.show();
                }
            });
        }

        public void bind (MovieResponse movieResponse){
            Picasso.get()
                    .load(IMAGE_BASE_URL+movieResponse.getPoster_path())
                    .fit()
                    .into(poster);
            if(Math.round(movieResponse.getVote_average()) < 1){
                ratingCard.setVisibility(View.INVISIBLE);
            }else {
                int ratePercent = Math.round(movieResponse.getVote_average())*10;
                rating.setText(String.valueOf(ratePercent));
            }
            title.setText(movieResponse.getTitle());
            year.setText(movieResponse.getRelease_date().split("-")[0]);
        }

    }

    public void addMovies(ArrayList<MovieResponse> movieResponses){
        this.moviesList.addAll(movieResponses);
        notifyDataSetChanged();
    }

    public ArrayList<MovieResponse> getMoviesList() {
        return moviesList;
    }
}
