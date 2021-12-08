package dev.thec0dec8ter.tmdb.custom_views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import dev.thec0dec8ter.tmdb.R;

public class CustomButton extends FrameLayout {

    private View buttonView;

    public CustomButton(@NonNull @NotNull Context context) {
        super(context);
        initViews(context);
    }

    public CustomButton(@NonNull @NotNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initViews(context);
    }

    public CustomButton(@NonNull @NotNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

    }

    private void initViews(Context context){
        buttonView = LayoutInflater.from(context).inflate(R.layout.custom_button, this, false);
        this.addView(buttonView);
//        leftTrack = switchView.findViewById(R.id.left_track);
//        rightTrack = switchView.findViewById(R.id.right_track);
//        thumbText = switchView.findViewById(R.id.thumb_text);
//        thumb = switchView.findViewById(R.id.thumb);
    }
}