package dev.thec0dec8ter.tmdb.adapters;

import android.content.Intent;
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

import dev.thec0dec8ter.tmdb.DetailActivity;
import dev.thec0dec8ter.tmdb.R;
import dev.thec0dec8ter.tmdb.models.Movie;
import dev.thec0dec8ter.tmdb.models.TvShow;

import static dev.thec0dec8ter.tmdb.network.RetrofitClientInstance.IMAGE_BASE_URL;

public class ShowAdapter extends RecyclerView.Adapter<ShowAdapter.ShowViewHolder>{

    private final ArrayList<Movie> moviesList = new ArrayList<>();
    private final ArrayList<TvShow> tvShowList = new ArrayList<>();

    @NonNull
    @Override
    public ShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_show, parent, false);
        return new ShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowViewHolder holder, int position) {
        if(moviesList.size() > 0){
            holder.bind(moviesList.get(position));
        }else if((tvShowList.size() > 0)) {
            holder.bind(tvShowList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        if(moviesList.size() > 0){
            return moviesList.size();
        }else {
            return tvShowList.size();
        }
    }

    public class ShowViewHolder extends RecyclerView.ViewHolder{
        ImageView imgPoster;
        CardView ratingCard;
        TextView ratingCount;
        TextView title;
        TextView releaseYear;
        TextView genre;

        public ShowViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPoster = itemView.findViewById(R.id.poster);
            ratingCard = itemView.findViewById(R.id.rating_card);
            ratingCount = itemView.findViewById(R.id.rating_count);
            title = itemView.findViewById(R.id.title);
            releaseYear = itemView.findViewById(R.id.release_year);
            genre = itemView.findViewById(R.id.genre);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), DetailActivity.class);
                    if(moviesList.size() > 0){
                        intent.putExtra("movie_id", moviesList.get(getAdapterPosition()).getId());
                    }else if(tvShowList.size() > 0){
                        intent.putExtra("tv_id", tvShowList.get(getAdapterPosition()).getId());
                    }
                    view.getContext().startActivity(intent);
                }
            });

        }

        public void bind (Movie movie){
            Picasso.get()
                    .load(IMAGE_BASE_URL+ movie.getPoster_path())
                    .fit()
                    .into(imgPoster);
            if(Math.round(movie.getVote_average()) < 1){
                ratingCard.setVisibility(View.INVISIBLE);
            }else {
                int ratePercent = Math.round(movie.getVote_average()) * 10;
                ratingCount.setText(String.valueOf(ratePercent));
            }
            title.setText(movie.getTitle());
            releaseYear.setText(movie.getRelease_date().split("-")[0]);
            if(movie.getGenres().size() > 0){
                genre.setText(movie.getGenres().get(0).getName());
            }

        }

        public void bind (TvShow tvShow){
            Picasso.get()
                    .load(IMAGE_BASE_URL+ tvShow.getPoster_path())
                    .fit()
                    .into(imgPoster);
            if(Math.round(tvShow.getVote_average()) < 1){
                ratingCard.setVisibility(View.INVISIBLE);
            }else {
                int ratePercent = Math.round(tvShow.getVote_average()) * 10;
                ratingCount.setText(String.valueOf(ratePercent));
            }
            title.setText(tvShow.getName());
            releaseYear.setText(tvShow.getFirst_air_date().split("-")[0]);
            if(tvShow.getGenres().size() > 0){
                genre.setText(tvShow.getGenres().get(0).getName());
            }

        }

    }

    public void addMovie(Movie movie){
        if(movie.getVote_average() > 0){
            this.moviesList.add(movie);
            notifyDataSetChanged();
        }
    }

    public void addTvShow(TvShow tvShow){
        if(tvShow.getVote_average() > 0){
            this.tvShowList.add(tvShow);
            notifyDataSetChanged();
        }
    }

    public void addTvShows(ArrayList<TvShow> tvShowList){
        for(TvShow tvShow:tvShowList){
            if(tvShow.getVote_average() > 0){
                this.tvShowList.add(tvShow);
            }
        }
        notifyDataSetChanged();
    }

    public void addMovies(ArrayList<Movie> moviesList){
        for(Movie movie:moviesList){
            if(movie.getVote_average() > 0){
                this.moviesList.add(movie);
            }
        }
        notifyDataSetChanged();
    }

    public ArrayList<Movie> getMoviesList() {
        return moviesList;
    }

    public ArrayList<TvShow> getTvShowList() {
        return tvShowList;
    }
}
