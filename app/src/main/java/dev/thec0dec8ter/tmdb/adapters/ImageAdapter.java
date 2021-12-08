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

import static dev.thec0dec8ter.tmdb.network.RetrofitClientInstance.IMAGE_BASE_URL;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MediaViewHolder> {

    private final ArrayList<String> images = new ArrayList<>();

    private boolean isVideo = false;

    public ImageAdapter() {

    }

    @NonNull
    @Override
    public MediaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_poster, parent, false);
        return new MediaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MediaViewHolder holder, int position) {
        holder.bind(images.get(position));
    }

    @Override
    public int getItemCount() {
        return images.size();
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
            Picasso.get().load(IMAGE_BASE_URL+imagePath).fit().into(imgMedia);
        }
    }

    public void addImage(String image) {
        this.images.add(image);
        notifyDataSetChanged();
    }
    public void addImages(ArrayList<String> images) {
        this.images.addAll(images);
        notifyDataSetChanged();
    }
}
