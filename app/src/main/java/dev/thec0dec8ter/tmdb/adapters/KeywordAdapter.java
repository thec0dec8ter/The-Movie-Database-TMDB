package dev.thec0dec8ter.tmdb.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import dev.thec0dec8ter.tmdb.R;

public class KeywordAdapter extends RecyclerView.Adapter<KeywordAdapter.KeywordViewHolder> {

    private final ArrayList<String> texts = new ArrayList<>();

    private boolean clickable = false;

    public KeywordAdapter(){

    }

    public KeywordAdapter(boolean clickable,List<String> texts){
        this.texts.addAll(texts);
        this.clickable = clickable;
    }

    @NonNull
    @Override
    public KeywordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_keyword, parent, false);
        return new KeywordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KeywordViewHolder holder, int position) {
        holder.bind(texts.get(position));
    }

    @Override
    public int getItemCount() {
        return texts.size();
    }

    public class KeywordViewHolder extends RecyclerView.ViewHolder{
        TextView keyword;

        public KeywordViewHolder(@NonNull View itemView) {
            super(itemView);
            keyword = itemView.findViewById(R.id.keyword_txt);

            Context context = itemView.getContext();
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(clickable) {
                        if (keyword.getCurrentTextColor() != context.getResources().getColor(R.color.light_green, null)) {
                            keyword.setTextColor(context.getResources().getColorStateList(R.color.light_green, null));
                            keyword.setBackgroundTintList(context.getResources().getColorStateList(R.color.dark_blue, null));
                        } else {
                            keyword.setTextColor(context.getResources().getColorStateList(R.color.black, null));
                            keyword.setBackground(context.getResources().getDrawable(R.drawable.curved_corners, null));
                            keyword.setBackgroundTintList(null);
                        }
                    }
                }
            });
        }

        public void bind(String text){
            keyword.setText(text);
        }
    }

    public void addTexts(List<String> texts){
        this.texts.addAll(texts);
        notifyDataSetChanged();
    }

    public void setClickable(boolean clickable) {
        this.clickable = clickable;
    }


}
