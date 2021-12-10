package dev.thec0dec8ter.tmdb.adapters;

import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;

import dev.thec0dec8ter.tmdb.R;

public class KeywordAdapter extends RecyclerView.Adapter<KeywordAdapter.KeywordViewHolder> {

    private final ArrayList<String> keywords = new ArrayList<>();
    private final ArrayList<String> values = new ArrayList<>();

    private String tag;
    private String[] queryParameter = null;

    public KeywordAdapter(){

    }

    public KeywordAdapter(String[] keyword_array,String[] values_array, String tag){
        keywords.addAll(Arrays.asList(keyword_array));
        values.addAll(Arrays.asList(values_array));
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
        holder.bind(keywords.get(position));
    }

    @Override
    public int getItemCount() {
        return keywords.size();
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
                        setQueryParameter(values.get(getAdapterPosition()));
                    }else {
                        removeTextBackground(txtKeyword);
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

    public String[] getQueryParameter(){
        return queryParameter;
    }

    public void setQueryParameter(String value){
        queryParameter = new String[]{tag, value};
    }

}
