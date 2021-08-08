package dev.thec0dec8ter.tmdb.adapters;

import android.text.Html;
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
        TextView ratingCount;
        TextView mediaType;
        TextView year;
        TextView genreName;

        public SearchviewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.poster);
            title = itemView.findViewById(R.id.title);
            ratingCount = itemView.findViewById(R.id.rating_count);
            mediaType = itemView.findViewById(R.id.media_type);
            year = itemView.findViewById(R.id.year);
            genreName = itemView.findViewById(R.id.genre_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        public void bind(Search search){
            Picasso.get()
                    .load(IMAGE_BASE_URL + search.getPoster_path())
                    .fit()
                    .into(poster);
            title.setTag(search.getId());
            int ratePercent = Math.round(search.getVote_average())*10;
            mediaType.setText(search.getMedia_type());
            if(search.getMedia_type().equals("person")){
                title.setText(Html.fromHtml("<b>Name: </b>" + search.getName()));
            }else if(search.getMedia_type().equals("movie")){
                title.setText(Html.fromHtml("<b>Title: </b>" + search.getTitle()));
                ratingCount.setText(String.valueOf(ratePercent));
            }else {
                title.setText(Html.fromHtml("<b>Title: </b>" + search.getName()));
                ratingCount.setText(String.valueOf(ratePercent));
            }

        }
    }

    public void addResult(Search result){
        this.results.add(result);
        notifyDataSetChanged();
    }

    public void addResults(ArrayList<Search> results){
        this.results.addAll(results);
        notifyDataSetChanged();
    }

    public void clearResults(){
        this.results.clear();
        notifyDataSetChanged();
    }
}
