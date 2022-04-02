package dev.thec0dec8ter.tmdb.adapters;

import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import dev.thec0dec8ter.tmdb.R;

public class KeywordAdapter extends RecyclerView.Adapter<KeywordAdapter.KeywordViewHolder> {

    private String[] keywords;
    private String[] values;

    private String tag;
    private String queryParameter = "";

    public KeywordAdapter(String tag, String[] keywords, String[] values){
        this.keywords = keywords;
        this.values = values;
        this.tag = tag;
    }

    @NonNull
    @NotNull
    @Override
    public KeywordViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_keyword, parent, false);
        return new KeywordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull KeywordAdapter.KeywordViewHolder holder, int position) {
        holder.bind(keywords[position]);
    }

    @Override
    public int getItemCount() {
        return keywords.length;
    }

    public class KeywordViewHolder extends RecyclerView.ViewHolder{
        TextView txtKeyword;

        public KeywordViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            txtKeyword = itemView.findViewById(R.id.txt_keyword);

            txtKeyword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int color = v.getContext().getColor(R.color.dark_blue);
                    if(txtKeyword.getBackgroundTintList() != ColorStateList.valueOf(color)){
                        changeTextBackground(txtKeyword);
                        setQueryParameter(values[getAdapterPosition()]);
                    }else {
                        removeTextBackground(txtKeyword);
                        setQueryParameter("");
                    }
                }
            });
        }

        public void bind(String keyword){
            txtKeyword.setText(keyword);
        }
    }

    public static void changeTextBackground(TextView textView){
        textView.setTextColor(textView.getContext().getColorStateList(R.color.light_green));
        textView.setBackgroundTintList(textView.getContext().getColorStateList(R.color.dark_blue));
    }

    public static void removeTextBackground(TextView textView){
        textView.setTextColor(textView.getContext().getColorStateList(R.color.dark_blue));
        textView.setBackground(textView.getContext().getDrawable(R.drawable.curved_corners));
        textView.setBackgroundTintList(null);
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setKeywords(String[] keywords) {
        this.keywords = keywords;
    }

    public void setValues(String[] values) {
        this.values = values;
    }

    public String getQueryParameter(){
        return queryParameter;
    }

    public void setQueryParameter(String queryParameter){
        this.queryParameter = queryParameter;
    }

}
