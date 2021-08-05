package dev.thec0dec8ter.tmdb.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import dev.thec0dec8ter.tmdb.R;
import dev.thec0dec8ter.tmdb.network.NetworkUtils;

public class MediaAdapter extends RecyclerView.Adapter<MediaAdapter.MediaViewHolder> {

    private final ArrayList<String> media = new ArrayList<>();

    private boolean isVideo = false;

    public MediaAdapter() {

    }

    @NonNull
    @Override
    public MediaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_media, parent, false);
        return new MediaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MediaViewHolder holder, int position) {
        holder.bind(media.get(position));
    }

    @Override
    public int getItemCount() {
        return media.size();
    }

    public class MediaViewHolder extends RecyclerView.ViewHolder {
        ImageView imgMedia;
        CardView playBtnCard;

        public MediaViewHolder(@NonNull View itemView) {
            super(itemView);
            imgMedia = itemView.findViewById(R.id.img_media);
            playBtnCard = itemView.findViewById(R.id.play_btn_card);
        }

        public void bind(String imagePath) {
            if(!isVideo){
                playBtnCard.setVisibility(View.GONE);
            }
            Picasso.get().load(NetworkUtils.IMAGE_BASE_URL+imagePath).fit().into(imgMedia);
        }
    }

    public void addMedia(String media) {
        this.media.add(media);
        notifyDataSetChanged();
    }
    public void addMedias(ArrayList<String> media) {
        this.media.addAll(media);
        notifyDataSetChanged();
    }
}
