package dev.thec0dec8ter.tmdb.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import dev.thec0dec8ter.tmdb.CelebDetailActivity;
import dev.thec0dec8ter.tmdb.DetailActivity;
import dev.thec0dec8ter.tmdb.R;
import dev.thec0dec8ter.tmdb.models.Search;

import static dev.thec0dec8ter.tmdb.network.RetrofitClientInstance.IMAGE_BASE_URL;

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
        ImageView poster;
        TextView title;
        TextView release_info;
        TextView mediaType;
        TextView ratingCount;
        TextView synopsis;

        public SearchviewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.poster);
            ratingCount = itemView.findViewById(R.id.rating_count);
            title = itemView.findViewById(R.id.title);
            release_info = itemView.findViewById(R.id.release_info);
            mediaType = itemView.findViewById(R.id.media_type);
            synopsis = itemView.findViewById(R.id.synopsis);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent;
                    if(mediaType.getText().toString().equalsIgnoreCase("movie")){
                        intent = new Intent(context, DetailActivity.class);
                        intent.putExtra("movie_id", title.getTag().toString());
                        v.getContext().startActivity(intent);
                    }else if(mediaType.getText().toString().equalsIgnoreCase("tv")){
                        intent = new Intent(context, DetailActivity.class);
                        intent.putExtra("tv_id", title.getTag().toString());
                        v.getContext().startActivity(intent);
                    }else if(mediaType.getText().toString().equalsIgnoreCase("person")) {
                        intent = new Intent(context, CelebDetailActivity.class);
                        intent.putExtra("celeb_id", title.getTag().toString());
                        v.getContext().startActivity(intent);
                    }
                }
            });
        }

        public void bind(Search search){
            Picasso.get()
                    .load(IMAGE_BASE_URL + search.getPoster_path())
                    .fit()
                    .into(poster);
            title.setTag(search.getId());

            switch (search.getMedia_type()) {
                case "movie": {
                    title.setText(search.getTitle());
                    mediaType.setText("Movie");
                    release_info.setText(search.getRelease_date());
                    synopsis.setText(search.getOverview());
                    int ratePercent = Math.round(search.getVote_average()) * 10;
                    ratingCount.setText(String.valueOf(ratePercent));
                    break;
                }
                case "tv": {
                    title.setText(search.getName());
                    mediaType.setText("Tv Show");
                    release_info.setText(search.getFirst_air_date());
                    synopsis.setText(search.getOverview());
                    int ratePercent = Math.round(search.getVote_average()) * 10;
                    ratingCount.setText(String.valueOf(ratePercent));
                    break;
                }
                case "person": {
                    title.setText(search.getName());
                    mediaType.setText("Celebrity");
                    int ratePercent = Math.round(search.getPopularity()) * 10;
                    ratingCount.setText(String.valueOf(ratePercent));
                    break;
                }

            }
        }
    }

    public void addResult(Search result){
        if(!(result.getPopularity() < 1) || !(result.getVote_average() < 1)){
            this.results.add(result);
            notifyDataSetChanged();
        }
    }

    public void addResults(ArrayList<Search> results){
        for(Search result:results){
            if(result.getPopularity() > 0 && result.getVote_average() > 0 && !result.isAdultContent()){
                this.results.add(result);
                notifyDataSetChanged();
            }
        }
    }

    public void clearResults(){
        this.results.clear();
        notifyDataSetChanged();
    }
}
