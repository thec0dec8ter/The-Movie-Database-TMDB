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

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import dev.thec0dec8ter.tmdb.DetailActivity;
import dev.thec0dec8ter.tmdb.R;
import dev.thec0dec8ter.tmdb.models.TvShow;

import static dev.thec0dec8ter.tmdb.network.RetrofitClientInstance.IMAGE_BASE_URL;


public class TvAdapter extends RecyclerView.Adapter<TvAdapter.TvViewHolder> {

    private final ArrayList<TvShow> tvList = new ArrayList<>();

    public TvAdapter() {

    }

    @NonNull
    @Override
    public TvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tv, parent, false);
        return new TvViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvViewHolder holder, int position) {
        holder.bind(tvList.get(position));
    }

    @Override
    public int getItemCount() {
        return tvList.size();
    }

    public class TvViewHolder extends RecyclerView.ViewHolder {
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

        public TvViewHolder(@NonNull View itemView) {
            super(itemView);
            Context context = itemView.getContext();

            poster = itemView.findViewById(R.id.poster);
            ratingCard = itemView.findViewById(R.id.rating_card);
            rating = itemView.findViewById(R.id.rating);
            title = itemView.findViewById(R.id.title);
            year = itemView.findViewById(R.id.year);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), DetailActivity.class);
                    intent.putExtra("tv_id", tvList.get(getAdapterPosition()).getId());
                    view.getContext().startActivity(intent);
                }
            });
        }

        public void bind(TvShow tvShow) {
            Picasso.get().load(IMAGE_BASE_URL + tvShow.getPoster_path()).fit().into(poster);
            if(Math.round(tvShow.getVote_average()) < 1){
                ratingCard.setVisibility(View.INVISIBLE);
            }else {
                int ratePercent = Math.round(tvShow.getVote_average())*10;
                rating.setText(String.valueOf(ratePercent));
            }
            title.setText(tvShow.getName());
//            year.setText(tvShow.getFirst_air_date().split("-")[0]);

        }
    }

    public void addTvShows(ArrayList<TvShow> tvList) {
        this.tvList.addAll(tvList);
        notifyDataSetChanged();
    }
}

